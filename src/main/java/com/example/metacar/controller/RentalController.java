package com.example.metacar.controller;

import com.example.metacar.dto.Cancel_CarDTO;
import com.example.metacar.dto.Have_CarDTO;
import com.example.metacar.dto.Rental_CarDTO;
import com.example.metacar.dto.Socar_MemberDTO;
import com.example.metacar.service.RentalService;
import com.example.metacar.service.UserService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/metaCar")
public class RentalController {
    @Autowired
    private RentalService service;
    @Autowired
    private UserService userService;

    @GetMapping("/getList")
    public Rental_CarDTO check() {
        return service.checkRental("123");
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<Map<String, Object>> cancelPage(@PathVariable("id") String id, Rental_CarDTO rc) {

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

        if (cc == null) {
            System.out.println("제대로된 정보 입력해주셈");
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/rental/{carNum}")
    @PreAuthorize("isAuthenticated()")
    public Map goRental(@PathVariable("carNum") String carNum, Principal principal) {
        String id = principal.getName();
        System.out.println("carNum = " + carNum);
        System.out.println(id);
        Socar_MemberDTO sm = userService.getUserByIdAndPassword(id);
        System.out.println(sm);
        Map<String, Object> map = new HashMap<>();
        Have_CarDTO hc = new Have_CarDTO();
        hc.setCarNum(carNum);
        Have_CarDTO have_carDTO = service.getCar(hc);
        map.put("car", have_carDTO);
        map.put("user", sm);
        if (have_carDTO == null) {
            throw new RuntimeException("no Car");
        }
        return map;
    }

    @PostMapping("/rental")
    public ResponseEntity rentalCar(@RequestBody Rental_CarDTO rc) {
        System.out.println("=====retnal======" + rc);
        if (service.checkReserve(rc.getId()) || service.canReserve(rc.getCarNum())) {
            System.out.println("예약차량 있음");
            throw new RuntimeException("예약차량 있음");
        }
        service.rentalCar(rc);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
