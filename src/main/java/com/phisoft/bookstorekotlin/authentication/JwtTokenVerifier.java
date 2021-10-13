package com.phisoft.bookstorekotlin.authentication;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Authentication filter called once per request filter that intercept requests
 * For token verification
 */
public class JwtTokenVerifier  extends OncePerRequestFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String requestToken=request.getHeader(AuthenticationConstants.jwtHeader);
        if(requestToken==null||requestToken.isEmpty()||!requestToken.startsWith(AuthenticationConstants.jwtTokenPrefix)){
            request.setAttribute("empty","The token is empty");
            chain.doFilter(request,response);
            return;
        }

        String token=requestToken.replace(AuthenticationConstants.jwtTokenPrefix+" ","");
        try{
            Jws<Claims> claimsJws= Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(AuthenticationConstants.jwtSecretKey.getBytes()))
                    .parseClaimsJws(token);
            Claims body=claimsJws.getBody();
            String username=body.getSubject();
            List<Map<String,String>> mapList=(List<Map<String, String>>)body.get("authorities");
            List<SimpleGrantedAuthority>authorities=mapList
                    .stream()
                    .map(m->new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toList());
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,null,authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }catch (SignatureException ex){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid JWT Signature");
        }catch (MalformedJwtException ex){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid JWT Token");
        }catch(ExpiredJwtException ex){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Token has expired");
        }catch(UnsupportedJwtException ex){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unsupported JWT exception");
        }catch(IllegalArgumentException ex){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Jwt claims string is empty");
        }catch (Exception ex){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"You are not authorized");
        }
        chain.doFilter(request,response);
    }



}
