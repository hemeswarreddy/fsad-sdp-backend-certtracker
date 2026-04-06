package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.User;
import com.klef.fsad.sdp.service.AdminService;

@RestController
@RequestMapping("admin")
@CrossOrigin("*")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/")
	public String slash()
	{
		return "Online Certificate Tracker";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> checkadminlogin(@RequestBody Admin admin)
	{
		try
		{
			Admin a = adminService.verifyAdminLogin(admin.getUsername(), admin.getPassword());
		
		    if(a!=null)
		    {
		    	return ResponseEntity.status(200).body(admin);
		    	
		    }
		    else
		    {
		    	return ResponseEntity.status(401).body("Login Invalid");
		    }
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}
	@GetMapping("/viewallusers")
	public ResponseEntity<?> viewallusers() {
		try {
			List<User> user = adminService.ViewallUsers();
			
			
			if(user.size()>0)
			{
				return ResponseEntity.status(200).body(user);
			}
			else
			{
				return ResponseEntity.noContent().build();
			}
			
		} catch (Exception e) 
		{
			return ResponseEntity.status(500).body("Error While Fetching User Details");
			
		}
	}

}
