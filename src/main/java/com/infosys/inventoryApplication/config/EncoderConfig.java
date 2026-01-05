package com.infosys.inventoryApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncoderConfig {
	 @Bean
	   public PasswordEncoder passwordEncoder()  // FIXED: Correct method name
	   {
	        return new BCryptPasswordEncoder();
	   }

   public PasswordEncoder passwordEcoding() {
	// TODO Auto-generated method stub
	return null;
   }

}
