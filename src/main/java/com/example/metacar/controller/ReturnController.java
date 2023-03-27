package com.example.metacar.controller;

import com.example.metacar.dto.Return_CarDTO;
import com.example.metacar.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metaCar/*")
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    @PostMapping("/return")
    public ResponseEntity<String> returnCar(@RequestBody Return_CarDTO returnCarDTO) {
        returnCarDTO.getId();
        returnService.returnCar(returnCarDTO.getId());

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
