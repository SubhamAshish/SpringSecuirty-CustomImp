package com.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.RoleFeaturePermissionScheme;
import com.demo.domain.User;
import com.demo.domain.UserAreaMapping;
import com.demo.domain.UserRoleFeaturePermissionMapping;
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

		
		 Collection<UserAreaMapping> userAreaMapping = user.getAreas();
		
		 Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
		
		 /**
		  * As one user can have multiple roles
		  */
		 Set<Integer> roleIds = new HashSet<>();
		 
		/**
		 * Adding authority like @PreAuthorize("hasAuthority('feature-permission')")
		 * 
		 */
		
		 userAreaMapping.forEach(userArea->{
			 
			 List<UserRoleFeaturePermissionMapping> userRoleFeaturePermissionMappings = userArea.getUserRoleFeaturePermissionMappings();
			 
			 userRoleFeaturePermissionMappings.forEach(userRoleFeaturePermission->{
				 
				 RoleFeaturePermissionScheme roleFeaturePermissionScheme = userRoleFeaturePermission.getRoleFeaturePermissionScheme();
				 
				 //addin role ids here
				 roleIds.add(roleFeaturePermissionScheme.getRole().getRoleId());
				 
				 LOGGER.info("<<Authorites : << : "+roleFeaturePermissionScheme.getFeaturePermissionMapping().getFeature().getFeatureName()
						 .concat("-")
						 .concat(roleFeaturePermissionScheme.getFeaturePermissionMapping().getPermission().getPermissionName()));
				 
				 grantedAuthority.add(new SimpleGrantedAuthority(
						 
						 roleFeaturePermissionScheme.getFeaturePermissionMapping().getFeature().getFeatureName()
						 .concat("-")
						 .concat(roleFeaturePermissionScheme.getFeaturePermissionMapping().getPermission().getPermissionName())
						 
						 ));
			 });
			 
		 });
		
		return new UserModel(user.getUserName(), user.getPassword(), user.isEnabled(), !user.isAccountExpired(), !user.isCredentialexpired(), !user.isAccountLocked(), grantedAuthority,user.getUserId(),userAreaMapping,roleIds,user.getPassword());
		
	}

}
