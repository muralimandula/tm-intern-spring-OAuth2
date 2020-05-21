package com.example.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{  // For Authorization
	
	@Autowired
	public AuthenticationManager authenticationManager;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		 * Security for the authorization(to access tokens, which further authorize user by roles)
		 */
		
		security.tokenKeyAccess("permitAll()")				// Anyone can use token
			.checkTokenAccess("isAuthenticated()");			// token must be an authenticated one
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		 * Client Details
		 */
		clients
			.inMemory()
			.withClient("ClientId")
			.secret("secret")
			.authorizedGrantTypes("authorization_code")
			.scopes("user_info")
			.autoApprove(true);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		/*
		 * Authorizing end-points (Who can access which end-point), is set-up according to authentication manager
		 */
		endpoints.authenticationManager(authenticationManager);
	}
}
