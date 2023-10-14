package com.PEERBLEND.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.PEERBLEND.config.JwtTokenProvider;
import com.PEERBLEND.exception.UserException;
import com.PEERBLEND.model.User;
import com.PEERBLEND.model.UserDetailsDTO;
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
	

	@Override
	public String createConnection(String email1,String email2) throws UserException {
		
		 User user1=userRepository.findByEmail(email1);
		 
		 User user2=userRepository.findByEmail(email2);
		 
		 user1.getPeers().add(user2.getId());
		 user2.getPeers().add(user1.getId());
		 
		 userRepository.save(user1);
		 userRepository.save(user2);
		 
		 return "Connection created with "+user2.getFirstName();
		 
		
	}

	@Override
	public UserDetailsDTO getProfile(String email)throws UserException{
		
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
		 userObject.setProfileImage(user.getProfileImage());
		 userObject.setEmail(user.getEmail());
		 userObject.setMobile(user.getMobile());
		 userObject.setRole(user.getRole());
		 return userObject;
	}

	
	@Override
	public UserDetailsDTO getPeerDetails(String email)throws UserException {
		
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
		 userObject.setProfileImage(user.getProfileImage());
		 userObject.setEmail(user.getEmail());
		 userObject.setMobile(user.getMobile());
		 userObject.setRole(user.getRole());
		 return userObject;
	}

	

}
