/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.mapper;

import main.dto.CarDTO;
import main.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author hp
 */
@Mapper
public interface CarMapper {
    @Autowired
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
    CarDTO toDTO(Car c);
    Car toCar(CarDTO x);
}
