package com.devsuperior.dslearnbds.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslearnbds.dto.UserDTO;
import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.UserRepository;
import com.devsuperior.dslearnbds.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {

	// instanciando o Logger
	private static Logger Logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthService authService;
	
	@Transactional(readOnly=true)
	public UserDTO findById(Long id) {
		
		authService.validateSelfOrAdmin(id);
		
		Optional<User> obj=repository.findById(id);
		User entity=obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found."));
		return new UserDTO(entity);
	}

	@Override // retorna username (email) de acordo com o UserDetails
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if (user == null) {
			Logger.error("User not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		Logger.info("User found: " + username);
		return user;
	}

}
