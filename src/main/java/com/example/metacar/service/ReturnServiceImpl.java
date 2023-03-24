package com.example.metacar.service;

import com.example.metacar.dto.Rental_CarDTO;
import com.example.metacar.dto.Return_CarDTO;
import com.example.metacar.mapper.ReturnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReturnServiceImpl implements ReturnService{
    @Autowired
    private ReturnMapper mapper;

    @Transactional
    @Override
    public void returnCar(String id) {
        Rental_CarDTO rentalCar = mapper.getRental(id);
        int resnum = rentalCar.getReserveNum();
        String carNum = mapper.getCarnum(resnum);
        Return_CarDTO rc = new Return_CarDTO();
        rc.setId(id);
        rc.setReserveNum(resnum);
        rc.setUseTime(rentalCar.getUseTime());
        mapper.carReturn(rc);
        mapper.returnUpdate(carNum);
        mapper.carReturnox(resnum);
    }

}
