package com.damb.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticatoManager;

    @Autowired
    InfoAdicionalToken infoAdicionalToken;
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	// esto se hace por cada cliente del backend
	clients.inMemory().withClient("AngularAPP").secret(passwordEncoder.encode("12345")).scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(3600).refreshTokenValiditySeconds(3600);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	TokenEnhancerChain tokenChain = new TokenEnhancerChain();
	tokenChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
	endpoints.authenticationManager(authenticatoManager).accessTokenConverter(accessTokenConverter())
		.tokenEnhancer(tokenChain);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
	JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
	jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVATE);
	jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLIC);
	return jwtAccessTokenConverter;
    }

}
