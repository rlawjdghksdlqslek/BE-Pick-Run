package example.openvidu.presentation;

import example.openvidu.dto.in.CreateTokenReqDto;
import io.livekit.server.AccessToken;
import io.livekit.server.RoomJoin;
import io.livekit.server.RoomName;
import io.livekit.server.WebhookReceiver;
import livekit.LivekitWebhook.WebhookEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class LiveKitController {

	@Value("${livekit.api.key}")
	private String LIVEKIT_API_KEY;

	@Value("${livekit.api.secret}")
	private String LIVEKIT_API_SECRET;

	@PostMapping(value = "/token")
	public ResponseEntity<Map<String, String>> createToken(
			@RequestHeader("X-Member-UUID") String memberUuid,
			@RequestBody CreateTokenReqDto createTokenReqDto
			) {

		if (createTokenReqDto.getChatRoomUuid() == null || memberUuid == null) {
			return ResponseEntity.badRequest().body(Map.of("errorMessage", "chatRoomUuid and memberUuid are required"));
		}

		AccessToken token = new AccessToken(LIVEKIT_API_KEY, LIVEKIT_API_SECRET);
		token.setName(createTokenReqDto.getNickname());
		token.setIdentity(memberUuid);
		token.addGrants(new RoomJoin(true), new RoomName(createTokenReqDto.getChatRoomUuid()));
		log.info("chatRoomUuid: " + createTokenReqDto.getChatRoomUuid());

		return ResponseEntity.ok(Map.of("token", token.toJwt()));
	}

	@PostMapping(value = "/livekit/webhook", consumes = "application/webhook+json")
	public ResponseEntity<String> receiveWebhook(@RequestHeader("Authorization") String authHeader, @RequestBody String body) {
		WebhookReceiver webhookReceiver = new WebhookReceiver(LIVEKIT_API_KEY, LIVEKIT_API_SECRET);
		try {
			WebhookEvent event = webhookReceiver.receive(body, authHeader);
			System.out.println("LiveKit Webhook: " + event.toString());
		} catch (Exception e) {
			System.err.println("Error validating webhook event: " + e.getMessage());
		}
		return ResponseEntity.ok("ok");
	}

}
