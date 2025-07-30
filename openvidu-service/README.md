# OpenVidu 서비스

이 프로젝트는 WebRTC 애플리케이션을 위한 백엔드 서비스 역할을 하는 Spring Boot 애플리케이션으로, 실시간 통신 기능을 위해 LiveKit과 통합됩니다.

## 주요 기능

-   LiveKit 룸에 대한 액세스 토큰 생성
-   룸의 이벤트에 응답하기 위한 LiveKit 웹훅 처리

## 요구 사항

-   Java 17
-   Gradle
-   Docker 및 Docker Compose (Docker로 실행 시)

## 시작하기

### 사전 준비

필요한 소프트웨어가 설치되어 있는지 확인하십시오.

### 프로젝트 빌드

프로젝트를 빌드하고 JAR 파일을 생성하려면 다음 명령을 실행하십시오.

```bash
./gradlew build
```

JAR 파일은 `build/libs` 디렉토리에 생성됩니다.

### 로컬에서 실행

1.  **설정:**
    `src/main/resources`에 `application.yml` 파일을 생성하거나 다음 환경 변수를 설정하십시오.
    -   `SERVER_PORT`: 애플리케이션이 실행될 포트 (기본값: `1000`).
    -   `LIVEKIT_API_KEY`: LiveKit API 키 (기본값: `devkey`).
    -   `LIVEKIT_API_SECRET`: LiveKit API 시크릿 (기본값: `secret`).

2.  **애플리케이션 실행:**

    ```bash
    java -jar build/libs/oepnvidu-serivce-0.0.1-SNAPSHOT.jar
    ```

### Docker로 실행

1.  **Docker 이미지 빌드:**

    ```bash
    docker build -t openvidu-service .
    ```

2.  **Docker Compose로 실행:**
    제공된 `docker-compose-openvidu.yml`은 서비스를 실행하도록 구성되어 있습니다. 루트 디렉토리에 필요한 환경 변수가 포함된 `.env` 파일을 생성해야 합니다.

    **.env 파일 예시:**
    ```
    LIVEKIT_API_KEY=your_api_key
    LIVEKIT_API_SECRET=your_api_secret
    ```

    그런 다음 서비스를 실행합니다.
    ```bash
    docker-compose -f docker-compose-openvidu.yml up -d
    ```

## API 엔드포인트

### 1. 토큰 생성

-   **URL:** `/token`
-   **Method:** `POST`
-   **Header:**
    -   `X-Member-UUID`: (문자열, 필수) - 회원의 고유 식별자.
-   **요청 본문:**
    ```json
    {
      "chatRoomUuid": "string",
      "nickname": "string"
    }
    ```
-   **성공 응답 (200 OK):**
    ```json
    {
      "token": "generated_jwt_token"
    }
    ```
-   **오류 응답 (400 Bad Request):**
    ```json
    {
      "errorMessage": "chatRoomUuid and memberUuid are required"
    }
    ```

### 2. LiveKit 웹훅

-   **URL:** `/livekit/webhook`
-   **Method:** `POST`
-   **Consume:** `application/webhook+json`
-   **Description:** 이 엔드포인트는 LiveKit 서버에서 웹훅 이벤트를 수신하기 위한 것입니다. LiveKit 프로젝트 설정에서 이 URL을 구성해야 합니다.
-   **성공 응답 (200 OK):**
    ```
    ok
    ```