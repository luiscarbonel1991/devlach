package com.devlach.springsecurityoauth2resourceserver;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jwt.JWTClaimNames;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskSpringSecurityTest {

    @Autowired
    JwtEncoder jwtEncoder;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldShowInvalidTokenError() throws Exception {

        // GIVEN
        var token = customToken(builder -> builder
                .audience(List.of("invalid-audience"))
                .claim("scp", List.of("tasks:read", "tasks:write"))
                .issuedAt(Instant.now().minusSeconds(86400))
                .expiresAt(Instant.now().minusSeconds(86400))
        );

        System.out.println("token = " + token);

        // WHEN
        mvc.perform(MockMvcRequestBuilders.get("/tasks")
                        .header("Authorization", "Bearer " + token))
                // THEN
                .andExpect(status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.header().exists("WWW-Authenticate"))
                .andExpect(MockMvcResultMatchers.header().string("WWW-Authenticate", containsString("invalid_token")));

    }

    private String customToken(Consumer<JwtClaimsSet.Builder> customizer) {
        var builder = JwtClaimsSet.builder()
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(86400))
                .subject("java")
                .issuer("https://devlach.com")
                .audience(List.of("tasks-api-client"))
                .claim("scp", List.of("tasks:read", "tasks:write"));
        customizer.accept(builder);

        return jwtEncoder.encode(JwtEncoderParameters.from(builder.build())).getTokenValue();

    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        JwtEncoder jwtEncoder(
                @Value("classpath:private.pem") RSAPrivateKey privateKey,
                @Value("classpath:public.pem") RSAPublicKey publicKey
        ) {
            JWK jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
            return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(jwk)));
        }

        @Bean
        JwtDecoder jwtDecoder(
                @Value("classpath:public.pem") RSAPublicKey publicKey
        ) {
            NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(publicKey).build();
            var audience = new JwtClaimValidator<List<Object>>(JWTClaimNames.AUDIENCE, aud -> aud != null && aud.contains("tasks-api-client"));
            jwtDecoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(List.of(audience)));
            return jwtDecoder;
        }
    }

}
