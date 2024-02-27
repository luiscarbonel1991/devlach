package com.devlach.springsecurityoauth2resourceserver;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableMethodSecurity // For Method-Level Authorization
public class SpringSecurityOauth2ResourceServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauth2ResourceServerApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(
            @Value("classpath:private.pem") RSAPrivateKey privateKey,
            @Value("classpath:public.pem") RSAPublicKey publicKey
    ) {
        return args -> {
            RSAKey key = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
            var jwtEncoder = new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(key)));
            JwtClaimsSet builder = JwtClaimsSet.builder()
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plusMillis(400))
                    .subject("java")
                    .issuer("https://devlach.com")
                    .audience(List.of("invalid-audience"))
                    .claim("scp", Arrays.asList("tasks:read", "tasks:write"))
                    .build();
            var jwtEncoded = jwtEncoder.encode(JwtEncoderParameters.from(builder));
            var consolePretty = """
                    This is a JWT token that you can use to test the API:
                    {
                     copyToClipboard: "%s"
                    }
                    """.formatted(jwtEncoded.getTokenValue());
            System.out.println(consolePretty);
        };
    }

}
