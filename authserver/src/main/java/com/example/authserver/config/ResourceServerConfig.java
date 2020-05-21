package com.example.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter{   // For WebSecurity

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.requestMatchers()
			.antMatchers("/login", "/oauth/authorize")  // these end-points
			.and()
			.authorizeRequests()		// allows restricting access
			.anyRequest()				// for any request made	
			.authenticated()			// needs to be authenticated	
			.and()
			.formLogin().permitAll();   // uses a basic login-form provided by Spring
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.parentAuthenticationManager(authenticationManager)
			.inMemoryAuthentication()	// Creating a basic user
			.withUser("murali")			// user name
			.password("murali")			// password
			.roles("USER");			// Having roles
	}
	
}
