package example.openvidu.presentation;

import example.openvidu.application.OpenViduServiceImpl;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/video")
public class OpenViduController {

    private final OpenViduServiceImpl openViduService;

    @PostMapping("/session")
    public ResponseEntity<Map<String, String>> createSession(@RequestParam String sessionId)
            throws OpenViduJavaClientException, OpenViduHttpException {
        String id = openViduService.createSession(sessionId);
        return ResponseEntity.ok(Map.of("sessionId", id));
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> createToken(@RequestParam String sessionId)
            throws OpenViduJavaClientException, OpenViduHttpException {
        String token = openViduService.createToken(sessionId);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
