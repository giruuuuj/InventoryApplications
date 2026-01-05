package com.infosys.inventoryApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.infosys.inventoryApplication.bean.InventoryUser;
import com.infosys.inventoryApplication.dao.InventoryUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryUserService implements UserDetailsService{

    @Autowired
    private InventoryUserRepository repository;
     
    private String role;
    private InventoryUser user;
    private String email;
    private String userId;
     
    public String getRole() {
        return role;
    }
    public InventoryUser getUser() {
        return user;
    }
    public String getEmail() {
        return email;
    }
    public String getUserId() {
        return userId;
    }
    
    // to save a new user in database
    public void save(InventoryUser user) {
        repository.save(user);
    }
    
    // Validate an existing user - FIXED VERSION
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<InventoryUser> userOptional = repository.findById(username);
        
        // FIX: Check if user exists before calling .get()
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        
        this.user = userOptional.get();
        this.userId = user.getUsername();
        this.role = user.getRole();
        this.email = user.getEmail();
        
        return this.user;
    }
    
    // Add this method for manual user setting
    public void setCurrentUser(InventoryUser user) {
        this.user = user;
        this.userId = user.getUsername();
        this.role = user.getRole();
        this.email = user.getEmail();
        
    }
    
    public List<String >  getUserBYRole(String role){
    	return repository.getUserByRole(role);
    }

}