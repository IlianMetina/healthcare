package com.myapp.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CookieToHeaderFilter implements GlobalFilter, Ordered {

    private static final String COOKIE_NAME = "jwt";
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        HttpCookie jwtInCookie = request.getCookies().getFirst(COOKIE_NAME);

        System.out.println("[" + java.time.Instant.now() + "] Cookie trouvé : " + (jwtInCookie != null ? jwtInCookie.getValue() : "AUCUN"));
        if(jwtInCookie != null){
            String token = jwtInCookie.getValue();
            ServerHttpRequest mutatedRequest = request.mutate()
                    .headers(headers -> headers.set(AUTH_HEADER, BEARER_PREFIX + token))
                    .build();

            ServerWebExchange mutatedExchange = exchange.mutate()
                    .request(mutatedRequest)
                    .build();

            return chain.filter(mutatedExchange);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
