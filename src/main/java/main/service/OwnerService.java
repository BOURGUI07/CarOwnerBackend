/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import main.dto.OwnerDTO;
import main.entity.Owner;
import main.mapper.OwnerMapper;
import main.repo.CarRepo;
import main.repo.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class OwnerService {

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
    @Autowired
    public OwnerService(OwnerMapper mapper, OwnerRepo repo, CarRepo carRepo) {
        this.mapper = mapper;
        this.repo = repo;
        this.carRepo = carRepo;
    }
    @PersistenceContext
    private EntityManager em;
    private OwnerMapper mapper;
    private OwnerRepo repo;
    private CarRepo carRepo;
    private Validator validator;
    
    public List<OwnerDTO> getAll(){
        return repo.findAll().stream().map(o -> mapper.toDTO(o)).collect(Collectors.toList());
    }
    
    public OwnerDTO findOwner(Integer id){
        var o = repo.findById(id).orElse(null);
        return mapper.toDTO(o);
    }
    
    @Transactional
    public void deleteOwner(Integer id){
        var o = repo.findById(id).orElse(null);
        if(o!=null){
            repo.deleteById(id);
        } 
    }
    
    @Transactional
    public OwnerDTO createOwner(OwnerDTO x){
        Set<ConstraintViolation<OwnerDTO>> violations = validator.validate(x);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        var o = mapper.toOwner(x);
        var savedOwner = repo.save(o);
        return mapper.toDTO(savedOwner);
    }
    
    @Transactional
    public OwnerDTO updateOwner(Integer id, OwnerDTO x){
        Set<ConstraintViolation<OwnerDTO>> violations = validator.validate(x);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        var o = repo.findById(id).orElse(null);
        if(o!=null){
            o.setFirstName(x.getFirstName());
            o.setLastName(x.getLastName());
            if(!x.getCarsIDs().isEmpty()){
                o.setCars(carRepo.findAllById(x.getCarsIDs()));
            }
            var saved = repo.save(o);
            return mapper.toDTO(saved);
        }
        return null;
    }
    
    public Owner findOwnerByFirstName(String firstName){
        var q = "SELECT * FROM owner WHERE firstname= :x";
        try{
            return(Owner)  em.createNativeQuery(q, Owner.class).setParameter("x", firstName).getSingleResult();
        }catch(NoResultException e){
            return null;
        }catch(NonUniqueResultException e){
            return null;
        }
    }
}
