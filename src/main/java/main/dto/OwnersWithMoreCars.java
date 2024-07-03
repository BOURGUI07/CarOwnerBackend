/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dto;

/**
 *
 * @author hp
 */
public class OwnersWithMoreCars {

    public OwnersWithMoreCars(Integer id, String firstName, String lastName, int carCount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.carCount = carCount;
    }

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

    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }
    private Integer id;
    private String firstName;
    private String lastName;
    private int carCount;
}
