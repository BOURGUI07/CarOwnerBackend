/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.mapper;

import main.dto.CarDTO;
import main.entity.Car;
import main.repo.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class CarMapper {
    @Autowired
    private OwnerRepo repo;
    public Car toCar(CarDTO x){
        var car = new Car();
        car.setBrand(x.getBrand());
        car.setColor(x.getColor());
        car.setModelYear(x.getModelYear());
        car.setPrice(x.getPrice());
        car.setRegistrationNumber(x.getRegistrationNumber());
        if(x.getOwnerID()!=null){
            car.setOwner(repo.findById(x.getOwnerID()).orElse(null));
        }
        return car;
    }
    
    public CarDTO toDTO(Car c){
        var x = new CarDTO();
        x.setId(c.getId());
        x.setBrand(c.getBrand());
        x.setColor(c.getColor());
        x.setModelYear(c.getModelYear());
        if(c.getOwner()!=null){
            x.setOwnerID(c.getOwner().getId());
        }
        x.setPrice(c.getPrice());
        x.setRegistrationNumber(c.getRegistrationNumber());
        return x;
    }
}
