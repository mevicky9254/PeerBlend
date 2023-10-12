package com.PEERBLEND.service;

import com.PEERBLEND.exception.UserException;
import com.PEERBLEND.model.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;

}
