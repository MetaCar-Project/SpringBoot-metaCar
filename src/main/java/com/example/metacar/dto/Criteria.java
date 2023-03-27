package com.example.metacar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

    private int pageNum;
    private int amount;

    private String carType[];
    private String carBig = "";
    private String carMiddle = "";
    private String carSmall = "";
    private String carSUV = "";

    private String keyword = "";
    private int zoneType;

    public Criteria() {
        this(1, 6);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public void setCarType(String newType[]) {
        carType = newType;
        for (int i = 0; i < carType.length; i++) {
            if (carType[i].equals("경차"))
                carSmall = "경차";
            if (carType[i].equals("중형"))
                carMiddle = "중형";
            if (carType[i].equals("대형"))
                carBig = "대형";
            if (carType[i].equals("SUV"))
                carSUV = "suv";
        }
    }

}
