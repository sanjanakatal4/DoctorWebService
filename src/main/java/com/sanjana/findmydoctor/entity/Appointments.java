package com.sanjana.findmydoctor.entity;

import java.sql.Date;

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
public class Appointments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String doctorEmail;
	private String userEmail;
	private String name;
	private String status;
	
	private Date docBookingDate; //Appointment Day
	private String docBookingTime;
	
	private Date bookingDateTime; //day of booking
	
}
