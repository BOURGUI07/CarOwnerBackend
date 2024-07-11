/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp
 */
@RestController
public class LoginController {
    @Autowired
    public LoginController(JwtService service, AuthenticationManager manager) {
        this.service = service;
        this.manager = manager;
    }
    private final JwtService service;
    private final AuthenticationManager manager;
    
    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials){
        var creds = new UsernamePasswordAuthenticationToken(credentials.username(),credentials.password());
        var auth = manager.authenticate(creds);
        var jwts = service.getToken(auth.getName());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer" + jwts).header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization").build();
    }
}
