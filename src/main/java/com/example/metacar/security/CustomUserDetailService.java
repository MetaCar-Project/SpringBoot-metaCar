package com.example.metacar.security;

import com.example.metacar.dto.Socar_MemberDTO;
import com.example.metacar.dto.Socar_Member_AuthDTO;
import com.example.metacar.mapper.UserMapper;
import com.example.metacar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Socar_MemberDTO member = mapper.getUserByIdAndPassword(username);
        if(member == null){
            throw new UsernameNotFoundException("id : " + username + " is not found");
        }
        String id = member.getId();
        String pw = member.getPassword();
        List<Socar_Member_AuthDTO> roles = member.getRoles();
        return User.builder()
                .username(id)
                .password(pw)
                .authorities(member.getRoles().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                        .collect(Collectors.toList()))
                .build();
    }
}
