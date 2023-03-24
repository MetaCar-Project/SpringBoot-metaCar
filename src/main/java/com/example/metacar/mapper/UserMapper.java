package com.example.metacar.mapper;

import com.example.metacar.dto.Socar_MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public void userCreate(Socar_MemberDTO sm);
    public void userRole(@Param("id") String id);
    public Socar_MemberDTO getUserByIdAndPassword(@Param("id") String id);
    public Socar_MemberDTO idGet(@Param("id") String id);
}
