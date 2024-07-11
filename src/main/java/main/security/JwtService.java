/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 *
 * @author hp
 */
@Component
public class JwtService {
    static final long EXPIRATIONTIME = 86400000;
    static final String PREFIX = "Bearer";
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    public String getToken(String username){
        var token = Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis()+EXPIRATIONTIME)).signWith(key).compact();
        return token;
    }
    
    public String getAuthuser(HttpServletRequest request){
        var token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(token!=null){
            var user = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token.replace(PREFIX, "")).getBody().getSubject();
            if(user!=null){
                return user;
            }
        }
        return null;
    }
}
