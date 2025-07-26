package pro.fennex.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class ApiServerAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        return Mono.fromRunnable(() -> {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            // Optionally, you can write a custom response body here
            // For example: exchange.getResponse().writeWith(Mono.just(DataBufferFactory.wrap("Unauthorized".getBytes())));
        });
    }
}