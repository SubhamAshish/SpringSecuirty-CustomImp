package com.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.CollectUserModel;
import com.demo.service.ConfigurationService;

/**
 * @author subham
 * 
 */

@RestController
public class Configuration {

	@Autowired
	private ConfigurationService configurationService;
	
	@PostMapping(value="/userSave")
	public boolean userSave(@RequestBody CollectUserModel  collectUserModel){
		
		return configurationService.saveUser(collectUserModel);
		
	}
	
	//insert one new user of role only user
	@PostMapping(value="/adminUserSave")
	public boolean newUserSave(@RequestBody CollectUserModel  collectUserModel){
		
		return configurationService.newUserSave(collectUserModel);
		
	}
	
}
