package com.demo.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author subham
 *
 */
@RestController
public class WebController {
	
	@GetMapping(value = "/api")
	public String abc() {

		return "Hello world!";
	}

	@GetMapping(value = "/check")
	@PreAuthorize("hasAuthority('user-view')") 
	public String abcd() {

		return "Hello Check!";
	}
	
	@GetMapping(value = "/api/check")
	public String abcde() {

		return "Hello Check!";
	}
	
	@GetMapping(value = "/")
	public String welcome() {

		return "Hello Welcome To Spring Secuity!!";
	}
	
	
}
