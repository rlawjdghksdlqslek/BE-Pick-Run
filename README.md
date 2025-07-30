# Pick-Learn 📚

![image](https://github.com/user-attachments/assets/a719da44-7838-4b06-a10e-fc124dce04d5)

<div align="center">

**AI가 아닌 사람 중심의 실시간 지식 공유 플랫폼**

AI가 빠르게 발전하며 정보 접근이 쉬워진 시대, <br>
질문하고, 답하고 서로의 지식과 경험을 나눌 수 있는 공간입니다.

</div>

---

## 📝 목차

- [프로젝트 소개](#-프로젝트-소개-및-개요)
- [배포 URL](#-배포-url)
- [기술 스택](#️-기술-및-개발-환경)
- [프로젝트 기간](#-프로젝트-기간)
- [시스템 아키텍처](#️-시스템-아키텍처)
- [마이크로서비스 목록](#-마이크로서비스-목록)
- [역할분담](#-역할분담)
- [주요 화면](#-주요-화면)
- [핵심 기능](#-핵심-기능)

---

## 📚 프로젝트 소개 및 개요
<hr>
- AI 가 빠르게 발전하며 정보 접근이 쉬워진 시대, 질문하고, 답하고 서로의 지식과 경험을 나눌 수 있는 AI 가 아닌 사람 중심의 실시간 지식 공유 플랫폼입니다

## 🔗 배포 URL (배포 중단)

현재 **Pick-Learn** 서비스는 배포가 중단되었습니다.

* **[👉 Pick-Learn 방문하기](https://pickandlearn.shop/)**

---

## 🛠️ 기술 및 개발 환경

### ⚙️ Back-end
<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-6DB33F?style=for-the-badge&logo=spring-cloud&logoColor=white)
![Spring Batch](https://img.shields.io/badge/Spring_Batch-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
<br>
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-000000?style=for-the-badge&logo=websocket&logoColor=white)
![Kafka](https://img.shields.io/badge/Kafka-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white)
![STOMP](https://img.shields.io/badge/STOMP-000000?style=for-the-badge&logo=stomp&logoColor=white)

</div>

### 🏗️ Infra
<div align="center">

![AWS](https://img.shields.io/badge/AWS-FF9900?style=for-the-badge&logo=amazon-aws&logoColor=white)
![EC2](https://img.shields.io/badge/EC2-FF9900?style=for-the-badge&logo=amazon-ec2&logoColor=white)
![S3](https://img.shields.io/badge/S3-FF9900?style=for-the-badge&logo=amazon-s3&logoColor=white)
![ECR](https://img.shields.io/badge/ECR-FF9900?style=for-the-badge&logo=amazon-ecr&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Docker Compose](https://img.shields.io/badge/Docker_Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Nginx](https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)

</div>

---

## 📅 프로젝트 기간

- **시작일**: 2025.05.06
- **종료일**: 2025.07.15
- **총 기간**: 약 2개월

---

## 🏛️ 시스템 아키텍처

<img width="1126" height="1056" alt="Image" src="https://github.com/user-attachments/assets/37af28ae-c7f3-43fa-9758-05442a4e676f" />

---

## ✨ CI/CD 파이프라인

<img width="1698" height="920" alt="Image" src="https://github.com/user-attachments/assets/f5e3536a-3792-4845-a10f-d7a27434e43f" />

---

## 🚀 마이크로서비스 목록

| Service                    | Repository                                                                        | Description                                  |
|----------------------------|-----------------------------------------------------------------------------------|----------------------------------------------|
| **Frontend**               | [`FE-Learn-Run`](https://github.com/3-Learn-Run/front)                            | 사용자 및 호스트를 위한 웹 애플리케이션                       |
| **Discovery Service**      | [`discovery-service`](https://github.com/3-Learn-Run/discovery-service/)          | 마이크로서비스들의 등록 및 검색을 담당하는 Eureka 서버            |
| **API Gateway**            | [`gateway-service`](https://github.com/3-Learn-Run/gateway-service/)              | 모든 클라이언트 요청을 각 마이크로서비스로 라우팅하는 단일 진입점 역할      |
| **Member Service**         | [`member-service`](https://github.com/3-Learn-Run/member-service/)                | 사용자 로그인, 회원가입, 소셜 로그인 및 비밀번호 변경 등 인증/인가 기능 제공 |
| **Profile Service**        | [`profile-service`](https://github.com/3-Learn-Run/profile-service/)              | 사용자 프로필 정보 관리                                |
| **Payment Service**        | [`payment-service`](https://github.com/3-Learn-Run/payment-service)               | 결제 요청/승인/취소 및 결제 내역 조회 등 결제 관련 비즈니스 로직 처리    |
| **Active History Service** | [`active-history-service`](https://github.com/3-Learn-Run/active-history-service) | 사용자 활동 이력 관리                                 |
| **Review Service**         | [`review-service`](https://github.com/3-Learn-Run/review-service)                 | 사용자 리뷰 관리                                    |
| **Post Service**           | [`post-service`](https://github.com/3-Learn-Run/post-service)                     | 질문 등록/수정/삭제 등 게시글 쓰기(Command) 기능 담당          |
| **Post Read Service**      | [`post-read-service`](https://github.com/3-Learn-Run/post-read-service)           | 게시글 읽기(Query) 기능 담당                                           |
| **Comment Service**        | [`Comment-service`](https://github.com/3-Learn-Run/Comment-service)               | 게시글 댓글 등록/수정/삭제 및 댓글 좋아요 기능 담당                       |
| **Chat Service**           | [`chat-service`](https://github.com/3-Learn-Run/chat-service)                     | 실시간 채팅 메시지 전송 및 채팅 내역 조회 기능 담당                         |
| **Openvidu Service**       | [`openvidu-service`](https://github.com/3-Learn-Run/openvidu-service)             | 비디오 통화를 위한 OpenVidu 토큰 생성 및 라이브 이벤트(웹훅) 처리|
| **Bookmark Service**       | [`bookmark-service`](https://github.com/3-Learn-Run/bookmark-service)             | 사용자 북마크 등록 및 삭제 기능 담당                        |
| **Point Service**          | [`point-service`](https://github.com/3-Learn-Run/point-service)            | 사용자 포인트 충전 및 포인트 내역 조회 기능 담당                            |
| **Infra Service**          | [`infra-service`](https://github.com/3-Learn-Run/infra-service)                    | MySQL, Redis, Kafka, MongoDB 등 핵심 인프라 자원 관리             |

---

## 👨‍💻 역할분담

#### 🐹 김정환

- **담당 영역**:
    - **Post-service**: 질문 등록/수정/삭제
    - **Post-read-service**: 카테고리별 질문 조회 (최신순, 인기순)
    - **Comment-service**: 댓글 등록/수정/삭제, 댓글 좋아요
    - **Bookmark-service**: 북마크 등록, 삭제
    - **Chat-service**: 채팅 전송, 채팅 내역 조회
    - **OpenVidu-service**: 토큰 생성, 라이브킷 웹훅
    - **Infra-service**: MySQL, Redis, Kafka, MongoDB 관리

#### 🐰 안동환

- **담당 영역**:
    - **Member-service**: 로그인, 회원가입, 소셜 로그인, 비밀번호 변경
    - **Profile-service**: 프로필 생성/수정/삭제, 프로필 이미지 등록/변경, 닉네임 변경, 자기소개 수정
    - **Active-history-service**: 활동 이력 조회
    - **Review-service**: 리뷰 생성/수정/삭제
    - **Payment-service**: 결제 요청/승인/취소, 결제 내역 조회
    - **Point-service**: 포인트 충전, 포인트 내역 조회

---

## 📸 주요 화면

#### 🏠 메인 화면
<img src="https://github.com/user-attachments/assets/1cde075c-e5aa-449c-b6dc-71746897128b" width="400px" />

#### 👤 사용자 인증
![회원가입](https://github.com/user-attachments/assets/53b313cc-c992-4a78-8692-aca4f772b5f3)

#### 📝 게시글 관리
|![질문 목록](https://github.com/user-attachments/assets/8f9ad5dc-4992-44d7-83c9-b9758500fa60)|![질문 상세 화면](https://github.com/user-attachments/assets/3f4b06af-ca78-439e-b0d4-b5c7181f90ac)|![검색](https://github.com/user-attachments/assets/469f8b28-1927-4af0-b148-7de8229712eb)|
|:--:|:--:|:--:|
|질문 목록|질문 상세|검색|

|![질문 생성](https://github.com/user-attachments/assets/532fa93d-0db9-4530-912f-71f8bd9428cd)|![질문 수정](https://github.com/user-attachments/assets/0270a9f9-203d-4f21-9965-df6f282b58cf)|![질문 삭제](https://github.com/user-attachments/assets/e579e413-f659-4650-9cc0-99e85fdf8d82)|
|:--:|:--:|:--:|
|질문 생성|질문 수정|질문 삭제|


|![댓글 생성](https://github.com/user-attachments/assets/ecd91a40-329b-49cd-bce0-06c56ef9243d)|![댓글 수정](https://github.com/user-attachments/assets/c216a874-dddd-42b1-a32d-8307f412b00e)|![댓글 삭제](https://github.com/user-attachments/assets/9bba39bc-46f4-4370-8dbe-64b19a14b909)|
|:--:|:--:|:--:|
|댓글 생성|댓글 수정|댓글 삭제|

#### 👤 프로필
![프로필 생성 gif](https://github.com/user-attachments/assets/449e362f-e111-4b6e-a871-34e19b8abc01)

#### 💬 채팅 서비스
![채팅 기능](https://github.com/user-attachments/assets/80fdd8e7-2ea5-43c2-8e93-5ed4178e24bb)

#### 💳 결제
![결제](https://github.com/user-attachments/assets/6d7899ca-ca6c-4c29-935d-da81c487633a)

---

## 팀 소개

### 🚀 Learn-Run 팀을 소개합니다!

안녕하세요, 저희는 4명의 Front-end 개발자로 구성된 **Learn-Run**팀입니다. </br>
배우면 바로 실천하고 달리는 팀 이라는 의미로, 새로운 기술이나 도전을 머뭇거리지 않고 빠르게 흡수하고 적용하는 실행력 중심의 팀
</br>

|                                                  **✨ 박수현**                                                   |                                     **✨ 박수아**                                     |                                     **✨ 김정환**                                      |                                     **✨ 안동환**                                      |
| :--------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------: |
| <img src="https://github.com/user-attachments/assets/f4f4d1c2-4a89-48a3-b149-3f0b77227de8" height=180 width=180> | <img src="https://avatars.githubusercontent.com/u/39180932?v=4" height=180 width=180> | <img src="https://avatars.githubusercontent.com/u/103404957?v=4" height=180 width=180> | <img src="https://avatars.githubusercontent.com/u/172372358?v=4" height=180 width=180> |
|                               **github**: [박수현](https://github.com/Sutaenghhh)                                |                   **github**: [박수아](https://github.com/dorabang)                   |               **github**: [김정환](https://github.com/rlawjdghksdlqslek)               |                 **github**: [안동환](https://github.com/Ahn-donghwan)                  |
|                            ![FrontEnd](https://img.shields.io/badge/FrontEnd-3f97fb)                             |               ![FrontEnd](https://img.shields.io/badge/FrontEnd-3f97fb)               |                ![BackEnd](https://img.shields.io/badge/BackEnd-3f97fb)                 |                ![BackEnd](https://img.shields.io/badge/BackEnd-3f97fb)                 |

<br/>

&nbsp;