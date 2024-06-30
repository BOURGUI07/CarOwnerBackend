/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    
    @Operation(summary="Get All Cars", description="Return a List of Cars")
    @ApiResponse(responseCode="200", description="Found All Cars Successfully")
    @GetMapping("/cars")
    public List<CarDTO> getCars(){
        return service.getAll();
    }
    
    @Operation(summary="Find Car By Id", description="Return a Single Car")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="Car Found Successfully"),
        @ApiResponse(responseCode="404", description="Car Not Found")})
    @GetMapping("/cars/{id}")
    public CarDTO getCar(@PathVariable Integer id){
        if(id<0){
            throw new RessourceNotFoundException("No Car Found for id: " + id);
        }
        return service.findCar(id);
    }
    
    @Operation(summary= "Create a New Car")
    @ApiResponse(responseCode="201", description="Car Created Successfully")
    @PostMapping("/cars")
    public CarDTO createCar(@Valid @RequestBody CarDTO x){
        return service.createCar(x);
    }
    
    @Operation(summary="Update a Car")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="Car Updated"),
        @ApiResponse(responseCode="404", description="Car Not Found")
    })
    @PutMapping("/cars/{id}")
    public CarDTO updateCar(@PathVariable Integer id,@Valid @RequestBody CarDTO x){
        if(id<0){
            throw new RessourceNotFoundException("No Car Found for id: " + id);
        }
        return service.updateCar(id, x);
    }
    
    @Operation(summary="Delete a Car")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="Car Deleted"),
        @ApiResponse(responseCode="404", description="Car Not Found")})
    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable Integer id){
        if(id<0){
            throw new RessourceNotFoundException("No Car Found for id: " + id);
        }
        service.deleteCar(id);
    }
    
    @Operation(summary="Find Cars By Brand")
    @GetMapping("/cars/findByBrand")
    public List<Car> findCarsByBrand(@RequestParam String brand){
        return service.getCarsByBrand(brand);
    }
    
    @Operation(summary="Find Cars By Color ")
    @GetMapping("/cars/findByColor")
    public List<Car> findCarsByColor(@RequestParam String color){
        return service.getCarsByColor(color);
    }
    
    @Operation(summary="Find Cars By Model Year")
    @GetMapping("/cars/findByYear")
    public List<Car> findCarsByYear(@RequestParam int year){
        return service.getCarsByYear(year);
    }
    
    @Operation(summary="Find Cars By Color or Brand")
    @GetMapping("/cars/findByColorOrBrand")
    public List<Car> findCarsByColorOrBrand(@RequestParam String color, @RequestParam String brand){
        return service.getCarsByColorOrBrand(color, brand);
    }
    
    @Operation(summary="Find Cars By Brand and Sort By Year")
    @GetMapping("/cars/findByBrandSortByYear")
    public List<Car> findCarsByBrandSortyear(@RequestParam String brand){
        return service.getCarsByBrandSortByYear(brand);
    }
    
    @Operation(summary="Count How Many Cars For Each Color")
    @GetMapping("/cars/countCarsEachColor")
    public List<Object[]> carsCountByColor(){
        return service.carsCountByColor();
    }
   
}
