package com.example.metacar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Socar_MemberDTO {
    private String id;
    private String password;
    private String phone;
    private String name;
    private String regNum;
    private int totalDistance;


    private List<Socar_Member_AuthDTO> roles;
    private Rental_CarDTO rentalCar;
}
