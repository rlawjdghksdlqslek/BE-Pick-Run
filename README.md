# Bookmark Service

## 1. 프로젝트 소개

게시글을 북마크하고 관리하는 기능을 제공하는 마이크로서비스입니다.

## 2. 기술 스택

- **언어:** Java 17
- **프레임워크:** Spring Boot 3.5.0
- **데이터베이스:** MySQL
- **라이브러리:**
    - Spring Data JPA
    - Spring Web
    - Spring Cloud Netflix Eureka Client
    - Spring Cloud OpenFeign
    - Lombok
    - Springdoc OpenAPI (Swagger)

## 3. 주요 기능

- **북마크 추가:** 사용자가 특정 게시글을 북마크에 추가할 수 있습니다.
- **북마크 삭제:** 사용자가 추가했던 북마크를 삭제할 수 있습니다.
- **북마크 여부 확인:** 사용자가 특정 게시글을 북마크했는지 확인할 수 있습니다.
- **북마크 목록 조회:** 사용자가 북마크한 게시글 목록을 조회할 수 있습니다.

## 4. API 명세

Swagger UI를 통해 API 명세를 확인할 수 있습니다.

- **Swagger UI:** `http://localhost:2000/swagger-ui.html`

| Method | URI | 설명 |
| --- | --- | --- |
| POST | /api/v1/bookmark/{postUuid} | 게시글 북마크 추가 |
| DELETE | /api/v1/bookmark/{postUuid} | 게시글 북마크 제거 |
| GET | /api/v1/bookmark/{postUuid} | 게시글 북마크 여부 확인 |
| GET | /api/v1/bookmark/list | 회원 북마크 목록 조회 |

## 5. 실행 방법

### 5.1. Docker Compose 사용

```bash
docker-compose -f docker-compose-bookmark.yml up -d
```

### 5.2. 직접 실행

```bash
./gradlew build
java -jar build/libs/bookmark-service-0.0.1-SNAPSHOT.jar
```
