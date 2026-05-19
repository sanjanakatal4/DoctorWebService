package com.sanjana.findmydoctor.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
//----lombok
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
	@Id
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	private String state;
	private String city;
	private String area;
	private String speciality;
	private int fee;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DoctorDetails doctorDetails;
	@OneToOne(cascade = CascadeType.ALL)
	private DoctorAvail doctorAvail;
	
}
