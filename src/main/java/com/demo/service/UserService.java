package com.demo.service;

import com.demo.domain.User;

/**
 * @author subham
 *
 */
public interface UserService {

	User findByUsername(String username);

}
