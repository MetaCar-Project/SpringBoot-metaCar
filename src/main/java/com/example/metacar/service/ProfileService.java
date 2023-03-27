package com.example.metacar.service;

import com.example.metacar.dto.Socar_MemberDTO;

public interface ProfileService {
    public Socar_MemberDTO detailUser(String id);
    public boolean updateUser(Socar_MemberDTO sm);
}
