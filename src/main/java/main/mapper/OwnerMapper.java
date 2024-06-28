/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.mapper;

import main.dto.OwnerDTO;
import main.entity.Owner;
import main.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class OwnerMapper {
    @Autowired
    private CarRepo repo;
    public Owner toOwner(OwnerDTO x){
        var o = new Owner();
        o.setFirstName(x.getFirstName());
        o.setLastName(x.getLastName());
        o.setCars(repo.findAllById(x.getCarsIDs()));
        return o;
    }
    
    public OwnerDTO toDTO(Owner o){
        var x = new OwnerDTO();
        x.setId(o.getId());
        x.setFirstName(o.getFirstName());
        x.setLastName(o.getLastName());
        x.setCarsIDs(repo.findIdsByCars(o.getCars()));
        return x;
    }
}
