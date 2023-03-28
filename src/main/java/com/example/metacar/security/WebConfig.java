package com.example.metacar.security;

import com.example.metacar.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    private UserMapper mapper;
    private CustomUserDetailService customUserDetailService;

    public WebConfig(UserMapper mapper, CustomUserDetailService customUserDetailService) {
        this.mapper = mapper;
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/metaCar/checkid")
                .antMatchers("/metaCar/main")
                .antMatchers("/metaCar/page")
                .antMatchers("/metaCar/signup");// 이 요청들에 대해서는 spring security 필터 체인을 적용하지 않겠다
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("metaCar/signup").permitAll()
                .antMatchers("metaCar/main").permitAll()
                .antMatchers("/metaCar/main").permitAll()
                .antMatchers("/metaCar/page").permitAll()
                .antMatchers("metaCar/page").permitAll()
                .antMatchers("metaCar/login").permitAll()
                .antMatchers("/metaCar/login").permitAll()
                .antMatchers("metaCar/checkid").permitAll()
                .and()
                .addFilter(authenticationFilter())
                .addFilter(JwtFilter()).authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout();

        // .and()
        // .addFilter(authenticationFilter())
        // .addFilterAfter(JwtFilter(),AuthenticationFilter.class);
    }

    private JwtFilter JwtFilter() throws Exception {
        return new JwtFilter(authenticationManager(), mapper);
    }

    private AuthenticationFilter authenticationFilter() throws Exception {
        return new AuthenticationFilter(authenticationManager(), mapper);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());

    }


}
