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
import com.PEERBLEND.model.User;
import com.PEERBLEND.model.UserDetailsDTO;
import com.PEERBLEND.repository.UserRepository;

@RestController()
@RequestMapping("/users")
public class UserController {
	
	
	private UserRepository userRepository;
	
	private JwtTokenProvider jwtProvider;
	
	public UserController(UserRepository userRepository, JwtTokenProvider jwtProvider) {
		super();
		this.userRepository = userRepository;
		this.jwtProvider = jwtProvider;
	}
	
	@GetMapping("/hello")
	public String getHello() {
		return "hello ";
	}
	
	@GetMapping("/createConnection/{email2}")
	 public ResponseEntity<String>connectPeer(@RequestHeader("Authorization") String jwt,@PathVariable String email2){
		 
		 String email1=jwtProvider.getEmailFromJwtToken(jwt);
		 
		 User user1=userRepository.findByEmail(email1);
		 
		 User user2=userRepository.findByEmail(email2);
		 
		 user1.getPeers().add(user2.getId());
		 
		 userRepository.save(user1);
		 
		 return new ResponseEntity<>("Connection created", HttpStatus.CREATED);
	 }

	@GetMapping("/profile")
	 public ResponseEntity<UserDetailsDTO> getProfile(@RequestHeader("Authorization") String jwt){
		 
		 String email=jwtProvider.getEmailFromJwtToken(jwt);
		 
		 User user=userRepository.findByEmail(email);
		 
		 List<Long> userids = user.getPeers();
		 UserDetailsDTO userObject = new UserDetailsDTO();
		 for(Long i : userids) {
			 User users = userRepository.findById(i).get();
			 userObject.getUsers().add(users);
		 }
		 userObject.setId(user.getId());
		 userObject.setFirstName(user.getFirstName());
		 userObject.setLastName(user.getLastName());
		 userObject.setEmail(user.getEmail());
		 userObject.setMobile(user.getMobile());
		 userObject.setRole(user.getRole());
		 return new ResponseEntity<>(userObject, HttpStatus.CREATED);
	 }

	
	

}
