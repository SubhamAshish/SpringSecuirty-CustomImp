package com.demo.service;

import com.demo.domain.User;

public interface UserService {

	User findByUsername(String username);

}
