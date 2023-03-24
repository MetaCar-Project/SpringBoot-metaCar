package com.example.metacar.service;

import com.example.metacar.dto.Cancel_CarDTO;
import com.example.metacar.dto.Have_CarDTO;
import com.example.metacar.dto.Rental_CarDTO;

import java.util.List;

public interface ReturnService {
    public void returnCar(String id);
}
