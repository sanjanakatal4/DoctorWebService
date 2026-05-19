package com.sanjana.findmydoctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanjana.findmydoctor.entity.User;



@Repository
public interface UserRepo extends JpaRepository<User, String> {
	boolean existsByEmail(String email);
}
