package com.demo.model;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.demo.domain.Role;

public class UserModel extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4431662893129587625L;

	private Integer userId;
	
	Collection<Role> role;
	/**
	 * 
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 * 
	 * @Description private constructors to use existing properties
	 */
	private UserModel(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
	}
	
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 * @param userId
	 * @param role
	 * 
	 * 
	 * @Desription extra parameter has been passed here to be initialized and return
	 */
	public UserModel(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,Integer userId,Collection<Role> role) {
		
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		this.userId=userId;
		this.role=role;
		
	}


	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public Collection<Role> getRole() {
		return role;
	}



	public void setRole(Collection<Role> role) {
		this.role = role;
	}
	
	
	
	
}
