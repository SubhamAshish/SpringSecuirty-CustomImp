package com.demo.security;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Role;
import com.demo.domain.User;
import com.demo.model.UserModel;
import com.demo.service.UserService;

/**
 * @author subham
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	@Transactional
	public UserModel loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userService.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password !");
		}

		
		Collection<Role> roles = user.getRole();
		
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
		
		/**
		 * Adding authority like @PreAuthorize("hasAuthority('RoleName')")
		 * we can customize it according to role-feature-permission based on our requirements
		 */
		
		roles.forEach(role->{
			
			grantedAuthority.add(new SimpleGrantedAuthority(role.getRoleName()));
			
			LOGGER.info("Role = " +role.getRoleName());
			
		});
		
		
		return new UserModel(user.getUserName(), user.getPassword(), user.isEnabled(), !user.isAccountExpired(), !user.isCredentialexpired(), !user.isAccountLocked(), grantedAuthority,user.getUserId(),roles);
		
	}

}
