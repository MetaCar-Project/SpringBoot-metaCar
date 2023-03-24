package com.example.metacar.controller;

import com.example.metacar.dto.Rental_CarDTO;
import com.example.metacar.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/metaCar")
public class RentalController {
    @Autowired
    private RentalService service;

    @GetMapping("/getList")
    public Rental_CarDTO check(){
        return service.checkRental("kosa12");
    }

}
