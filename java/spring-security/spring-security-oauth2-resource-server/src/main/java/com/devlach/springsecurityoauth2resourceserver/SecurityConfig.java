package com.devlach.springsecurityoauth2resourceserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, CustomAuthenticationEntryPoint entryPoint) throws Exception {
        return httpSecurity.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.GET, "/tasks/**").hasAuthority("SCOPE_tasks:read")
                        .requestMatchers(HttpMethod.POST, "/tasks/**").hasAuthority("SCOPE_tasks:write")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
                        //.authenticationEntryPoint(entryPoint)  // Add the CustomAuthenticationEntryPoint to handle errors with ProblemDetail
                        .jwt(Customizer.withDefaults()))
                .build();
    }

    /* Validate audience claim with JwtDecoder */
/*    @Bean
    public JwtDecoder jwtDecoder(
            @Value("classpath:public.pem") RSAPublicKey publicKey
    ) {
        String audience = "tasks-api-client";
        var audiences = new JwtClaimValidator<List<String>>(AUD, claims -> claims != null
                && claims.contains(audience));
        DelegatingOAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(audiences);
        var jwtDecoder = NimbusJwtDecoder.withPublicKey(publicKey).build();
        jwtDecoder.setJwtValidator(validator);
        return jwtDecoder;
    }*/
}
