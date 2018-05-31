package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Subham Ashish
 *
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	/**
	 * configure authentication-provider here.
	 */
	@Autowired
	private AuthenticationProviderImplementation authenticationProviderImplementation;
	
	@Autowired      // here is configuration related to spring boot basic authentication
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationProviderImplementation);
        
    }
	
	
	/**
	 * configure basic auth and allow which url pattern to be blocked or released without authentication.
	 */
	@Override
	 public void configure(HttpSecurity http) throws Exception {
	
		 http
			.httpBasic().and()//indicate basic authentication is requires
			.authorizeRequests()
				.antMatchers("/api/**","/").permitAll()// /api/anything will be accessible directly no need of any authentication
				.anyRequest().authenticated()////indicate all request will be secure
				.and()
			.csrf().disable();//disable csrf
		 
		 
				//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

		 /**
		  * every time it creates a new session,without all existing session attributes to the new session
		  */
		 
		 http.sessionManagement()
		  .sessionFixation().newSession();
		 
		 //https://docs.spring.io/spring-security/site/docs/5.0.3.RELEASE/reference/htmlsingle/#ns-session-fixation
		 
	 }
	

}
