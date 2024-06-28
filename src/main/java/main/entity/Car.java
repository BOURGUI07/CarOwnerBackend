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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author hp
 */
@Entity
@Table(name="car")
@Getter
@Setter
@NoArgsConstructor
@Builder(toBuilder=true)
public class Car {
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
    private int modelYear;
    @Column(name="price")
    private double price;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private Owner owner;
}
