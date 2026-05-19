package com.sanjana.findmydoctor.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class DoctorDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 10)
	private String phone;
	//@Temporal(TemporalType.DATE) //Optional
	private Date dob;
	private String qualification;
	@Column(nullable = true)
	private int experience;
	private String gender;
	@Column(nullable = true , columnDefinition = "longblob")
    private byte[] photo;

}
