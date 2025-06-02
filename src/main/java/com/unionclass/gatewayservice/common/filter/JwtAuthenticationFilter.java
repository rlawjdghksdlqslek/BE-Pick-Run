package com.unionclass.gatewayservice.common.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unionclass.gatewayservice.common.exception.BaseErrorResponse;
import com.unionclass.gatewayservice.common.exception.ErrorCode;
import com.unionclass.gatewayservice.common.security.JwtProvider;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    private final JwtProvider jwtProvider;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        super(Config.class);
        this.jwtProvider = jwtProvider;
    }

    @Setter
    @Getter
    public static class Config {
        private List<String> skipPaths;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();
            log.info("요청 경로: {}", path);

            for (String skipPath : config.getSkipPaths()) {
                if (antPathMatcher.match(skipPath, path)) {
                    log.info("인증 제외 경로 → 필터 통과: {}", path);
                    return chain.filter(exchange);
                }
            }

            String authorizationHeader = request.getHeaders().getFirst("Authorization");
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return handleException(exchange, ErrorCode.NO_JWT_TOKEN);
            }

            String token = authorizationHeader.replace("Bearer ", "");
            if (!jwtProvider.validateToken(token)) {
                return handleException(exchange, ErrorCode.INVALID_TOKEN);
            }

            return chain.filter(exchange);
        };
    }

    private Mono<Void> handleException(ServerWebExchange exchange, ErrorCode errorCode) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(errorCode.getHttpStatus());
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        BaseErrorResponse errorResponse = BaseErrorResponse.of(
                errorCode.getHttpStatus(),
                errorCode.isSuccess(),
                errorCode.getCode(),
                errorCode.getMessage());

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] data;
        try {
            data = objectMapper.writeValueAsBytes(errorResponse);
        } catch (JsonProcessingException e) {
            data = new byte[0];
        }

        DataBuffer buffer = response.bufferFactory().wrap(data);
        return response.writeWith(Mono.just(buffer)).then(Mono.empty());
    }
}