package com.example.metacar.mapper;

import com.example.metacar.dto.Socar_MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileMapper {
    public Socar_MemberDTO userDetail(String id);
    public Socar_MemberDTO userDetail2(String id);
    public int userUpdate(Socar_MemberDTO sm);
}
