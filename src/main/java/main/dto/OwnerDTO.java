/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 *
 * @author hp
 */
@Value
public class OwnerDTO {

    public OwnerDTO(Integer id, String firstName, String lastName, List<Integer> carsIDs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.carsIDs = carsIDs;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Integer> getCarsIDs() {
        return carsIDs;
    }
    private Integer id;
    @NotBlank(message="First Name is Mandatory")
    @Size(min=2, max=50, message="First Name Must be At least 2 characters and doesn't exceed 50")
    private String firstName;
    private String lastName;
    private List<Integer> carsIDs;
}
