/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.client;

import main.dto.OwnerDTO;
import main.entity.Owner;
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
    
    
    
}
