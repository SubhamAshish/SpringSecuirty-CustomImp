package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.domain.Role;
import com.demo.domain.User;
import com.demo.model.CollectUserModel;
import com.demo.repository.RoleRepository;
import com.demo.repository.UserRepository;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Value("#{'${user.role.id}'.split(',')}")
	List<Integer> roleValue;

	@Override
	@Transactional
	public boolean saveUser(CollectUserModel collectUserModel) {

		User user = new User();

		user.setUserName(collectUserModel.getUserName());
		user.setPassword(bCryptPasswordEncoder.encode(collectUserModel.getPassword()));

		User saveUser = userRepository.save(user);

		if (saveUser != null) {

			List<Role> roles = roleRepository.findByRoleIdIn(roleValue);

			roles.forEach(role -> {
				role.setUser(saveUser);
			});

		}
		return true;
	}

	@Override
	@Transactional
	public boolean newUserSave(CollectUserModel collectUserModel) {
		User user = new User();

		user.setUserName(collectUserModel.getUserName());
		user.setPassword(bCryptPasswordEncoder.encode(collectUserModel.getPassword()));

		User saveUser = userRepository.save(user);

		if (saveUser != null) {

			Role role = roleRepository.findByRoleId(2);

			role.setUser(saveUser);

		}
		return true;
	}

}
