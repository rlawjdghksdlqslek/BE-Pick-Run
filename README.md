# Comment Service

## 프로젝트 설명

이 프로젝트는 댓글 기능을 제공하는 마이크로서비스입니다. 사용자는 댓글을 작성, 조회, 수정, 삭제할 수 있으며, 댓글에 좋아요를 누를 수 있습니다.

## 주요 기능

- 댓글 생성, 조회, 수정, 삭제
- 댓글 좋아요 기능
- Kafka를 이용한 이벤트 기반 통신
- Spring Boot 기반 RESTful API

## 시작하기

### 전제 조건

- Java 17
- Gradle
- Docker (선택 사항, 로컬 환경 설정 시)

### 빌드 및 실행

1. 프로젝트 클론:
   ```bash
   git clone <repository_url>
   cd comment-service
   ```

2. Gradle을 사용하여 빌드:
   ```bash
   ./gradlew clean build
   ```

3. 애플리케이션 실행:
   ```bash
   java -jar build/libs/comment-service-0.0.1-SNAPSHOT.jar
   ```

### Docker를 이용한 실행 (선택 사항)

```bash
 docker-compose -f docker-compose-comment.yml up --build
```

## API 문서

애플리케이션이 실행 중일 때 다음 URL에서 Swagger UI를 통해 API 문서를 확인할 수 있습니다:

`http://localhost:8080/swagger-ui/index.html`

## 사용 기술

- Spring Boot
- Spring Data MongoDB
- Kafka
- Gradle
- Docker

## 기여

기여를 환영합니다! 풀 리퀘스트를 보내기 전에 이슈를 열어 논의해 주세요.

## 라이선스

이 프로젝트는 MIT 라이선스에 따라 배포됩니다. 자세한 내용은 `LICENSE` 파일을 참조하십시오.
