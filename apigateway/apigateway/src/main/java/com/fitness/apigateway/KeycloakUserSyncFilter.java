package com.fitness.apigateway;

import com.fitness.apigateway.User.RegisterRequest;
import com.fitness.apigateway.User.UserService;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class KeycloakUserSyncFilter implements WebFilter {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        String userId = exchange.getRequest().getHeaders().getFirst("X-User-ID");
        RegisterRequest registerRequest = getUserDetails(token);

        if (userId == null) {
            userId = registerRequest.getKeycloakId();
        }

        if (userId != null && token != null){
            String finalUserId = userId;

            return userService.validateUser(userId)

                    .flatMap(exist -> {
                        if (!exist) {
                            log.info("User does not exist, Proceeding to sync.");
                            if (registerRequest != null) {
                                return userService.registerUser(registerRequest)
                                        .then(Mono.empty());
                            } else {
                                log.error("Failed to extract user details from token. Skipping sync.");
                                return Mono.empty();
                            }

                        } else {
                            log.info("User already exist, Skipping sync.");
                            return Mono.empty();
                        }
                    })
                    .then(Mono.defer(() -> {
                        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                                .header("X-User-ID", finalUserId)
                                .build();
                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
                    }));
        }
        return chain.filter(exchange);
    }

    private RegisterRequest getUserDetails(String token) {
        log.info("Extracting user details from token.");
        try {
            String tokenWithoutBearer = token.replace("Bearer ", "").trim();
            SignedJWT signedJWT = SignedJWT.parse(tokenWithoutBearer);
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setEmail(claims.getStringClaim("email"));
            registerRequest.setKeycloakId(claims.getStringClaim("sub"));
            registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            registerRequest.setFirstName(claims.getStringClaim("given_name"));
            registerRequest.setLastName(claims.getStringClaim("family_name"));
            return registerRequest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

//try {
//        if (token != null && token.startsWith("Bearer ")) {
//String jwtToken = token.substring(7);
//SignedJWT signedJWT = SignedJWT.parse(jwtToken);
//JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
//
//String keycloakId = claims.getSubject();
//String email = claims.getStringClaim("email");
//String firstName = claims.getStringClaim("given_name");
//String lastName = claims.getStringClaim("family_name");
//
//RegisterRequest registerRequest = new RegisterRequest();
//                registerRequest.setKeycloakId(keycloakId);
//                registerRequest.setEmail(email);
//                registerRequest.setFirstName(firstName);
//                registerRequest.setLastName(lastName);
//                registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
//
//        return registerRequest;
//            }
//                    } catch (Exception e) {
//        log.error("Error parsing JWT token: {}", e.getMessage());
//        }
//        return null;