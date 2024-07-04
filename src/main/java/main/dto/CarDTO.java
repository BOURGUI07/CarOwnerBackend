/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

/**
 *
 * @author hp
 */
@Value
public class CarDTO {

    public CarDTO(Integer id, String brand, String color, String registrationNumber, Integer modelYear, Double price, Integer ownerID) {
        this.id = id;
        this.brand = brand;
        this.color = color;
        this.registrationNumber = registrationNumber;
        this.modelYear = modelYear;
        this.price = price;
        this.ownerID = ownerID;
    }

    public Integer getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getOwnerID() {
        return ownerID;
    }
    private Integer id;
    @NotBlank(message="Brand Name is Mandatory")
    @Size(min=2, max=50, message="Brand Name Must be At least 2 characters and doesn't exceed 50")
    private String brand;
    private String color;
    private String registrationNumber;
    private Integer modelYear;
    private Double price;
    private Integer ownerID;
}
