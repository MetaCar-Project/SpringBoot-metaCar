package com.example.metacar.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request2, ServletResponse response2, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터 호출");

        HttpServletRequest request = (HttpServletRequest) request2;
        HttpServletResponse response = (HttpServletResponse) response2;

        if(request.getHeader("AUTHORIZATION")==null){
            onError(response, "UNAUTHORIZATION");
        }else{
            String authorizationHeader = request.getHeader("AUTHORIZATION");
            System.out.println("AUTHORIZATION : " + authorizationHeader);
            String jwt = authorizationHeader.replace("Bearer", "");

            if(!isJwrValid(jwt)){
                onError(response, "UNAUTHORIZATION");
            }
        }
        filterChain.doFilter(request2,response2);
    }

    private boolean isJwrValid(String jwt){
        boolean returnValue = true;
        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey("hello").parseClaimsJws(jwt).getBody().getSubject();
        }catch (Exception e){
            returnValue=false;
        }
        if(subject==null || subject.isEmpty()){
            returnValue = false;
        }
        return returnValue;
    }

    private void onError(HttpServletResponse response, String httpStatus) throws IOException{
        response.addHeader("error", httpStatus);
        response.sendError(HttpServletResponse.SC_FORBIDDEN,httpStatus);
    }
}
