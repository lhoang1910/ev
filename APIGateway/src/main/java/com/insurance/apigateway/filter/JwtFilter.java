package com.insurance.apigateway.filter;

import com.insurance.apigateway.utils.JwtExtract;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
public class JwtFilter implements GatewayFilter {

    private final JwtExtract jwtExtract;

    public JwtFilter(JwtExtract jwtExtract) {
        this.jwtExtract = jwtExtract;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        final List<String> apiEndpoints = List.of("/api/auth/sign-up", "/api/auth/sign-in");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {
            if (authMissing(request)) return onError(exchange);

            String token = request.getHeaders().getOrEmpty("Authorization").stream().findFirst().orElse(null);

            if (token != null && token.startsWith("Bearer ")) token = token.substring(7);

            try {
                jwtExtract.validateToken(token);
                String username = jwtExtract.extractUsername(token);

                if (username != null) {
                    // Log thêm để kiểm tra giá trị
                    System.out.println("Adding header loggedInUser: " + username);

                    request = exchange.getRequest().mutate().header("loggedInUser", username).build();
                    exchange = exchange.mutate().request(request).build();

                    // Kiểm tra lại header vừa thêm
                    String addedUser = exchange.getRequest().getHeaders().getFirst("loggedInUser");
                    System.out.println("Header loggedInUser after mutate: " + addedUser);
                } else {
                    return onError(exchange);
                }
            } catch (Exception e) {
                return onError(exchange);
            }
        }
        return chain.filter(exchange);
    }

    private boolean authMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private Mono<Void> onError(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
