package com.stackroute.moviecruiser.service;

import com.stackroute.moviecruiser.exceptions.UserAlreadyExistsException;
import com.stackroute.moviecruiser.exceptions.UserNotFoundException;
import com.stackroute.moviecruiser.model.User;

public interface UserService  {
	
	boolean saveUser(User user) throws UserAlreadyExistsException;
	
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
