package com.klef.fsad.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.sdp.entity.User;
import com.klef.fsad.sdp.service.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String Userslash()
	{
		return "User Controller test";
	}
	
	//user registration
	@PostMapping("/signup")
	public ResponseEntity<?> userregistration(@RequestBody User u)
	{
		try
		{
			String output =userService.userRegistration(u);
			return ResponseEntity.status(201).body(output);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}
	
	//user login
	@PostMapping("login")
	public ResponseEntity<?> verifyuserlogin(@RequestBody User user)
	{
		try
		{
			User u= userService.verifyUserLogin(user.getEmail(), user.getPassword());
			if(u!=null)
		    {
		    	return ResponseEntity.status(200).body(u);
		    }
		    else
		    {
		    	return ResponseEntity.status(401).body("Login Invalid");
		    }
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}

}
