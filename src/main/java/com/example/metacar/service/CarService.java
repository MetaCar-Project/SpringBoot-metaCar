package com.example.metacar.service;

import com.example.metacar.dto.Criteria;
import com.example.metacar.dto.Have_CarDTO;
import com.example.metacar.dto.Rental_CarDTO;

import java.util.List;

public interface CarService {
    public List<Have_CarDTO> getCarList();

    public List<Have_CarDTO> carWithPaginggetList(Criteria cri);

    public Have_CarDTO cardetail(String carNum);

    public Rental_CarDTO carMain(String id);

}
