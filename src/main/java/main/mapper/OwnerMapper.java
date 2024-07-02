/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.mapper;

import main.dto.OwnerDTO;
import main.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author hp
 */
@Mapper
public interface OwnerMapper {
    @Autowired
    OwnerMapper INSTANCE = Mappers.getMapper(OwnerMapper.class);
    
    OwnerDTO toDTO(Owner o);
    Owner toOwner(OwnerDTO x);
}
