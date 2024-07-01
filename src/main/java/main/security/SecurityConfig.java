/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author hp
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    public SecurityConfig(UserDetailsServiceImpl service) {
        this.service = service;
    }
    private final UserDetailsServiceImpl service;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(HttpMethod.GET, "/api/owners").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/owenrs/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/owners").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/owners/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/owners/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/cars").hasAnyRole("GUEST", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/cars/**").hasAnyRole("GUEST", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/cars").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/cars/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/cars/**").hasAnyRole("ADMIN")
        );
        
        http.httpBasic(Customizer.withDefaults());
        http.csrf(x -> x.disable());
        return http.build();
    }
    
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
