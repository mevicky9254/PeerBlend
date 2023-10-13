package com.PEERBLEND.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.PEERBLEND.config.JwtTokenProvider;
import com.PEERBLEND.exception.UserException;
import com.PEERBLEND.model.User;
import com.PEERBLEND.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	private UserRepository userRepository;
	private JwtTokenProvider jwtTokenProvider;
	
	public UserServiceImplementation(UserRepository userRepository,JwtTokenProvider jwtTokenProvider) {
		
		this.userRepository=userRepository;
		this.jwtTokenProvider=jwtTokenProvider;
		
	}
	
	@Override
	public User registerUser(User user) throws UserException {
		 return userRepository.save(user);
	}


	@Override
	public User findUserByName(String name) throws UserException {
		User user=userRepository.findByFirstName(name);
		
		if(user !=null){
			return user;
		}
		throw new UserException("user not found!");
		
	}

	
	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User> user=userRepository.findById(userId);
		
		if(user.isPresent()){
			return user.get();
		}
		throw new UserException("user not found with id "+userId);
	}
	

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		System.out.println("user service");
		String email=jwtTokenProvider.getEmailFromJwtToken(jwt);

		User user=userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not exist with email "+email);
		}
		return user;
	}

	

}
