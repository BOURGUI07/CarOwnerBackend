/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.client;

import java.util.List;
import java.util.Optional;
import main.dto.AveragePriceByOwner;
import main.dto.MostExpensiveCarByOwner;
import main.dto.OwnerDTO;
import main.dto.OwnersOfBrand;
import main.dto.OwnersOfCarsLessThanYear;
import main.dto.OwnersWithMoreCars;
import main.dto.TotalValueOfOwnerCars;
import main.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 *
 * @author hp
 */
@Service
public class OwnerClient {
    @Autowired
    public OwnerClient(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("http://localhost:8080/api/owners").build();
    }
    private final WebClient webClient;
    
    public Flux<OwnerDTO> getAllOwners(){
        return this.webClient.get().retrieve().bodyToFlux(OwnerDTO.class);
    }
    
    public Mono<OwnerDTO> getOwnerById(Integer id){
        return this.webClient.get().uri("/{id}", id).retrieve().bodyToMono(OwnerDTO.class);
    }
    
    public Mono<OwnerDTO> createOwner(OwnerDTO x){
        return this.webClient.post().bodyValue(x).retrieve().bodyToMono(OwnerDTO.class);
    }
    
    public Mono<OwnerDTO> updateOwner(Integer id, OwnerDTO x){
        return this.webClient.put().uri("/{id}", id).bodyValue(x).retrieve().bodyToMono(OwnerDTO.class);
    }
    
    public Mono<Void> deleteOwner(Integer id){
        return webClient.delete().uri("/{id}",id).retrieve().bodyToMono(Void.class);
    }
    
    
    public Mono<OwnerDTO> findOwnerByFirstName(String firstName){
        return webClient.get().uri("/findByName", firstName).retrieve().bodyToMono(OwnerDTO.class);
    }
    
    public Flux<OwnersWithMoreCars> ownersWithMoreThanOneCar(){
        return webClient.get().uri("/moreThanOneCar").retrieve().bodyToFlux(OwnersWithMoreCars.class);
    }
    
    public Flux<MostExpensiveCarByOwner> mostExpensiveCarByOwner(){
        return webClient.get().uri("/mostExpensiveByOwner").retrieve().bodyToFlux(MostExpensiveCarByOwner.class);
    }
    
    public Flux<AveragePriceByOwner> avgPriceByOwner(){
        return webClient.get().uri("/avgPriceByOwner").retrieve().bodyToFlux(AveragePriceByOwner.class);
    }
    
    public Flux<OwnersOfBrand> ownersOfBrand(String brand){
        return webClient.get().uri("/ownerOfBrand", brand).retrieve().bodyToFlux(OwnersOfBrand.class);
    }
    public Flux<TotalValueOfOwnerCars> totalValueOfOwnerCars(){
        return webClient.get().uri("/totalPriceEachOwner").retrieve().bodyToFlux(TotalValueOfOwnerCars.class);
    }
    
    public Flux<OwnersOfCarsLessThanYear> ownersOfBrand(int year){
        return webClient.get().uri("/ownersOfYearLess", year).retrieve().bodyToFlux(OwnersOfCarsLessThanYear.class);
    }
    
    public Flux<OwnerDTO> ownersWithNoCars(){
        return this.webClient.get().uri("/withNoCar").retrieve().bodyToFlux(OwnerDTO.class);
    }
    
    public Mono<List<OwnerDTO>> getProducts(String firstName, String lastName) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/owners")
                        .queryParamIfPresent("firstName", Optional.ofNullable(firstName))
                        .queryParamIfPresent("lastName", Optional.ofNullable(lastName))
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<OwnerDTO>>() {});
    }
           
}
