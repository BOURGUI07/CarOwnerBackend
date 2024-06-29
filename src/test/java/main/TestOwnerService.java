/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package main;

import java.util.Arrays;
import java.util.Optional;
import main.dto.OwnerDTO;
import main.entity.Owner;
import main.mapper.OwnerMapper;
import main.repo.CarRepo;
import main.repo.OwnerRepo;
import main.service.OwnerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author hp
 */
public class TestOwnerService {
    @Mock
    private OwnerMapper mapper;
    @Mock
    private OwnerRepo repo;
    @Mock
    private CarRepo repo1;
    @InjectMocks
    private OwnerService service;
    private Owner o = new Owner();
    private OwnerDTO x = new OwnerDTO();
    
    public TestOwnerService() {
        o.setId(1);
        o.setFirstName("youness");
        o.setLastName("Bourgui");
        
        x.setId(1);
        x.setFirstName("youness");
        x.setLastName("Bourgui");
    }
    
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testCreateOwner(){
        when(mapper.toOwner(x)).thenReturn(o);
        when(repo.save(o)).thenReturn(o);
        when(mapper.toDTO(o)).thenReturn(x);
        
        assertEquals(x, service.createOwner(x));
        verify(repo, times(1)).save(o);
    }
    
    @Test
    void testUpdateOwner(){
        when(repo.findById(1)).thenReturn(Optional.of(o));
        when(repo.save(o)).thenReturn(o);
        when(mapper.toDTO(o)).thenReturn(x);
        assertEquals(x, service.updateOwner(1, x));
        verify(repo,times(1)).save(o);
    }
    
    @Test
    void testFindOwner(){
        when(repo.findById(1)).thenReturn(Optional.of(o));
        when(mapper.toDTO(o)).thenReturn(x);
        assertEquals(x, service.findOwner(1));
    }
    
    @Test
    void testGetAllOwners(){
        when(repo.findAll()).thenReturn(Arrays.asList(o));
        when(mapper.toDTO(o)).thenReturn(x);
        assertEquals(1, service.getAll().size());
    }
    
    @Test
    void testDeleteOwner(){
        doNothing().when(repo).deleteById(1);
        when(repo.findById(1)).thenReturn(Optional.of(o));
        service.deleteOwner(1);
        verify(repo,times(1)).deleteById(1);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
