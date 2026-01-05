// src/main/java/com/infosys/inventoryApplication/controller/LoginController.java
package com.infosys.inventoryApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.inventoryApplication.bean.InventoryUser;
import com.infosys.inventoryApplication.config.EncoderConfig;
import com.infosys.inventoryApplication.service.InventoryUserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/invent/")
@CrossOrigin(origins = "http://localhost:3131", allowCredentials = "true")
public class LoginController {

    @Autowired
    private InventoryUserService service;

    @Autowired
    private EncoderConfig econfig;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String registerNewUser(@RequestBody InventoryUser user) {
        PasswordEncoder bCrypt = econfig.passwordEncoder();  // Changed from passwordEncoding() to passwordEncoder()
        String encodedPassword = bCrypt.encode(user.getPassword());
        user.setPassword(encodedPassword);
        service.save(user);
        return "User registered successfully";
    }
    
    @PostMapping("/register")
    public String registerUser(@RequestBody InventoryUser user) {
        PasswordEncoder bCrypt = econfig.passwordEncoder();
        String encodedPassword = bCrypt.encode(user.getPassword());
        user.setPassword(encodedPassword);
        service.save(user);
        return "User registered successfully";
    }
    
    @GetMapping("/login/{userId}/{password}")	
    public String validateUser(@PathVariable String userId, @PathVariable String password) {
        String role = "false";
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userId, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            role = service.getRole();
        } catch (Exception ex) {
            // Handle exception
        }
        return role;
    }

    @GetMapping("/user")
    public InventoryUser getUserDetails() {
        return service.getUser();
    }

    @GetMapping("/user/id")  // Fixed: changed endpoint
    public String getUserId() {
        return service.getUserId();
    }

    @GetMapping("/role")
    public String getRole() {
        return service.getRole();
    }

    @GetMapping("/role/{role}")
    List<String> getUserByRole(@PathVariable String role){
        return service.getUserBYRole(role);
    }
    
    
    
    

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("Logout successful");
    }
    
    
    
    
}