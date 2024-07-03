/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dto;

/**
 *
 * @author hp
 */
public class MostExpensiveCarByOwner {

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

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public MostExpensiveCarByOwner(Integer id, String firstName, String lastName, double maxPrice) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maxPrice = maxPrice;
    }
    private Integer id;
    private String firstName;
    private String lastName;
    private double maxPrice;
}
