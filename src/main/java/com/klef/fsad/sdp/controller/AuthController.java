package com.klef.fsad.sdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.sdp.dto.AuthRequestDTO;
import com.klef.fsad.sdp.entity.Admin;
import com.klef.fsad.sdp.entity.User;
import com.klef.fsad.sdp.service.AdminService;
import com.klef.fsad.sdp.service.UserService;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class AuthController 
{
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO auth)
    {
        try
        {
            //Check Admin first
            Admin admin = adminService.verifyAdminLogin(auth.getUsername(), auth.getPassword());

            if(admin != null)
            {
                return ResponseEntity.ok("Admin logged in successfully");
            }

            //Check User (username + password)
            User user = userService.verifyUserLogin(auth.getUsername(), auth.getPassword());

            if(user != null)
            {
                return ResponseEntity.ok("User login successfull");
            }

            return ResponseEntity.status(401).body("Invalid Login");

        }
        catch(Exception e)
        {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
    
  
}