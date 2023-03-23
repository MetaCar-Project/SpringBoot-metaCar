package com.example.metacar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Have_CarDTO {
    //차번호
    private String carNum;
    //쏘카존번호
    private int sczoneNum;
    //차량구분 (suv,승용차등등)
    private String carKind;
    //차량메이커 (현대,기아)
    private String carMaking;
    //차량 모델(아이오닉, 투싼)
    private String carModel;
    //연료 (휘발유, 경유)
    private String carGas;
    //연비
    private String carEff;
    //대여여부 (o,x)
    private String reserveNow;



    private DistanceDTO distanceDto;
    private Zone_CarDTO zoneCar;
}
