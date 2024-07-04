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
import java.util.Locale;
import main.dto.CarDTO;
import main.dto.CountCarsByColor;
import main.entity.Car;
import main.handler.RessourceNotFoundException;
import main.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@Validated
public class CarController {
    @Autowired
    public CarController(CarService service, MessageSource source) {
        this.service = service;
        this.source = source;
    }
    private final CarService service;
    private final MessageSource source;
    
    @Operation(summary="Get All Cars", description="Return a List of Cars")
    @ApiResponse(responseCode="200", description="Found All Cars Successfully")
    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getCars(){
        return ResponseEntity.ok(service.getAll());
    }
    
    @Operation(summary="Get Cars By Some Criteria")
    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getCarBy(
            @RequestParam String brand,
            @RequestParam String color,
            @RequestParam Integer minYear,
            @RequestParam Integer maxYear,
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice
    ){
        return ResponseEntity.ok(service.findCarsByCriteria(brand, color, minYear, maxYear, minPrice, maxPrice));
    }
    
    @Operation(summary="Find Car By Id", description="Return a Single Car")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="Car Found Successfully"),
        @ApiResponse(responseCode="404", description="Car Not Found")})
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable Integer id, Locale locale){
        if(id<0){
            throw new RessourceNotFoundException(source.getMessage("error.car.notfound", new Object[]{id}, locale));
        }
        var car = service.findCar(id);
        if(car==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(car);
    }
    
    @Operation(summary= "Create a New Car")
    @ApiResponse(responseCode="201", description="Car Created Successfully")
    @PostMapping("/cars")
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO x){
        var createdCar = service.createCar(x);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }
    
    @Operation(summary="Update a Car")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="Car Updated"),
        @ApiResponse(responseCode="404", description="Car Not Found")
    })
    @PutMapping("/cars/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Integer id,@Valid @RequestBody CarDTO x, Locale locale){
        if(id<0){
            throw new RessourceNotFoundException(source.getMessage("error.car.notfound", new Object[]{id}, locale));
        }
        return ResponseEntity.ok(service.updateCar(id, x));
    }
    
    @Operation(summary="Delete a Car")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="Car Deleted"),
        @ApiResponse(responseCode="404", description="Car Not Found")})
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Integer id, Locale locale){
        if(id<0){
            throw new RessourceNotFoundException(source.getMessage("error.car.notfound", new Object[]{id}, locale));
        }
        service.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary="Find Cars By Brand")
    @GetMapping("/cars/findByBrand")
    public List<CarDTO> findCarsByBrand(@RequestParam String brand){
        return service.getCarsByBrand(brand);
    }
    
    @Operation(summary="Find Cars By Color ")
    @GetMapping("/cars/findByColor")
    public List<CarDTO> findCarsByColor(@RequestParam String color){
        return service.getCarsByColor(color);
    }
    
    @Operation(summary="Find Cars By Model Year")
    @GetMapping("/cars/findByYear")
    public List<CarDTO> findCarsByYear(@RequestParam Integer year){
        return service.getCarsByYear(year);
    }
    
    @Operation(summary="Find Cars By Color or Brand")
    @GetMapping("/cars/findByColorOrBrand")
    public List<CarDTO> findCarsByColorOrBrand(@RequestParam String color, @RequestParam String brand){
        return service.getCarsByColorOrBrand(color, brand);
    }
    
    @Operation(summary="Find Cars By Brand and Sort By Year")
    @GetMapping("/cars/findByBrandSortByYear")
    public List<CarDTO> findCarsByBrandSortyear(@RequestParam String brand){
        return service.getCarsByBrandSortByYear(brand);
    }
    
    @Operation(summary="Count How Many Cars For Each Color")
    @GetMapping("/cars/countCarsEachColor")
    public List<CountCarsByColor> carsCountByColor(){
        return service.carsCountByColor();
    }
   
}
