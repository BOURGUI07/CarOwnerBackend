/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dto;

/**
 *
 * @author hp
 */
public class TotalValueOfOwnerCars {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public TotalValueOfOwnerCars(Integer id, String firstName, String lastName, double value) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.value = value;
    }
    private Integer id;
    private String firstName;
    private String lastName;
    private double value;
}
