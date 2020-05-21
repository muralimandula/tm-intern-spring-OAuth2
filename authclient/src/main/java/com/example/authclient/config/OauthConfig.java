package com.example.authclient.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class OauthConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			.antMatcher("/**").authorizeRequests()    // These end-points need authorization
			.antMatchers("/","/login**").permitAll()  // These end-points are allowed to all
			.anyRequest().authenticated();			  // Any other request need Authentication/Login
	}
	
	
}
