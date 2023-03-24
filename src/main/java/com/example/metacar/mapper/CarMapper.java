package com.example.metacar.mapper;

import com.example.metacar.dto.Criteria;
import com.example.metacar.dto.Have_CarDTO;
import com.example.metacar.dto.Rental_CarDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {
    public List<Have_CarDTO> cargetList();
    public List<Have_CarDTO> carWithPaginggetList(Criteria cri);
    public Have_CarDTO cargetDetail(String carNum);
    public Rental_CarDTO mainCar (String id);

}
