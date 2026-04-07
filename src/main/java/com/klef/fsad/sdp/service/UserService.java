package com.klef.fsad.sdp.service;

import com.klef.fsad.sdp.entity.User;

public interface UserService {
	public String userRegistration(User user);//for user-self-registration
	public User verifyUserLogin(String email, String pwd);
	
	 public String updateuserProfile(User user);
	 
	 public User getUserByUsername(String username);
}
