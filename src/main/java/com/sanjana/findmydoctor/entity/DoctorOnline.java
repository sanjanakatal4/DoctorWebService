package com.sanjana.findmydoctor.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class DoctorOnline {
	@Id
	private String doctorEmail;
	private String userEmail;
	@Column(nullable = false)
	private String speciality;
	private String roomId;
	
}
