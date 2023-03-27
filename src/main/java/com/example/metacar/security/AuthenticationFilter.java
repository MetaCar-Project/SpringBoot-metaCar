package com.example.metacar.security;

import com.example.metacar.dto.Socar_MemberDTO;
import com.example.metacar.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private UserMapper mapper;

    public AuthenticationFilter(AuthenticationManager authenticationManager,UserMapper mapper){
        super.setAuthenticationManager(authenticationManager);
        this.mapper = mapper;
        setFilterProcessesUrl("/metaCar/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try{
            System.out.println("attempAuthentication 호출");
            Socar_MemberDTO creds = new ObjectMapper().readValue(request.getInputStream(),
                    Socar_MemberDTO.class);
            Socar_MemberDTO sm = mapper.getUserByIdAndPassword(creds.getId());
            if(sm==null){
                throw new UsernameNotFoundException("아이디없다고");
            }
            System.out.println(sm);

            return getAuthenticationManager().authenticate(
                        new UsernamePasswordAuthenticationToken(
                            sm.getId(),
                            creds.getPassword(),
                            new ArrayList<>())
                        );
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String userName = ((User)authResult.getPrincipal()).getUsername();
        System.out.println(userName+"============================");


        String jwt = Jwts.builder()
                .setHeaderParam("type", "jwt")
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*60*24*365)))
                .signWith(SignatureAlgorithm.HS256, "hello")
                .compact();
        response.addHeader("token",jwt);
    }
}
