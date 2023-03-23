package com.example.metacar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Return_CarDTO {
    private int reserveNum;
    private String id;
    private Date returnTime;
    private int useTime;
}
