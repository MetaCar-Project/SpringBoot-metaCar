package com.example.metacar.mapper;

import com.example.metacar.dto.Rental_CarDTO;
import com.example.metacar.dto.Return_CarDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReturnMapper {
    public void carReturn(Return_CarDTO rc);

    public int returnUpdate(String carNum);

    public int carReturnox(int reserveNum);

    public String getCarnum(int reserveNum);

    public Rental_CarDTO getRental(String id);
}
