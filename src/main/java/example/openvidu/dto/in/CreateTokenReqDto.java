package example.openvidu.dto.in;

import lombok.Getter;

@Getter
public class CreateTokenReqDto {
    String chatRoomUuid;
    String nickname;
}
