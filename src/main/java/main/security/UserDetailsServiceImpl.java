/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    public UserDetailsServiceImpl(AppUserRepository repo) {
        this.repo = repo;
    }
    private final AppUserRepository repo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        var user = repo.findByUsername(username);
        UserBuilder builder = null;
        if(user.isPresent()){
            var appUser = user.get();
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(appUser.getPassword());
            builder.roles(appUser.getRole());
        }else{
            throw new UsernameNotFoundException("User not found");
        }
        return builder.build();
    }
}
