package com.phisoft.bookstorekotlin.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

/**
 * Authentication filter that intercept every request for authentication
 * It checks the username and password for authentication
 */
public class JwtUsernamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtUsernamePasswordAuthFilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernamePasswordAuthRequest usernamePasswordAuthRequest = new ObjectMapper().readValue(request.getInputStream(), UsernamePasswordAuthRequest.class);
            Authentication authentication=new UsernamePasswordAuthenticationToken(usernamePasswordAuthRequest.username,
                    usernamePasswordAuthRequest.password);
            return authenticationManager.authenticate(authentication);
        }catch (IOException e){

            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token= Jwts.builder().setSubject(authResult.getName())
                .claim("authorities",authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(AuthenticationConstants.jwtTokenExpiration)))
                .signWith(Keys.hmacShaKeyFor(AuthenticationConstants.jwtSecretKey.getBytes()))
                .compact();
          response.addHeader(AuthenticationConstants.jwtHeader,AuthenticationConstants.jwtTokenPrefix+" "+token);

    }



}
