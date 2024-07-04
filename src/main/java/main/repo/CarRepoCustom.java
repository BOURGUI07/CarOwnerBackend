/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package main.repo;

import java.util.List;
import main.dto.CarDTO;

/**
 *
 * @author hp
 */
public interface CarRepoCustom {
    List<CarDTO> findCarsByCriteria(String brand, String color, Integer minYear, Integer maxYear, Double minPrice, Double maxPrice);
}
