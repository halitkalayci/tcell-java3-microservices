package com.turkcell.authserver.core.configuration;

import com.turkcell.authserver.services.abstracts.UserService;
import com.turkcell.core.security.BaseJwtFilter;
import com.turkcell.core.security.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final BaseSecurityService baseSecurityService;
    private static final String[] WHITE_LIST = {
      "/api/v1/auth/**",
      "/swagger-ui/**",
      "/v2/api-docs",
      "/v3/api-docs",
      "/v3/api-docs/**",
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        baseSecurityService.configureCommonSecurityRules(http);

        http
                .authorizeHttpRequests((req)->
                        req
                                .requestMatchers(WHITE_LIST).permitAll()
                                .requestMatchers("/api/v1/admin").hasAnyAuthority("admin")
                                .requestMatchers(HttpMethod.POST, "/api/v1/test/**").hasAnyAuthority("Test.Add")
                                .requestMatchers(HttpMethod.PUT, "/api/v1/test/**").hasAnyAuthority("Test.Update")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/test/**").hasAnyAuthority("Test.Delete")
                                .anyRequest().authenticated()
                );

        return http.build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}