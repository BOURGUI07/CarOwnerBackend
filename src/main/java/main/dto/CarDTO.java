/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author hp
 */
@Getter
@Setter
@NoArgsConstructor
public class CarDTO {
    private Integer id;
    @NotBlank(message="Brand Name is Mandatory")
    @Size(min=2, max=50, message="Brand Name Must be At least 2 characters and doesn't exceed 50")
    private String brand;
    private String color;
    private String registrationNumber;
    private int modelYear;
    private double price;
    private Integer ownerID;
}
