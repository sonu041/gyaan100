package com.shuvankar.gyaan100.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
//@EnableWebSecurity
//@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String FRONTEND_LOCALHOST = "http://localhost:4200";
    private static final String FRONTEND_STAGING = "https://somehost.github.io";

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity.csrf()
                .disable()
                .authorizeExchange(exchange -> exchange
                                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyExchange()
                        .authenticated()
                        )
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
        serverHttpSecurity.cors().disable();
        System.out.println("-------Inside SecurityConfig------");
        return serverHttpSecurity.build();

    }
//
//    @Bean
//    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll()
//                .and()
//                .httpBasic();
//        http.cors();
//        System.out.println("-------Inside SecurityFilterChain------");
//        return http.build();
//    }

    @Bean
    CorsConfigurationSource corsConfiguration() {
        CorsConfiguration corsConfig = new CorsConfiguration();
//        System.out.println(corsConfig.getAllowCredentials().toString());
        corsConfig.applyPermitDefaultValues();
        corsConfig.addAllowedMethod(HttpMethod.PUT);
        corsConfig.addAllowedMethod(HttpMethod.DELETE);
        corsConfig.addAllowedMethod(HttpMethod.OPTIONS);
        corsConfig.setAllowedOrigins(Arrays.asList(FRONTEND_LOCALHOST, FRONTEND_STAGING));

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        System.out.println("-----------Inside CorsConfig-----------");
        return source;
    }


}
