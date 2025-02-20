/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.auditing.Auditable;

/**
 *
 * @author hp
 */
@Entity
@Table(name="car")
@Getter
@Setter
@NoArgsConstructor
public class Car extends Auditable{

    public Integer getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public Double getPrice() {
        return price;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="brand")
    private String brand;
    @Column(name="color")
    private String color;
    @Column(name="registration")
    private String registrationNumber;
    @Column(name="model_year")
    private Integer modelYear;
    @Column(name="price")
    private Double price;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private Owner owner;
}
