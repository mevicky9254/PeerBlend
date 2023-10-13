package com.PEERBLEND.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.PEERBLEND.config.JwtTokenProvider;
import com.PEERBLEND.model.User;
import com.PEERBLEND.repository.UserRepository;

@RestController("/users")
public class UserController {
	
	
	private UserRepository userRepository;
	
	private JwtTokenProvider jwtProvider;
	
	public UserController(UserRepository userRepository, JwtTokenProvider jwtProvider) {
		super();
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}
	
	
	@PostMapping("/create/{email}")
	 public ResponseEntity<String>connectPeer(@RequestHeader("Authorization") String jwt,@PathVariable String email2){
		 
		 String email1=jwtProvider.getEmailFromJwtToken(jwt);
		 
		 User user1=userRepository.findByEmail(email1);
		 
		 User user2=userRepository.findByEmail(email2);
		 
		 user1.getPeers().add(user2);
		 
		 user2.getPeers().add(user1);
		 
		 userRepository.save(user1);
		 
		 userRepository.save(user2);
		 
		 return new ResponseEntity<>("Connection created", HttpStatus.CREATED);
	 }

	
	
	

}
