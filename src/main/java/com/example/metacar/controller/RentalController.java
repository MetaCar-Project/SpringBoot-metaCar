package com.example.metacar.controller;

import com.example.metacar.dto.Have_CarDTO;
import com.example.metacar.dto.Rental_CarDTO;
import com.example.metacar.service.RentalService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/metaCar")
public class RentalController {
    @Autowired
    private RentalService service;

    @GetMapping("/rental")
    @PreAuthorize("isAuthenticated()")
    public Have_CarDTO goRental(@RequestParam("carNum") String carNum, Principal principal){
        String id = principal.getName();
        System.out.println("carNum = " + carNum);
        System.out.println(id);
        Have_CarDTO hc = new Have_CarDTO();
        hc.setCarNum(carNum);
        Have_CarDTO have_carDTO = service.getCar(hc);
        if(have_carDTO==null){
            throw new RuntimeException("no Car");
        }
        return have_carDTO;
    }


    @PostMapping("/rental")
    public ResponseEntity rentalCar(@RequestBody Rental_CarDTO rc){
        if(service.checkReserve(rc.getId()) || service.canReserve(rc.getCarNum())) {
            System.out.println("예약차량 있음");
            throw new RuntimeException("예약차량 있음");
        }
        service.rentalCar(rc);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
