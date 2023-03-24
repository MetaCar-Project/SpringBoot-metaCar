package com.example.metacar.service;

import com.example.metacar.dto.Socar_MemberDTO;
import com.example.metacar.mapper.ProfileMapper;
import com.example.metacar.mapper.RentalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private ProfileMapper mapper;
    @Autowired
    private RentalMapper mapper2;

    @Override
    public Socar_MemberDTO detailUser(String id) {

        return mapper2.getRental(id)==null? mapper.userDetail2(id) : mapper.userDetail(id);
    }

    @Override
    public boolean updateUser(Socar_MemberDTO sm) {
        if(mapper.userUpdate(sm)==1) {
            return true;
        }
        else {
            return false;
        }
    }


}
