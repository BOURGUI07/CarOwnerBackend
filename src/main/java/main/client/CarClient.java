/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.client;

import main.dto.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
}
