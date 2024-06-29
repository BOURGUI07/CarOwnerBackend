/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dto;

import java.util.ArrayList;
import java.util.List;
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
public class OwnerDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<Integer> carsIDs = new ArrayList<>();
}
