package com.demo.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImplementation extends AbstractUserDetailsAuthenticationProvider {
	
	@Autowired
	private CustomUserDetailsService  customUserDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

		
		if (authentication.getCredentials() == null || userDetails.getPassword() == null) {
			
			throw new BadCredentialsException("Credentials cannot be null");
			
		}
		
		/**
		 * here it still not authenticated....
		 */
		
		Collection<GrantedAuthority> authorities = authentication.getAuthorities();
		boolean authenticated = authentication.isAuthenticated();
		
		/**
		 * authenticated password
		 */
		
		if (!bCryptPasswordEncoder.matches((String) authentication.getCredentials(),userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Credentials !");

		}
		
		System.out.println("Is Authenticated - > "+authenticated);
		
	}

	
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		/**
		 * call loadByUserName method
		 */
		
		return customUserDetailsService.loadUserByUsername(username);
	}
	
	
}
