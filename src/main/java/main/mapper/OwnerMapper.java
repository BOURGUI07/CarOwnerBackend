/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.mapper;

import java.util.ArrayList;
import main.dto.OwnerDTO;
import main.entity.Owner;
import main.repo.CarRepo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerMapper {
    @Autowired
    private CarRepo repo;
    public Owner toOwner(OwnerDTO x){
        var o = new Owner();
        o.setFirstName(x.getFirstName());
        o.setLastName(x.getLastName());
        if(x.getCarsIDs()!=null && !x.getCarsIDs().isEmpty()){
            o.setCars(repo.findAllById(x.getCarsIDs()));
        }
        o.setCars(repo.findAllById(x.getCarsIDs()));
        return o;
    }
    
    public OwnerDTO toDTO(Owner o){
        var x = new OwnerDTO(o.getId(), o.getFirstName(), o.getLastName(), repo.findIdsByCars(o.getCars()));
        if(o.getCars().isEmpty()){
            x = new OwnerDTO(o.getId(), o.getFirstName(), o.getLastName(), new ArrayList<>());
        }else if(o.getCars()==null){
            x = new OwnerDTO(o.getId(), o.getFirstName(), o.getLastName(), null);
        }
        return x;
    }
}
