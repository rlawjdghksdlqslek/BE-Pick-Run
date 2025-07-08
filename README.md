# Post Service

게시물(Post) 및 카테고리(Category) 관리를 위한 마이크로서비스입니다.

## 주요 기능

- **게시물 관리**: 게시물의 생성(Create), 조회(Read), 수정(Update), 삭제(Delete) 기능을 제공합니다.
- **카테고리 관리**: 계층 구조를 가진 메인 카테고리 및 서브 카테고리를 관리합니다.
- **이벤트 발행**: 게시물 생성, 수정, 삭제 시 Apache Kafka를 통해 관련 이벤트를 발행하여 다른 서비스와의 데이터 일관성을 유지합니다.
- **API 문서화**: Springdoc OpenAPI (Swagger)를 통해 API 문서를 자동으로 생성하고 제공합니다.
- **서비스 디스커버리**: Spring Cloud Netflix Eureka Client를 사용하여 서비스 등록 및 조회를 지원합니다.

## 기술 스택

- **언어**: Java 17
- **프레임워크**: Spring Boot
- **데이터베이스**: Spring Data JPA (MySQL), Spring Data MongoDB
- **빌드 도구**: Gradle
- **메시징 큐**: Apache Kafka
- **유틸리티**: Lombok
- **서비스 디스커버리**: Spring Cloud Netflix Eureka Client
- **API 문서**: Springdoc OpenAPI
- **컨테이너**: Docker, Docker Compose
- **CI/CD**: GitHub Actions

## 실행 방법

### 사전 요구사항

- JDK 17 이상
- Gradle
- Docker & Docker Compose
- MySQL 및 MongoDB 인스턴스 (로컬 또는 원격)
- Apache Kafka 인스턴스 (로컬 또는 원격)
- Eureka Server 인스턴스 (로컬 또는 원격)

### 1. 프로젝트 빌드

```bash
./gradlew build
```

### 2. 애플리케이션 실행

#### 로컬에서 직접 실행

`application.yml` 및 `application-dev.yml`에 명시된 데이터베이스, Kafka, Eureka Server 등 외부 서비스 설정을 완료한 후 아래 명령어로 실행합니다.

```bash
./gradlew bootRun
```

#### Docker Compose를 사용하여 실행

`docker-compose-post.yml` 파일에 정의된 서비스들을 실행합니다. 이 방법을 사용하면 의존 관계에 있는 서비스(예: DB, Kafka, Eureka Server)를 함께 실행할 수 있습니다.

```bash
docker-compose -f docker-compose-post.yml up -d
```

## API 문서

애플리케이션 실행 후, 아래 URL에서 API 문서를 확인할 수 있습니다.

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`

## 프로젝트 구조

```
src
└── main
    └── java
        └── com
            └── example
                └── post_service
                    ├── category     # 카테고리 도메인 (MySQL)
                    ├── common       # 공통 모듈 (Exception, Response, Config 등)
                    ├── kafka        # Kafka Producer 및 이벤트
                    └── post         # 게시물 도메인 (MongoDB)
```

- **`category`**: 메인/서브 카테고리 관련 로직을 포함하며, MySQL을 사용합니다.
- **`post`**: 게시물 CRUD 관련 로직을 포함하며, MongoDB를 사용합니다.
- **`kafka`**: 게시물 변경에 따른 Kafka 이벤트를 정의하고 발행하는 로직을 포함합니다.
- **`common`**: 예외 처리, 공통 응답 형식, Swagger 설정 등 애플리케이션 전반에 사용되는 모듈을 포함합니다.

## CI/CD

`.github/workflows/deploy.yml` 파일을 통해 GitHub Actions를 사용한 CI/CD 파이프라인이 구성되어 있습니다. `main` 브랜치에 코드가 푸시되면 자동으로 배포가 진행됩니다.
