package com.example.metacar.controller;

import com.example.metacar.dto.Cancel_CarDTO;
import com.example.metacar.dto.Rental_CarDTO;
import com.example.metacar.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/metaCar")
public class RentalController {
    @Autowired
    private RentalService service;

    @GetMapping("/getList")
    public Rental_CarDTO check(){
        return service.checkRental("123");
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<Map<String,Object>> cancelPage(@PathVariable("id")String id, Rental_CarDTO rc){

        Rental_CarDTO rentalData = service.checkRental(id);
        List<Cancel_CarDTO> cancelData = service.cancelGet(id);

        Map<String, Object> result = new HashMap<>();
        result.put("rentalGet", rentalData);
        result.put("cancel", cancelData);

        HttpStatus status = HttpStatus.OK;
        if (rentalData == null && cancelData == null) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancel(@RequestBody Cancel_CarDTO cc) {
        service.cancelCar(cc);


        if(cc == null) {
            System.out.println("제대로된 정보 입력해주셈");
        }

        return new ResponseEntity<>("success",HttpStatus.OK);
    }

}
