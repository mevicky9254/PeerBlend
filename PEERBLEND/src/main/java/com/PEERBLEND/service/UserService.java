package com.PEERBLEND.service;

import com.PEERBLEND.exception.UserException;
import com.PEERBLEND.model.User;

public interface UserService {
	
	public User registerUser(User user) throws UserException;
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserByName(String name)throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

}
