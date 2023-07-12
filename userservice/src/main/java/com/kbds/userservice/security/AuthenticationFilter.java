package com.kbds.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbds.userservice.dto.UserDto;
import com.kbds.userservice.service.UserService;
import com.kbds.userservice.vo.RequestLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final UserService userService;
    private final Environment env;



    @Autowired
    public AuthenticationFilter(UserService userService, Environment env) {

        this.userService = userService;
        this.env = env;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        log.info("================== attemptAuthentication 호출");
        try {
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);
            log.info("========================== creds : "+creds.getUserId() + "  "+ creds.getPwd());
            log.info("========================== request : "+request.toString());

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUserId(),
                            creds.getPwd(),
                            new ArrayList<>()
                    )
            );
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        log.info("================== successfulAuthentication 호출");
        String userId = ((User)authResult.getPrincipal()).getUsername();
        log.info("========================== userId : " + userId);
        UserDto userDetails = userService.getUserDetailsByUserId(userId);
        log.info("========================== userDetails : "+userDetails.toString());
        String token = Jwts.builder()
                .setSubject(userDetails.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        log.info("========================== token : "+ token);
        response.addHeader("token", token);
        response.addHeader("userId", userDetails.getUserId());
        log.info("========================== response : "+response.toString()+" , "+response.getHeader(token) +" , "+ response.getHeader(userId));
    }
}
