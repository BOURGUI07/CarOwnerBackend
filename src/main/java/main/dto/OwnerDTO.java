/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message="First Name is Mandatory")
    @Size(min=2, max=50, message="First Name Must be At least 2 characters and doesn't exceed 50")
    private String firstName;
    private String lastName;
    private List<Integer> carsIDs = new ArrayList<>();
}
