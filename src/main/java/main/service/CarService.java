/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import main.dto.CarDTO;
import main.entity.Car;
import main.mapper.CarMapper;
import main.repo.CarRepo;
import main.repo.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class CarService {
    @Autowired
    public CarService(CarRepo repo, OwnerRepo repo1, CarMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
        this.repo1=repo1;
    }
    @PersistenceContext
    private EntityManager em;
    
    
    private CarRepo repo;
    private CarMapper mapper;
    private OwnerRepo repo1;
    
    public CarDTO findCar(Integer id){
        var c = repo.findById(id).orElse(null);
        return mapper.toDTO(c);
    }
    
    @Transactional
    public CarDTO createCar(CarDTO x){
        var car = mapper.toCar(x);
        var savedCar = repo.save(car);
        return mapper.toDTO(savedCar);
    }
    
    @Transactional
    public CarDTO updateCar(Integer id, CarDTO x){
        var car = repo.findById(id).orElse(null);
        car.setBrand(x.getBrand());
        car.setColor(x.getColor());
        car.setModelYear(x.getModelYear());
        car.setPrice(x.getPrice());
        car.setRegistrationNumber(x.getRegistrationNumber());
        if(x.getOwnerID()!=null){
            car.setOwner(repo1.findById(x.getOwnerID()).orElse(null));
        } 
        var savedCar = repo.save(car);
        return mapper.toDTO(savedCar);
    }
    
    public List<CarDTO> getAll(){
        return repo.findAll().stream().map(x -> mapper.toDTO(x)).collect(Collectors.toList());
    }
    
    @Transactional
    public void deleteCar(Integer id){
        var c = repo.findById(id).orElse(null);
        if(c!=null){
            repo.deleteById(id);
        }
    }
    
    public List<Car> getCarsByBrand(String brand){
        var q = "SELECT * FROM car WHERE brand= :brand";
        return em.createNativeQuery(q, Car.class).setParameter("brand", brand).getResultList();
    }
    
    public List<Car> getCarsByYear(int year){
        var q = "SELECT * FROM car WHERE model_year= :x";
        return em.createNativeQuery(q, Car.class).setParameter("x", year).getResultList();
    }
    
    public List<Car> getCarsByColor(String color){
        var q = "SELECT * FROM car WHERE color= :x";
        return em.createNativeQuery(q, Car.class).setParameter("x", color).getResultList();
    }
    
    public List<Car> getCarsByColorOrBrand(String color, String brand){
        var q = "SELECT * FROM car WHERE color= :color OR brand= :brand";
        return em.createNativeQuery(q, Car.class).setParameter("brand", brand).setParameter("color",color).getResultList();
    }
    
    public List<Car> getCarsByBrandSortByYear(String brand){
        var q = "SELECT * FROM car WHERE brand= :brand ORDER BY model_year";
        return em.createNativeQuery(q, Car.class).setParameter("brand", brand).getResultList();
    }
}
