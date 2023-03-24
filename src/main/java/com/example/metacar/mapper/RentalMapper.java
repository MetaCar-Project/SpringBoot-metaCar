package com.example.metacar.mapper;

import com.example.metacar.dto.Cancel_CarDTO;
import com.example.metacar.dto.Have_CarDTO;
import com.example.metacar.dto.Rental_CarDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RentalMapper {
    public void carRental(Rental_CarDTO rc);

    public Have_CarDTO carGet(Have_CarDTO hc);

    public int carUpdate(String carNum);

    public void carCancel(Cancel_CarDTO cc);

    public int cancelUpdate(String carNum);

    public int carCancelox(int reserveNum);

    public String getCarnum(int reserveNum);

    public List<Cancel_CarDTO> getCancel(String id);

    public Rental_CarDTO getRental(String id);

    public Rental_CarDTO canRental(String carNum);
}
