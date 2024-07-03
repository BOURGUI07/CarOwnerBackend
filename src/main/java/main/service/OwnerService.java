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
import main.dto.AveragePriceByOwner;
import main.dto.MostExpensiveCarByOwner;
import main.dto.OwnerDTO;
import main.dto.OwnersOfBrand;
import main.dto.OwnersOfCarsLessThanYear;
import main.dto.OwnersWithMoreCars;
import main.dto.TotalValueOfOwnerCars;
import main.entity.Owner;
import main.mapper.OwnerMapper;
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
public class OwnerService {

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
    @Autowired
    public OwnerService(OwnerRepo repo, CarRepo carRepo, OwnerMapper mapper) {
        this.repo = repo;
        this.carRepo = carRepo;
        this.mapper=mapper;
    }
    @PersistenceContext
    private EntityManager em;
    private OwnerMapper mapper;
    private OwnerRepo repo;
    private CarRepo carRepo;
    private Validator validator;
    
    @Cacheable(value="allOwners", key="#root.methodName")
    public List<OwnerDTO> getAll(){
        return repo.findAll().stream().map(o -> mapper.toDTO(o)).collect(Collectors.toList());
    }
    
    @Cacheable(value="findOwnerById", key="#id")
    public OwnerDTO findOwner(Integer id){
        var o = repo.findById(id).orElse(null);
        return mapper.toDTO(o);
    }
    
    @CacheEvict(value={"findOwnerById","allOwners"}, allEntries=true)
    public void clearCache(){
    }
    
    @Transactional
    @CacheEvict(value={"findOwnerById","allOwners"}, allEntries=true)
    public void deleteOwner(Integer id){
        var o = repo.findById(id).orElse(null);
        if(o!=null){
            repo.deleteById(id);
        }
        clearCache();
    }
    
    @Transactional
    @CacheEvict(value={"findOwnerById","allOwners"}, allEntries=true)
    public OwnerDTO createOwner(OwnerDTO x){
        Set<ConstraintViolation<OwnerDTO>> violations = validator.validate(x);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        var o = mapper.toOwner(x);
        var savedOwner = repo.save(o);
        clearCache();
        return mapper.toDTO(savedOwner);
    }
    
    @Transactional
    @CacheEvict(value={"findOwnerById","allOwners"}, allEntries=true)
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
            clearCache();
            return mapper.toDTO(saved);
        }
        return null;
    }
    
    public OwnerDTO findOwnerByFirstName(String firstName){
        var q = "SELECT * FROM owner WHERE firstname= :x";
        try{
            return mapper.toDTO((Owner)  em.createNativeQuery(q, Owner.class).setParameter("x", firstName).getSingleResult());
        }catch(NoResultException e){
            return null;
        }catch(NonUniqueResultException e){
            return null;
        }
    }
    
    //Which owners have more than one car?
    public List<OwnersWithMoreCars> ownersWhoHaveMoreThanOneCar(){
        var q = "SELECT o.id, o.firstame, o.lastName, COUNT(c.id) as num_cars FROM owner o JOIN car c ON(o.id=c.owner_id) GROUP BY o.id, o.firstame, o.lastName HAVING COUNT(c.id)>1 ORDER BY num_cars DESC";
        List<Object[]> results= em.createNativeQuery(q).getResultList();
        return results.stream().map(x -> new OwnersWithMoreCars((Integer)x[0],(String)x[1],(String)x[2],(int)x[3])).collect(Collectors.toList());
    }
    
    //What is the most expensive car owned by each owner?
    public List<MostExpensiveCarByOwner> mostExpensiveCarByOwner(){
        var q = "SELECT o.id, o.firstame, o.lastName, MAX(c.price) as max_price FROM owner o JOIN car c ON(o.id=c.owner_id) GROUP BY o.id, o.firstame, o.lastName ORDER BY max_price DESC";
        List<Object[]> results= em.createNativeQuery(q).getResultList();
        return results.stream().map(x -> new MostExpensiveCarByOwner((Integer)x[0],(String)x[1],(String)x[2],(double)x[3])).collect(Collectors.toList());
    }
    
    //What is the average price of cars owned by each owner?
    public List<AveragePriceByOwner> avgPriceByOwner(){
        var q = "SELECT o.id, o.firstame, o.lastName, AVG(c.price) as avg_price FROM owner o JOIN car c ON(o.id=c.owner_id) GROUP BY o.id, o.firstame, o.lastName ORDER BY avg_price DESC";
        List<Object[]> results= em.createNativeQuery(q).getResultList();
        return results.stream().map(x -> new AveragePriceByOwner((Integer)x[0],(String)x[1],(String)x[2],(double)x[3])).collect(Collectors.toList());
    }
    
    //Which owners have cars of a specific brand
    public List<OwnersOfBrand> ownersOfBrand(String brand){
        var q = "SELECT o.id, o.firstame, o.lastName FROM owner o JOIN car c ON(o.id=c.owner_id) WHERE c.brand= :brand";
        List<Object[]> results= em.createNativeQuery(q).setParameter("brand", brand).getResultList();
        return results.stream().map(x -> new OwnersOfBrand((Integer)x[0],(String)x[1],(String)x[2])).collect(Collectors.toList());
    }
    
    //What is the total value of cars owned by each owner?
    public List<TotalValueOfOwnerCars> totalValueOfOwnerCars(){
        var q = "SELECT o.id, o.firstame, o.lastName, SUM(c.price) as total FROM owner o JOIN car c ON(o.id=c.owner_id) GROUP BY o.id, o.firstame, o.lastName ORDER BY total DESC";
        List<Object[]> results= em.createNativeQuery(q).getResultList();
        return results.stream().map(x -> new TotalValueOfOwnerCars((Integer)x[0],(String)x[1],(String)x[2],(double)x[3])).collect(Collectors.toList());
    }
    
    //Which owners have cars older than a certain year
    public List<OwnersOfCarsLessThanYear> ownersOfCarsLessThanYear(int year){
        var q = "SELECT o.id, o.firstame, o.lastName FROM owner o JOIN car c ON(o.id=c.owner_id) WHERE c.model_year< :x";
        List<Object[]> results= em.createNativeQuery(q).setParameter("x", year).getResultList();
        return results.stream().map(x -> new OwnersOfCarsLessThanYear((Integer)x[0],(String)x[1],(String)x[2])).collect(Collectors.toList());
    }
    
    //Owners Who Don't Have Any Car
    public List<OwnerDTO> ownersWithNoCars(){
        var q = "SELECT o.* FROM owner o LEFT JOIN car c ON(o.id=c.owner_id) WHERE c.id IS NULL";
        return (List<OwnerDTO>) em.createNativeQuery(q, Owner.class).getResultList().stream().map(x -> mapper.toDTO((Owner) x)).collect(Collectors.toList());
    }
}
