/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.client;

import java.util.List;
import java.util.Optional;
import main.dto.CarDTO;
import main.dto.CountCarsByColor;
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
public class CarClient {
    @Autowired
    public CarClient(WebClient.Builder client) {
        this.client = client.baseUrl("http://localhost:8080/api/cars").build();
    }
    private final WebClient client;
    
    public Flux<CarDTO> getAllOwners(){
        return this.client.get().retrieve().bodyToFlux(CarDTO.class);
    }
    
    public Mono<CarDTO> getOwnerById(Integer id){
        return this.client.get().uri("/{id}", id).retrieve().bodyToMono(CarDTO.class);
    }
    
    public Mono<CarDTO> createOwner(CarDTO x){
        return this.client.post().bodyValue(x).retrieve().bodyToMono(CarDTO.class);
    }
    
    public Mono<CarDTO> updateOwner(Integer id, CarDTO x){
        return this.client.put().uri("/{id}", id).bodyValue(x).retrieve().bodyToMono(CarDTO.class);
    }
    
    public Mono<Void> deleteOwner(Integer id){
        return client.delete().uri("/{id}",id).retrieve().bodyToMono(Void.class);
    }
    
    public Flux<CarDTO> findCarsOfBrand(String brand){
        return this.client.get().uri("/findByBrand", brand).retrieve().bodyToFlux(CarDTO.class);
    }
    
    public Flux<CarDTO> findsCarsByYear(int year){
        return this.client.get().uri("/findByYear", year).retrieve().bodyToFlux(CarDTO.class);
    }
    
    public Flux<CarDTO> findCarsByColor(String color){
        return this.client.get().uri("/findByColor", color).retrieve().bodyToFlux(CarDTO.class);
    }
    
    public Flux<CarDTO> findCarsByColorOrBrand(String color, String brand){
        Object[] array = {color, brand};
        return this.client.get().uri("/findByColorOrBrand", array).retrieve().bodyToFlux(CarDTO.class);
    }
    
    public Flux<CarDTO> findCarsOfBrandSortByYear(String brand){
        return this.client.get().uri("/findByBrandSortByYear", brand).retrieve().bodyToFlux(CarDTO.class);
    }
    
    public Flux<CountCarsByColor> countCarsEachColor(String brand){
        return this.client.get().uri("/countCarsEachColor", brand).retrieve().bodyToFlux(CountCarsByColor.class);
    }
    
    public Mono<List<CarDTO>> getProducts(String brand, String color, Integer minYear, Integer maxYear, Double minPrice, Double maxPrice) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/cars")
                        .queryParamIfPresent("brand", Optional.ofNullable(brand))
                        .queryParamIfPresent("color", Optional.ofNullable(color))
                        .queryParamIfPresent("minYear", Optional.ofNullable(minYear))
                        .queryParamIfPresent("maxYear", Optional.ofNullable(maxYear))
                        .queryParamIfPresent("minPrice", Optional.ofNullable(minPrice))
                        .queryParamIfPresent("maxPrice", Optional.ofNullable(maxPrice))
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CarDTO>>() {});
    }
}
