package example.openvidu.config;

import example.openvidu.util.SslUtil;
import io.openvidu.java.client.OpenVidu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OpenViduConfig {
    @Value("${openvidu.url}")
    private String url;

    @Value("${openvidu.secret}")
    private String secret;

    @Bean
    public OpenVidu openVidu()  throws Exception{
        log.info("SslUtil.disableCertificateValidation 실행 전");
        SslUtil.disableCertificateValidation(); // 인증서 무시 (테스트 용)
        log.info("SslUtil.disableCertificateValidation 실행 후");
        return new OpenVidu(url, secret);
    }
}