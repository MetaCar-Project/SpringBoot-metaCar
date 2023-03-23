package com.example.metacar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cancel_CarDTO {
    //예약번호
    private int reserveNum;
    private String id;
    //취소사유
    private String cancelWhy;
    //취소일자
    private Date cancelDate;
}
