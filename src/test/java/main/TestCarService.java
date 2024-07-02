/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package main;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Arrays;
import java.util.Optional;
import main.dto.CarDTO;
import main.entity.Car;
import main.mapper.CarMapper;
import main.repo.CarRepo;
import main.repo.OwnerRepo;
import main.service.CarService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author hp
 */
public class TestCarService {
    @Mock
    private CarMapper mapper;
    @Mock
    private CarRepo repo;
    @Mock
    private OwnerRepo repo1;
    @InjectMocks
    private CarService service;
    private Car c = new Car();
    private CarDTO x;
    private Validator validator;
    
    public TestCarService() {
        c.setId(1);
        c.setBrand("bmw");
        c.setColor("black");
        c.setModelYear(2014);
        c.setPrice(45000.00);
        c.setRegistrationNumber("F11-D2000");
        
        x = new CarDTO(1,"bmw","black","F11-D2000",2014,45000.00,null);
    }
    
    
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        service.setValidator(validator);
    }
    
    @Test
    void testCreateCar(){
        when(mapper.toCar(x)).thenReturn(c);
        when(repo.save(c)).thenReturn(c);
        when(mapper.toDTO(c)).thenReturn(x);
        assertEquals(x, service.createCar(x));
        verify(repo,times(1)).save(c);
    }
    
    @Test
    void testUpdateCar(){
        when(repo.findById(1)).thenReturn(Optional.of(c));
        when(repo.save(c)).thenReturn(c);
        when(mapper.toDTO(c)).thenReturn(x);
        assertEquals(x, service.updateCar(1, x));
        verify(repo,times(1)).save(c);
    }
    
    @Test
    void testFindCar(){
        when(repo.findById(1)).thenReturn(Optional.of(c));
        when(mapper.toDTO(c)).thenReturn(x);
        assertEquals(x, service.findCar(1));
    }
    
    @Test
    void testGetAllCars(){
        when(repo.findAll()).thenReturn(Arrays.asList(c));
        when(mapper.toDTO(c)).thenReturn(x);
        assertEquals(1, service.getAll().size());
    }
    
    @Test
    void testDeletecar(){
        doNothing().when(repo).deleteById(1);
        when(repo.findById(1)).thenReturn(Optional.of(c));
        service.deleteCar(1);
        verify(repo,times(1)).deleteById(1);
    }
    
    @Test
    void testOwnerName(){
        var car = new CarDTO(1,"","black","F11-D2000",2014,45000.00,null);
        assertThrows(ConstraintViolationException.class, () -> {
            service.createCar(car);
        });
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
