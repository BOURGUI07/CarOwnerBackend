/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import main.dto.CarDTO;
import main.dto.CountCarsByColor;
import main.entity.Car;
import main.mapper.CarMapper;
import main.repo.CarRepo;
import main.repo.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
        this.repo1=repo1;
        this.mapper=mapper;
    }
    private CarMapper mapper;
    @PersistenceContext
    private EntityManager em;
    
    
    private CarRepo repo;
    private OwnerRepo repo1;
    private Validator validator;

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
    
    @Cacheable(value="findCarById", key="#id")
    public CarDTO findCar(Integer id){
        var c = repo.findById(id).orElse(null);
        return mapper.toDTO(c);
    }
    
    @Cacheable(value="allCars", key="#root.methodName")
    public List<CarDTO> getAll(){
        return repo.findAll().stream().map(x -> mapper.toDTO(x)).collect(Collectors.toList());
    }
    
    @CacheEvict(value={"findCarById","allCars"}, allEntries=true)
    public void clearCache(){
    }
    
    @Transactional
    @CacheEvict(value={"findCarById","allCars"}, allEntries=true)
    public CarDTO createCar(CarDTO x){
        Set<ConstraintViolation<CarDTO>> violations = validator.validate(x);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        var car = mapper.toCar(x);
        var savedCar = repo.save(car);
        clearCache();
        return mapper.toDTO(savedCar);
    }
    
    @Transactional
    @CacheEvict(value={"findCarById","allCars"}, allEntries=true)
    public CarDTO updateCar(Integer id, CarDTO x){
        Set<ConstraintViolation<CarDTO>> violations = validator.validate(x);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
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
        clearCache();
        return mapper.toDTO(savedCar);
    }
    
    
    @Transactional
    @CacheEvict(value={"findCarById","allCars"}, allEntries=true)
    public void deleteCar(Integer id){
        var c = repo.findById(id).orElse(null);
        if(c!=null){
            repo.deleteById(id);
        }
        clearCache();
    }
    
    public List<CarDTO> getCarsByBrand(String brand){
        var q = "SELECT * FROM car WHERE brand= :brand";
        var results =  em.createNativeQuery(q, Car.class).setParameter("brand", brand).getResultList();
        return (List<CarDTO>) results.stream().map(x -> mapper.toDTO((Car) x)).collect(Collectors.toList());
    }
    
    public List<CarDTO> getCarsByYear(int year){
        var q = "SELECT * FROM car WHERE model_year= :x";
        var results= em.createNativeQuery(q, Car.class).setParameter("x", year).getResultList();
        return (List<CarDTO>) results.stream().map(x -> mapper.toDTO((Car) x)).collect(Collectors.toList());
    }
    
    public List<CarDTO> getCarsByColor(String color){
        var q = "SELECT * FROM car WHERE color= :x";
        var results= em.createNativeQuery(q, Car.class).setParameter("x", color).getResultList();
        return (List<CarDTO>) results.stream().map(x -> mapper.toDTO((Car) x)).collect(Collectors.toList());
    }
    
    public List<CarDTO> getCarsByColorOrBrand(String color, String brand){
        var q = "SELECT * FROM car WHERE color= :color OR brand= :brand";
        var results= em.createNativeQuery(q, Car.class).setParameter("brand", brand).setParameter("color",color).getResultList();
        return (List<CarDTO>) results.stream().map(x -> mapper.toDTO((Car) x)).collect(Collectors.toList());
    }
    
    public List<CarDTO> getCarsByBrandSortByYear(String brand){
        var q = "SELECT * FROM car WHERE brand= :brand ORDER BY model_year";
        var results= em.createNativeQuery(q, Car.class).setParameter("brand", brand).getResultList();
        return (List<CarDTO>) results.stream().map(x -> mapper.toDTO((Car) x)).collect(Collectors.toList());

    }
    
    //How many cars are there for each color?
    public List<CountCarsByColor> carsCountByColor(){
        var q = "SELECT color, COUNT(id) as num_cars FROM car GROUP BY color";
        List<Object[]> results =  em.createNativeQuery(q).getResultList();
        return results.stream().map(x -> new CountCarsByColor((String) x[0],(int) x[1])).collect(Collectors.toList());
    }
    
    
}
