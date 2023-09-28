package com.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		
		UserDetails admin = User.withUsername("ratan")
								.password(passwordEncoder.encode("Ratan@123"))
								.roles("ADMIN")
								.build();
		
		UserDetails user = User.withUsername("abhi")
								.password(passwordEncoder.encode("Abhi@123"))
								.roles("USER")
								.build();
		
		UserDetails hr = User.withUsername("shiva")
							 .password(passwordEncoder.encode("Shiva@123"))
							 .roles("HR")
							 .build();
					
		
		return new InMemoryUserDetailsManager(admin,user,hr);	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
	
		httpSecurity.csrf()
					.disable()
					.authorizeHttpRequests()
					.antMatchers("/").permitAll()
					.anyRequest()
					.authenticated()
					.and()
					.formLogin()
					.setBuilder(httpSecurity);
		
		return httpSecurity.build();
					
	}
}

//		"RoleLockSecurity" represents a robust and controlled access solution that 
//		enforces security through assigned roles, ensuring authorized access to specific areas 
//		while restricting unauthorized entry.
