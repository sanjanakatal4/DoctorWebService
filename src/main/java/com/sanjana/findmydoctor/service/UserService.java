package com.sanjana.findmydoctor.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjana.findmydoctor.entity.User;
import com.sanjana.findmydoctor.repo.UserRepo;


@Service
public class UserService {
	@Autowired
	private UserRepo userRepository;

	public boolean register(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
	        return false;
	    }
	    userRepository.save(user);
	    return true;
	}
	public User getUser(String email) {
		return userRepository.findById(email).orElse(null);
	}
	
	public User updateUser(User user) {
		User existingUser = userRepository.findById(user.getEmail()).orElse(null);
	    if (existingUser != null) {
	        existingUser.setPhone(user.getPhone());
	        existingUser.setName(user.getName());
	        existingUser.setDob(user.getDob());
	        existingUser.setGender(user.getGender());
	        userRepository.save(existingUser);
	        return existingUser;
	    }
	    return null;
	}
	
	public boolean updatePhoto(String email, byte[] photo) {
		User user = userRepository.findById(email).orElse(null);
	    if (user == null) {
	        return false;
	    }
	    user.setPhoto(photo); 
        userRepository.save(user);
	    return true;
	}

	public boolean updatePassword(String email, String oldPassword, String newPassword) {
		User user = userRepository.findById(email).orElse(null);
	    if (user == null) {
	        return false;
	    }
	    if (!oldPassword.equals(user.getPassword())) {
	        return false;
	    }
	    user.setPassword(newPassword);
	    userRepository.save(user);
	    return true; 
	}

	public boolean updatePassword(String email, String newPassword) {
		User user = userRepository.findById(email).orElse(null);
	    if (user == null) {
	        return false;
	    }
	    user.setPassword(newPassword);
	    userRepository.save(user);
	    return true; 
	}

	public byte[] getPhoto(String email) {
		User user = userRepository.findById(email).orElse(null);
		return user.getPhoto();
	}
}
