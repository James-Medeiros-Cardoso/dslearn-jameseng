package com.devsuperior.dslearnbds.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.UserRepository;

@Component //será um componente do SpringBoot
public class JwtTokenEnhancer implements TokenEnhancer{

	//para acessar o usuário:
	@Autowired
	private UserRepository userRepository;
	
	@Override //receber os dois objetos. Quando gerar o token, ele vai acrescentar os objetos informaos pela classe
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		User user=userRepository.findByEmail(authentication.getName()); //busca o usuário por email
		
		//acrescentar objetos ao Token:
		Map<String, Object> map=new HashMap<>();
		map.put("userId", user.getId()); //inserir Id
		
		DefaultOAuth2AccessToken token=(DefaultOAuth2AccessToken) accessToken;
		token.setAdditionalInformation(map); //adiciona informações ao token

		return token;
	}
}