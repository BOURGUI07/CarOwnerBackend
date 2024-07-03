/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.dto;

/**
 *
 * @author hp
 */
public class CountCarsByColor {

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public CountCarsByColor(String color, int carCount) {
        this.color = color;
        this.carCount = carCount;
    }
    private String color;
    private int carCount;
}
