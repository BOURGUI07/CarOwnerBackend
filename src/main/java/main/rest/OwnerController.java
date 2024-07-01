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
import main.dto.OwnerDTO;
import main.entity.Owner;
import main.handler.RessourceNotFoundException;
import main.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class OwnerController {
    @Autowired
    public OwnerController(OwnerService service) {
        this.service = service;
    }
    private OwnerService service;
    
    @PostMapping("/owners")
    @Operation(summary= "Create a New Owner")
    @ApiResponse(responseCode="201", description="Owner Created Successfully")
    public ResponseEntity<OwnerDTO> createOwner(@Valid @RequestBody OwnerDTO x){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createOwner(x));
    }
    
    @Operation(summary="Update an Owner")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="Owner Updated"),
        @ApiResponse(responseCode="404", description="Owner Not Found")
    })
    @PutMapping("/owners/{id}")
    public ResponseEntity<OwnerDTO> updateOwner(@PathVariable Integer id,@Valid @RequestBody OwnerDTO x){
        if(id<0){
            throw new RessourceNotFoundException("No Owner Found for id: " + id);
        }
        return ResponseEntity.ok(service.updateOwner(id, x));
    }
    
    @Operation(summary="Get All Owners", description="Return a List of Owners")
    @ApiResponse(responseCode="200", description="Found All Owners Successfully")
    @GetMapping("/owners")
    public ResponseEntity<List<OwnerDTO>> getOwners(){
        return ResponseEntity.ok(service.getAll());
    }
    
    @GetMapping("/owners/{id}")
    @Operation(summary="Find Owner By Id", description="Return a Single Owner")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="Owner Found Successfully"),
        @ApiResponse(responseCode="404", description="Owner Not Found")})
    public ResponseEntity<OwnerDTO> getOwner(@PathVariable Integer id){
        if(id<0){
            throw new RessourceNotFoundException("No Owner Found for id: " + id);
        }
        var owner = service.findOwner(id);
        if(owner==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
        return ResponseEntity.ok(owner);
    }
    
    @Operation(summary="Delete an Owner")
    @ApiResponses(value={
        @ApiResponse(responseCode="200", description="Owner Deleted"),
        @ApiResponse(responseCode="404", description="Owner Not Found")})
    @DeleteMapping("/owners/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Integer id){
        if(id<0){
            throw new RessourceNotFoundException("No Owner Found for id: " + id);
        }
        service.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @Operation(summary="Find Owner By First Name")
    @GetMapping("/owners/findByName")
    public Owner findOwnerByName(@RequestParam String name){
        return service.findOwnerByFirstName(name);
    }
    
    @Operation(summary="Owners Who Have More Than One Car")
    @GetMapping("/owners/moreThanOneCar")
    public List<Object[]> ownersWhoHaveMoreThanOneCar(){
        return service.ownersWhoHaveMoreThanOneCar();
    }
    
    @Operation(summary="Most Expensive Car for Each Owner")
    @GetMapping("/owners/mostExpensiveByOwner")
    public List<Object[]> mostExpensiveCarByOwner(){
        return service.mostExpensiveCarByOwner();
    }
    
    @Operation(summary="Average Car Price for Each Owner")
    @GetMapping("/owners/avgPriceByOwner")
    public List<Object[]> avgPriceByOwner(){
        return service.avgPriceByOwner();
    }
    
    @Operation(summary="Owners of Input Brand")
    @GetMapping("/owners/ownersOfBrand")
    public List<Object[]> ownersOfBrand(@RequestParam String brand){
        return service.ownersOfBrand(brand);
    }
    
    @Operation(summary="Total Value of Cars for Each Owner")
    @GetMapping("/owners/totalPriceEachOwner")
    public List<Object[]> totalValueOfOwnerCars(){
        return service.totalValueOfOwnerCars();
    }
    
    @Operation(summary="Owners Whose Car's Model Year is Less Than Input Year")
    @GetMapping("/owners/ownersOfYearLess")
    public List<Object[]> ownersOfCarsLessThanYear(@RequestParam int year){
        return service.ownersOfCarsLessThanYear(year);
    }
    
    @Operation(summary="Owners With No Car")
    @GetMapping("/owners/withNoCar")
    public List<Owner> ownersWithNoCars(){
        return service.ownersWithNoCars();
    }
    
}
