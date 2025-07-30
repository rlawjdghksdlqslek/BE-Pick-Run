# Chat Service

## 📖 프로젝트 소개
MSA(Microservice Architecture) 기반의 채팅 서비스입니다.

## ✨ 주요 기능
- **채팅방 관리**: 1:1 채팅방 생성 및 목록 조회를 제공합니다.
- **실시간 채팅**: WebSocket을 통해 실시간으로 메시지를 주고받을 수 있습니다.
- **메시지 관리**: 메시지 저장, 조회 및 읽음 처리 기능을 제공합니다.

## 🛠️ 기술 스택
- **언어**: Java 17
- **프레임워크**: Spring Boot 3.5.0
- **데이터베이스**: JPA, MySQL, MongoDB, QueryDSL
- **실시간 통신**: WebSocket
- **API 문서**: Swagger
- **빌드 도구**: Gradle
- **배포**: Docker, AWS ECR, AWS EC2

## 🚀 실행 방법
### Docker Compose
```bash
docker-compose -f docker-compose-chat.yml up -d
```

### 수동 빌드 및 실행
1. **Gradle 빌드**
```bash
./gradlew build -x test
```
2. **Docker 이미지 빌드**
```bash
docker build -t chat-service:latest .
```
3. **Docker 컨테이너 실행**
```bash
docker run -p 2000:2000 chat-service:latest
```

## 📝 API 명세
API 명세는 Swagger를 통해 제공됩니다.
- [Swagger UI](http://localhost:2001/swagger-ui/index.html)

## ⚙️ CI/CD
- **GitHub Actions**를 사용하여 `main` 브랜치에 push 또는 pull request가 발생할 때 자동으로 빌드 및 배포를 수행합니다.
- **배포 과정**
    1. Gradle 빌드
    2. Docker 이미지 빌드 및 AWS ECR에 푸시
    3. AWS EC2에 접속하여 최신 이미지로 업데이트 및 실행

## 🔗 엔드포인트
### ChatRoomController
| Method | URI | 설명 |
| --- | --- | --- |
| POST | /api/v1/chat-room/create | 채팅방 생성 |
| PATCH | /api/v1/chat-room/read/{chatRoomUuid} | 채팅방 읽음 처리 |
| GET | /api/v1/chat-room/list | 채팅방 목록 조회 |
| GET | /api/v1/chat-room/{chatRoomUuid} | 단일 채팅방 정보 조회 |
| GET | /api/v1/chat-room/{chatRoomUuid}/message | 채팅방 메시지 목록 조회 |

### ChatMessageController
| Method | URI | 설명 |
| --- | --- | --- |
| MESSAGE | /chat.sendMessage | 메시지 전송 |