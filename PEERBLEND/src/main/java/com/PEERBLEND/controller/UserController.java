package com.PEERBLEND.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PEERBLEND.config.JwtTokenProvider;
import com.PEERBLEND.exception.UserException;
import com.PEERBLEND.model.User;
import com.PEERBLEND.model.UserDetailsDTO;
import com.PEERBLEND.repository.UserRepository;
import com.PEERBLEND.service.UserService;

@RestController()
@RequestMapping("/users")
public class UserController {
	
	
	private UserRepository userRepository;
	
	private UserService userService;
	
	
	private JwtTokenProvider jwtProvider;
	
	public UserController(UserRepository userRepository, JwtTokenProvider jwtProvider,UserService userService) {
		super();
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
		this.userService=userService;
	}
	
	
	@GetMapping("/hello")
	public String getHello() {
		return "hello ";
	}
	
	
	@GetMapping("/createConnection/{email2}")
	 public ResponseEntity<String>connectPeer(@RequestHeader("Authorization") String jwt,@PathVariable String email2) throws UserException{
		 
		 String email1=jwtProvider.getEmailFromJwtToken(jwt);
		 
		 String message=userService.createConnection(email1, email2);
		 
		 return new ResponseEntity<>(message, HttpStatus.CREATED);
	 }

	
	@GetMapping("/profile")
	 public ResponseEntity<UserDetailsDTO> getProfile(@RequestHeader("Authorization") String jwt) throws UserException{
		 
		 String email=jwtProvider.getEmailFromJwtToken(jwt);
		 
		 UserDetailsDTO userObject=userService.getProfile(email);
		
		 return new ResponseEntity<>(userObject, HttpStatus.CREATED);
	 }
	
	
	@GetMapping("/peerDetails/{email}")
	public ResponseEntity<UserDetailsDTO>getPeerDetails(@RequestHeader ("Authorization") String jwt,@PathVariable String email) throws UserException{
		
		UserDetailsDTO userObject=userService.getPeerDetails(email);
		
		 return new ResponseEntity<>(userObject, HttpStatus.OK);
	    
		
	}

	
	

}
