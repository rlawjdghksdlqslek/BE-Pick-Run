# Post Read Service

## 1. 프로젝트 소개

**Post Read Service**는 게시글 조회, 검색 및 조회수 관리에 특화된 마이크로서비스입니다.

본 서비스는 MSA(Microservice Architecture) 환경에서 CQRS(Command Query Responsibility Segregation) 패턴의 **조회(Query) 책임**을 담당합니다. 게시글의 생성/수정/삭제와 같은 변경 이벤트는 Kafka를 통해 비동기적으로 수신하여 자체 데이터 저장소를 동기화합니다.

특히, 대규모 트래픽에서도 안정적인 성능을 보장하기 위해 **Redis와 스케줄러를 활용한 비동기 방식으로 조회수를 집계**하며, 서비스 디스커버리를 위해 Eureka Client를 사용합니다.

---

## 2. 아키텍처

### 전체 시스템 아키텍처

- **Service Discovery (Eureka)**: MSA 환경에서 각 서비스의 위치를 동적으로 찾고 관리합니다.
- **API Gateway / 다른 서비스 (Feign Client)**: 외부 요청을 받거나, 서비스 간 통신이 필요할 때 Feign Client를 사용하여 선언적으로 API를 호출합니다.
- **Event Source (e.g., Post-Write-Service)**: 게시글의 생성(Create), 수정(Update), 삭제(Delete)가 발생하면 Kafka로 이벤트를 발행합니다.
- **Message Queue (Kafka)**: 발행된 이벤트를 `post-created`, `post-updated`, `post-deleted` 등의 토픽으로 관리합니다.
- **Event Consumer (Post-Read-Service)**: 각 토픽을 구독(Subscribe)하고 있다가, 이벤트를 수신하면 MongoDB의 게시글 문서를 생성, 수정 또는 삭제하여 데이터 동기화를 수행합니다.

### 조회수 처리 아키텍처


1.  **조회 요청**: 클라이언트가 특정 게시글 조회를 요청합니다.
2.  **Redis에 조회수 증가**: API 서버는 DB에 직접 접근하는 대신, **Redis의 카운터를 1 증가**시키고 사용자에게 즉시 응답합니다. 이 방식은 DB I/O를 제거하여 API 응답 속도를 극대화합니다.
3.  **주기적 동기화**: **Spring Scheduler**가 정해진 주기(예: 1분)마다 실행됩니다.
4.  **DB 일괄 업데이트**: 스케줄러는 Redis에 누적된 모든 게시글의 조회수 정보를 가져와 **MongoDB에 일괄적으로 업데이트(Batch Update)**하여 데이터 정합성을 맞춥니다.

---

## 3. 기술 스택

- **Language**: `Java 17`
- **Framework**: 
    - `Spring Boot 3.4.5`
    - `Spring Cloud 2024.0.1`
- **Database**: `MongoDB`
- **Cache & In-memory Data Grid**: `Redis` (with `Redisson` client)
- **Message Queue**: `Apache Kafka`
- **Service Discovery**: `Netflix Eureka`
- **Declarative REST Client**: `OpenFeign`
- **API Documentation**: `SpringDoc (Swagger UI)`
- **Build Tool**: `Gradle`
- **Containerization**: `Docker`, `Docker Compose`

---

## 4. 실행 방법

### Prerequisites

- Java 17
- Gradle
- Docker
- Docker Compose

### Installation & Run

1.  **프로젝트 클론**
    ```bash
    git clone https://github.com/Learn-Run/post-read-service.git
    cd post-read-service
    ```

2.  **애플리케이션 프로퍼티 설정**
    `src/main/resources/application.yml` 파일과 각 프로필(`dev`, `prod`)에 맞는 yml 파일에 MongoDB, Redis, Kafka, Eureka 등 외부 서비스 접속 정보를 환경에 맞게 수정합니다.

    ```yaml
    spring:
      data:
        mongodb:
          uri: mongodb://localhost:27017/post_db
      redis:
        host: localhost
        port: 6379
      kafka:
        bootstrap-servers: localhost:9092
    
    eureka:
      client:
        service-url:
          defaultZone: http://localhost:8761/eureka
    ```

3.  **Docker Compose를 이용한 실행 (권장)**
    프로젝트 루트 경로에서 아래 명령어를 실행하면 애플리케이션과 모든 외부 의존성(MongoDB, Redis, Kafka, Eureka 등)이 함께 실행됩니다.

    ```bash
    # docker-compose-post-read.yml 파일에 모든 서비스가 정의되어 있는지 확인하세요.
    docker-compose -f docker-compose-post-read.yml up -d
    ```
    * `-d` 옵션은 백그라운드에서 실행합니다.

4.  **로컬에서 직접 실행**
    로컬 환경에 모든 의존성 서비스가 이미 구동 중일 경우, 아래 명령어로 Spring Boot 애플리케이션만 실행할 수 있습니다.

    ```bash
    ./gradlew clean build
    java -jar build/libs/postreadservice-0.0.1-SNAPSHOT.jar
    ```

---

## 5. API Endpoints

Swagger UI를 통해 API 명세를 확인하고 테스트할 수 있습니다.
- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`

주요 API는 다음과 같습니다.

| Method | URI | 설명 |
| :--- | :--- | :--- |
| `GET` | `/posts/{postId}` | 특정 게시글의 상세 정보를 조회합니다. |
| `GET` | `/posts` | 게시글 목록을 페이지네이션하여 조회합니다. (정렬 조건 파라미터 포함) |