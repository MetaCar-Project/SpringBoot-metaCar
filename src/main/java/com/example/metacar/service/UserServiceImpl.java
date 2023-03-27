package com.example.metacar.service;

import com.example.metacar.dto.Socar_MemberDTO;
import com.example.metacar.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    //private PasswordEncoder pe;


    @Override
    @Transactional
    public void createUser(Socar_MemberDTO sm) {
        String pw = sm.getPassword();
        //sm.setPassword(pe.encode(pw));
        mapper.userCreate(sm);
        mapper.userRole(sm.getId());
    }


    @Override
    public boolean getId(String id) {
        // TODO Auto-generated method stub
        return mapper.idGet(id)==null? false : true;
    }

    @Override
    public Socar_MemberDTO getUserByIdAndPassword(String id) {
        return mapper.getUserByIdAndPassword(id);
    }

}
