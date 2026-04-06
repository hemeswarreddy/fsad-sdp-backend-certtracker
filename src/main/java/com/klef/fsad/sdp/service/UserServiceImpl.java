package com.klef.fsad.sdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.User;
import com.klef.fsad.sdp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public String userRegistration(User user) {
		userRepository.save(user);//add user
		return "User registered successfully ";
	}

	@Override
	public User verifyUserLogin(String email, String pwd) {
		return userRepository.findByEmailAndPassword(email, pwd);
	}

}
