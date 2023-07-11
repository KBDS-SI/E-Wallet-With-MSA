package com.kbds.userservice.security;

import com.kbds.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity  {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env;
    private final ObjectPostProcessor<Object> objectPostProcessor;
    private static final String[] AUTH_WHITELIST = {
            "/actuator/**",
            "/hello",
            "/health-check",
            "/dd",
            "/join",
            "/login"
            };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(request -> request.disable());
        http.headers(request -> request.frameOptions(frameOptionsConfig -> request.disable()));
//        http.authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll());
        http.authorizeHttpRequests(request -> request.requestMatchers(AUTH_WHITELIST).permitAll()).addFilter(getAuthenticationFilter());
        http.authorizeHttpRequests(request -> request.requestMatchers(PathRequest.toH2Console()).permitAll()).addFilter(getAuthenticationFilter());

        return http.build();
    }
//
    @Autowired
    public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, Environment env, ObjectPostProcessor<Object> objectPostProcessor) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.env = env;
        this.objectPostProcessor = objectPostProcessor;
    }
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
        return auth.build();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, env);
        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);
        authenticationFilter.setAuthenticationManager(authenticationManager(builder));
        return authenticationFilter;
    }

}
