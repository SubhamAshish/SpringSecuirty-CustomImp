package com.demo.model;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.demo.domain.UserAreaMapping;

public class UserModel extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4431662893129587625L;

	private Integer userId;
	
	private Set<Integer> roleIds;
	
	private String hashPassword;

	Collection<UserAreaMapping> userAreaMapping;

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
	 * @Desription extra parameter has been passed here to be initialized and
	 *             return
	 */
	public UserModel(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			Integer userId, Collection<UserAreaMapping> userAreaMapping,Set<Integer>roleIds,String hashPassword) {

		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

		this.userId = userId;
		this.userAreaMapping = userAreaMapping;
		this.roleIds=roleIds;
		this.hashPassword=hashPassword;

	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Collection<UserAreaMapping> getUserAreaMapping() {
		return userAreaMapping;
	}

	public void setUserAreaMapping(Collection<UserAreaMapping> userAreaMapping) {
		this.userAreaMapping = userAreaMapping;
	}

	public Set<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	
	
}
