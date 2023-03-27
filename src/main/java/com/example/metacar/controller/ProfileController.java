package com.example.metacar.controller;

import com.example.metacar.dto.Cancel_CarDTO;
import com.example.metacar.dto.Rental_CarDTO;
import com.example.metacar.dto.Socar_MemberDTO;
import com.example.metacar.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/metaCar/*")
@RestController
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping("/profile/{id}")
    public ResponseEntity<Socar_MemberDTO> profile(@PathVariable("id") String id){

        Socar_MemberDTO socar_memberDTO =  service.detailUser(id);
        System.out.println(socar_memberDTO);
        HttpStatus status = HttpStatus.OK;
        if (socar_memberDTO == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(socar_memberDTO,status);

    }
}
