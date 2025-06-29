package example.openvidu.application;

import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenViduServiceImpl implements OpenViduService {

    private final OpenVidu openVidu;
    private final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 세션을 생성하거나 이미 존재하면 재사용합니다.
     *
     * @param customSessionId 프론트에서 요청한 세션 식별자
     * @return 실제 Session ID
     */
    public String createSession(String customSessionId) throws OpenViduJavaClientException, OpenViduHttpException {

        log.info("[세션생성] 요청 SessionId = {}", customSessionId);
        if (sessionMap.containsKey(customSessionId)) {
            log.info("[세션생성] 이미 존재하는 세션 재사용 → {}", customSessionId);
            return sessionMap.get(customSessionId).getSessionId();
        }

        try {
            // 1) 세션 프로퍼티 빌드
            SessionProperties properties = new SessionProperties.Builder()
                    .customSessionId(customSessionId)
                    .build();

            log.debug("[세션생성] SessionProperties = {}", properties);

            // 2) OpenVidu 서버 호출
            Session session = openVidu.createSession(properties);
            log.info("[세션생성] 세션 생성 성공 → sessionId={}", session.getSessionId());

            // 3) 캐시에 저장
            sessionMap.put(customSessionId, session);
            return session.getSessionId();

        } catch (Exception e) {
            log.error("[세션생성] 세션 생성 실패: {}", e.getMessage(), e);
            throw e;
        }
    }


    /**
     * 세션 ID로 토큰을 발급합니다.
     *
     * @param sessionId 기존 세션 ID
     * @return 발급된 토큰
     */
    public String createToken(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        log.info("[토큰발급] 요청 SessionId = {}", sessionId);

        Session session = sessionMap.get(sessionId);

        if (session == null) {
            log.warn("[토큰발급] 세션을 찾을 수 없습니다 → {}", sessionId);
            throw new RuntimeException("Session not found");
        }

        // 기본 권한으로 토큰 발급
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder().build();
        String token = session.createConnection(connectionProperties).getToken();

        log.info("[토큰발급] 토큰 발급 성공 → {}", token);
        return token;
    }
}
