package com.sanjana.findmydoctor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class DoctorAvail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String mon_mor;
	private String mon_eve;
	private String tue_mor;
	private String tue_eve;
	private String wed_mor;
	private String wed_eve;
	private String thu_mor;
	private String thu_eve;
	private String fri_mor;
	private String fri_eve;
	private String sat_mor;
	private String sat_eve;
	private String sun_mor;
	private String sun_eve;
	private int max_mor_apmt;
	private int max_eve_apmt;
	
	
}
