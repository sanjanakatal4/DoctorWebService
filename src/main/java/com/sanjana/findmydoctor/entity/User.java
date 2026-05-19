package com.sanjana.findmydoctor.entity;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 */
@Entity
//----lombok
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
	@Id
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	@Column(length = 10)
	private String phone;
	private Date dob;
	private String gender;
	@Lob
    private byte[] photo;
	
}
