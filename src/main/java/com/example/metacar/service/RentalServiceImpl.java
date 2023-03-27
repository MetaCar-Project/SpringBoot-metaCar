package com.example.metacar.service;

import com.example.metacar.dto.Cancel_CarDTO;
import com.example.metacar.dto.Have_CarDTO;
import com.example.metacar.dto.Rental_CarDTO;
import com.example.metacar.mapper.RentalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService{

    @Autowired
    private RentalMapper mapper;

    @Transactional
    @Override
    public void rentalCar(Rental_CarDTO rc){

        mapper.carUpdate(rc.getCarNum());
        mapper.carRental(rc);
    }

    @Override
    public Have_CarDTO getCar(Have_CarDTO hc) {
        return mapper.carGet(hc);
    }

    @Override
    @Transactional
    public void cancelCar(Cancel_CarDTO cc) {
        Rental_CarDTO rc = mapper.getRental(cc.getId());
        int resnum = rc.getReserveNum();
        String carNum = mapper.getCarnum(resnum);
        cc.setReserveNum(resnum);
        mapper.carCancel(cc);
        mapper.cancelUpdate(carNum);
        mapper.carCancelox(resnum);
    }

    @Override
    public List<Cancel_CarDTO> cancelGet(String id) {

        return mapper.getCancel(id);
    }


    @Override
    public boolean checkReserve(String id) {

        return mapper.getRental(id) == null? false : true;
    }

    @Override
    public Rental_CarDTO checkRental(String id) {

        return mapper.getRental(id);

    }

    @Override
    public boolean canReserve(String carNum) {
        return mapper.canRental(carNum) == null? false : true;
    }

}
