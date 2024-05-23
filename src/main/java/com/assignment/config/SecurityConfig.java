package com.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    public SecurityConfig(CorsFilter corsFilter, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.corsFilter = corsFilter;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable() // Enable CORS and disable CSRF
                .authorizeRequests()
                .antMatchers("/api/registration", "/api/login").permitAll()// Allow public access to auth endpoints
                .anyRequest().authenticated() // Secure other endpoints
                .and()
                .addFilterBefore(corsFilter, CorsFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, CorsFilter.class);
    }
}
