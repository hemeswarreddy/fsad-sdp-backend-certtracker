package com.klef.fsad.sdp.service;

import com.klef.fsad.sdp.entity.User;

public interface UserService {
	public String userRegistration(User user);
	public User verifyUserLogin(String email, String pwd);
	

}
