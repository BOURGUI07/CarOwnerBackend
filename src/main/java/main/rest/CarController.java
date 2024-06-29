/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.rest;

import java.util.List;
import main.dto.CarDTO;
import main.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    public CarController(CarService service) {
        this.service = service;
    }
    private CarService service;
    
    @GetMapping("/cars")
    public List<CarDTO> getCars(){
        return service.getAll();
    }
    
    @GetMapping("/cars/{id}")
    public CarDTO getCar(@PathVariable Integer id){
        return service.findCar(id);
    }
    
    @PostMapping("/cars")
    public CarDTO createCar(@RequestBody CarDTO x){
        return service.createCar(x);
    }
    
    @PutMapping("/cars/{id}")
    public CarDTO updateCar(@PathVariable Integer id, @RequestBody CarDTO x){
        return service.updateCar(id, x);
    }
    
    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Integer id){
        service.deleteCar(id);
    }
}
