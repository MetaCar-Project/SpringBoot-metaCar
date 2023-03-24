package com.example.metacar.service;

import com.example.metacar.dto.Socar_MemberDTO;

public interface UserService {
    public void createUser(Socar_MemberDTO sm);

    public boolean getId(String id);
}
