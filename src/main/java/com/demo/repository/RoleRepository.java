package com.demo.repository;

import java.util.List;

import org.springframework.data.repository.RepositoryDefinition;

import com.demo.domain.Role;

@RepositoryDefinition(domainClass = Role.class, idClass = Integer.class)
public interface RoleRepository {


	List<Role> findByRoleIdIn(List<Integer> roleValue);

	Role findByRoleId(int i);
	
	
	

}
