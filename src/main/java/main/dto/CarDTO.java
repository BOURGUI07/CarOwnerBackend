/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dto;

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
    private String brand;
    private String color;
    private String registrationNumber;
    private int modelYear;
    private double price;
    private Integer ownerID;
}
