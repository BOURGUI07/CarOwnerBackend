/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.rest;

import jakarta.validation.Valid;
import java.util.List;
import main.dto.CarDTO;
import main.entity.Car;
import main.handler.RessourceNotFoundException;
import main.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        if(id<0){
            throw new RessourceNotFoundException("No Car Found for id: " + id);
        }
        return service.findCar(id);
    }
    
    @PostMapping("/cars")
    public CarDTO createCar(@Valid @RequestBody CarDTO x){
        return service.createCar(x);
    }
    
    @PutMapping("/cars/{id}")
    public CarDTO updateCar(@PathVariable Integer id,@Valid @RequestBody CarDTO x){
        if(id<0){
            throw new RessourceNotFoundException("No Car Found for id: " + id);
        }
        return service.updateCar(id, x);
    }
    
    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Integer id){
        if(id<0){
            throw new RessourceNotFoundException("No Car Found for id: " + id);
        }
        service.deleteCar(id);
    }
    
    @GetMapping("/cars/findByBrand")
    public List<Car> findCarsByBrand(@RequestParam String brand){
        return service.getCarsByBrand(brand);
    }
    
    @GetMapping("/cars/findByColor")
    public List<Car> findCarsByColor(@RequestParam String color){
        return service.getCarsByColor(color);
    }
    
    @GetMapping("/cars/findByYear")
    public List<Car> findCarsByYear(@RequestParam int year){
        return service.getCarsByYear(year);
    }
    
    @GetMapping("/cars/findByColorOrBrand")
    public List<Car> findCarsByColorOrBrand(@RequestParam String color, @RequestParam String brand){
        return service.getCarsByColorOrBrand(color, brand);
    }
    
    @GetMapping("/cars/findByBrandSortByYear")
    public List<Car> findCarsByBrandSortyear(@RequestParam String brand){
        return service.getCarsByBrandSortByYear(brand);
    }
    
    @GetMapping("/cars/countCarsEachColor")
    public List<Object[]> carsCountByColor(){
        return service.carsCountByColor();
    }
   
}
