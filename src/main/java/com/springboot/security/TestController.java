package com.springboot.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/")
	public String homePage() {
		return "WELCOME TO HOME PAGE.....Spring Security Example";
	}
	
	@GetMapping("/manager")
	@PreAuthorize("hasRole('ADMIN')")
	public String map() {
		return "WELCOME TO ADMIN PAGE.....Check the Services";
	}
	
	@GetMapping("/clerk")
	@PreAuthorize("hasRole('USER')")
	public String check() {
		return "WELCOME TO CLERK PAGE.....Check the Services";
	}
	
	@GetMapping("/hr")
	@PreAuthorize("hasRole('HR')")
	public String hr() {
		return "WWELCOME TO HR PAGE.....Check the Services";
	}
}
