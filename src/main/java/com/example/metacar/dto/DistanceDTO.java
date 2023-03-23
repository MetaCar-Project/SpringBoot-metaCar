package com.example.metacar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistanceDTO {
    private String carkind;
    //가격
    private int howmuch;
}
