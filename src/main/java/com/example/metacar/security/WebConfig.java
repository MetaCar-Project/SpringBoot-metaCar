package com.example.metacar.security;

import com.example.metacar.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    private UserMapper mapper;
    private CustomUserDetailService customUserDetailService;

    public WebConfig(UserMapper mapper, CustomUserDetailService customUserDetailService){
        this.mapper=mapper;
        this.customUserDetailService=customUserDetailService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").authenticated()
                .and()
                    .formLogin()
                .and()
                    .logout();

        http.authorizeRequests().antMatchers("/**")
                .hasIpAddress("127.0.0.1")
                .and()
                .addFilter(authenticationFilter())
                .addFilterAfter(JwtFilter(),AuthenticationFilter.class);
    }

    private JwtFilter JwtFilter(){
        return new JwtFilter();
    }
    private AuthenticationFilter authenticationFilter() throws Exception {
        return new AuthenticationFilter(authenticationManager() ,mapper);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());

    }


}
