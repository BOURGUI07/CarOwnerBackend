/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.rest;

import java.util.List;
import main.dto.OwnerDTO;
import main.entity.Owner;
import main.handler.RessourceNotFoundException;
import main.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/api")
public class OwnerController {
    @Autowired
    public OwnerController(OwnerService service) {
        this.service = service;
    }
    private OwnerService service;
    
    @PostMapping("/owners")
    public OwnerDTO createOwner(@RequestBody OwnerDTO x){
        return service.createOwner(x);
    }
    
    @PutMapping("/owners/{id}")
    public OwnerDTO updateOwner(@PathVariable Integer id, @RequestBody OwnerDTO x){
        if(id<0){
            throw new RessourceNotFoundException("No Owner Found for id: " + id);
        }
        return service.updateOwner(id, x);
    }
    
    @GetMapping("/owners")
    public List<OwnerDTO> getOwners(){
        return service.getAll();
    }
    
    @GetMapping("/owners/{id}")
    public OwnerDTO getOwner(@PathVariable Integer id){
        if(id<0){
            throw new RessourceNotFoundException("No Owner Found for id: " + id);
        }
        return service.findOwner(id);
    }
    
    @DeleteMapping("/owners/{id}")
    public void deleteOwner(@PathVariable Integer id){
        if(id<0){
            throw new RessourceNotFoundException("No Owner Found for id: " + id);
        }
        service.deleteOwner(id);
    }
    
    @GetMapping("/owners/findByName")
    public Owner findOwnerByName(@RequestParam String name){
        return service.findOwnerByFirstName(name);
    }
}
