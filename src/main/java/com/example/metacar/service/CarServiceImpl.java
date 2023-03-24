package com.example.metacar.service;

import com.example.metacar.dto.Criteria;
import com.example.metacar.dto.Have_CarDTO;
import com.example.metacar.dto.Rental_CarDTO;
import com.example.metacar.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper mapper;

    @Override
    public List<Have_CarDTO> getCarList() {
        return mapper.cargetList();
    }

    @Override
    public List<Have_CarDTO> carWithPaginggetList(Criteria cri) {
        return mapper.carWithPaginggetList(cri);
    }

    @Override
    public Have_CarDTO cardetail(String carNum) {
        return mapper.cargetDetail(carNum);
    }

    @Override
    public Rental_CarDTO carMain(String id) {

        return mapper.mainCar(id);
    }
}
