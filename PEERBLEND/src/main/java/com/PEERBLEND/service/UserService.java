package com.PEERBLEND.service;

import com.PEERBLEND.exception.UserException;
import com.PEERBLEND.model.User;
import com.PEERBLEND.model.UserDetailsDTO;

public interface UserService {
	
	public User registerUser(User user) throws UserException;
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserByName(String name)throws UserException;
	
	public String createConnection(String email1,String email2)throws UserException;
	
	public UserDetailsDTO getProfile(String email)throws UserException;
	
	public UserDetailsDTO getPeerDetails(String email)throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

}
