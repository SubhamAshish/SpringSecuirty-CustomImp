package com.demo.repository;

import org.springframework.data.repository.RepositoryDefinition;

import com.demo.domain.User;

/**
 * @author subham
 *
 */
@RepositoryDefinition(domainClass = User.class, idClass = Integer.class)
public interface UserRepository {

	User findByUserName(String username);

	User save(User user);
	
	

}
