package com.turkcell.orderservice.core.configuration;

import com.turkcell.core.security.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final BaseSecurityService baseSecurityService;

    private static final String[] WHITE_LIST = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        baseSecurityService.configureCommonSecurityRules(http);

        http.authorizeHttpRequests((req)->req
                .requestMatchers(WHITE_LIST).permitAll()
                .requestMatchers("/api/v1/orders/**").authenticated()
        );

        return http.build();
    }
}
