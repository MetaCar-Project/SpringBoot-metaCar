package com.example.metacar.controller;

import com.example.metacar.dto.Criteria;
import com.example.metacar.dto.Have_CarDTO;
import com.example.metacar.dto.PageDTO;
import com.example.metacar.dto.Rental_CarDTO;
import com.example.metacar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class  MainController {

    @Autowired
    private CarService carService;

    @GetMapping("/metaCar/main")
    public List<Have_CarDTO> main(Criteria cri) {
        return carService.carWithPaginggetList(cri);
    }

    @GetMapping("/metaCar/detail/{carNum}")
    public Have_CarDTO detail(@PathVariable("carNum") String carNum){
        return  carService.cardetail(carNum);
    }

    @PostMapping("/main")
    @ResponseBody
    public ResponseEntity<Rental_CarDTO> main(@RequestBody String id, HttpServletResponse response){
        String checkid=id.trim().substring(1).substring(0, id.length()-2);
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        return new ResponseEntity<Rental_CarDTO> (carService.carMain(checkid), HttpStatus.OK);
    }
}