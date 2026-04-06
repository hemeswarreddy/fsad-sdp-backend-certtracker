package com.klef.fsad.sdp.service;

import java.util.Optional;

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

	@Override
	public String updateuserProfile(User user) {
		 Optional<User> optional = userRepository.findById(user.getId());
		 if (optional.isPresent()) {
       User u = optional.get();

           u.setContact(user.getContact());
       
           u.setName(user.getName());
        
   userRepository.save(u);
          return "User Profile Updated Successfully";
      } 
       else 
       {
           return "Customer ID Not Found to Update";       }
	}


}
