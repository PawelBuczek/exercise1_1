package com.kodilla.exercise1_1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable();

        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/m1")
                .hasAnyRole("R1", "R2", "R3")
                .requestMatchers(HttpMethod.GET, "/m2")
                .hasAnyRole("R2", "R3")
                .requestMatchers(HttpMethod.GET, "/m3")
                .hasAnyRole("R3")
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("R1")
                .password(bCryptPasswordEncoder.encode("r1"))
                .roles("R1")
                .build());
        manager.createUser(User.withUsername("R2")
                .password(bCryptPasswordEncoder.encode("r2"))
                .roles("R1", "R2")
                .build());
        manager.createUser(User.withUsername("R3")
                .password(bCryptPasswordEncoder.encode("r3"))
                .roles("R1", "R2", "R3")
                .build());
        return manager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}