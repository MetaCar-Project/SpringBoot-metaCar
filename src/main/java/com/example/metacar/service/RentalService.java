package com.example.metacar.service;

import com.example.metacar.dto.Cancel_CarDTO;
import com.example.metacar.dto.Have_CarDTO;
import com.example.metacar.dto.Rental_CarDTO;

import java.util.List;

public interface RentalService {
    public void rentalCar(Rental_CarDTO rc);
    public Have_CarDTO getCar(Have_CarDTO hc);
    public void cancelCar(Cancel_CarDTO cc);
    public List<Cancel_CarDTO> cancelGet(String id);
    public boolean checkReserve(String id);
    public Rental_CarDTO checkRental(String id);
    public boolean canReserve (String carNum);
}
