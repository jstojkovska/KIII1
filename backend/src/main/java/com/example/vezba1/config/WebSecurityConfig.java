package com.example.vezba1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@Profile("test")
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    private final CustomUsernamePasswordAuthentication authentication;

    public WebSecurityConfig(CustomUsernamePasswordAuthentication authentication) {
        this.authentication = authentication;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(
                        corsConfigurationSource()));
//                .authorizeHttpRequests(requests -> requests.requestMatchers(
//                                "/api/accommodations",
//                                "/api/countries",
//                                "/api/hosts",
//                                "/api/reservations",
//                                "/api/user/login",
//                                "/api/user/register",
//                                "/swagger-ui/**",
//                                "/v3/api-docs/**",
//                                "/api/user/temp/add/{accommodationId}",
//                                "/api/user/temp",
//                                "/api/user/temp/confirm"
//                        ).permitAll()
//                        .anyRequest().hasRole("ADMIN"))
//                .formLogin((form) -> form.loginProcessingUrl(
//                        "/api/user/login"
//                ).permitAll().failureUrl("/api/user/login?error=BadCredentials").defaultSuccessUrl(
//                        "/swagger-ui/index.html", true
//                ))
//                .logout((logout) -> logout.logoutUrl("/api/user/logout")
//                        .clearAuthentication(true)
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .logoutSuccessUrl("/api/user/login"))
//                .exceptionHandling((ex) -> ex.accessDeniedPage("/access_denied"));
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
                AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authentication);
        return authenticationManagerBuilder.build();
    }
}
