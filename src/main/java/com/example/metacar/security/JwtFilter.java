package com.example.metacar.security;

import com.example.metacar.dto.Socar_MemberDTO;
import com.example.metacar.mapper.UserMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JwtFilter extends BasicAuthenticationFilter {

    private UserMapper mapper;

    public JwtFilter(AuthenticationManager authenticationManager, UserMapper mapper) {
        super(authenticationManager);
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request2, HttpServletResponse response2, FilterChain filterChain) throws IOException, ServletException  {
        System.out.println("필터 호출");

        HttpServletRequest request = (HttpServletRequest) request2;
        HttpServletResponse response = (HttpServletResponse) response2;

        if(request.getHeader("AUTHORIZATION")==null){
            onError(response, "UNAUTHORIZATION");
            return;
        }else{
            String authorizationHeader = request.getHeader("AUTHORIZATION");
            System.out.println("AUTHORIZATION : " + authorizationHeader);
            String jwt = authorizationHeader.replace("Bearer", "");

            if(!isJwrValid(jwt)){
                onError(response, "UNAUTHORIZATION");
                return;
            }

            String name = Jwts.parser().setSigningKey("hello").parseClaimsJws(jwt).getBody().getSubject();
            Socar_MemberDTO sm = mapper.getUserByIdAndPassword(name);
            UserDetails user = User.builder()
                    .username(sm.getId())
                    .password(sm.getPassword())
                    .authorities(sm.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                            .collect(Collectors.toList()))
                    .build();
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    sm.getPassword(),
                    sm.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                            .collect(Collectors.toList()));
            System.out.println(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println();


        }

        filterChain.doFilter(request2,response2);
    }



    private boolean isJwrValid(String jwt){
        boolean returnValue = true;
        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey("hello").parseClaimsJws(jwt).getBody().getSubject();
            System.out.println("subject : " + subject);
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
