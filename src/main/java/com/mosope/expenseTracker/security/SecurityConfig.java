package com.mosope.expenseTracker.security;

import com.mosope.expenseTracker.security.filter.AuthenticationFilter;
import com.mosope.expenseTracker.security.filter.ExceptionHandlerFilter;
import com.mosope.expenseTracker.security.filter.JWTAuthorizationFilter;
import com.mosope.expenseTracker.security.manager.CustomAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationManager customAuthenticationManager;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/user/login");

        http.
                csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize.
                        requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
