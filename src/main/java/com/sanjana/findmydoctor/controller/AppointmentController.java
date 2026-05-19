package com.sanjana.findmydoctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjana.findmydoctor.entity.Appointments;
import com.sanjana.findmydoctor.service.AppointmentService;
import com.sanjana.findmydoctor.service.MailService;



@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	MailService mailService;
	
	@DeleteMapping("/deleteAppointment/{id}")
	public boolean deleteAppointment(@PathVariable int id) {
		return appointmentService.delete(id);
	}
	@PutMapping("/updateAppointmentStatus/{id}/{status}")
	public boolean updateAppointmentStatus(@PathVariable int id,@PathVariable String status) {
		Appointments appointment=appointmentService.updateStatus(id,status);
		if(appointment==null) {
			return false;
		}else {
			//Html mail
			String m="<h1>Booking Status!</h1> <hr/> <p style='background-color:yellow;'>"+status+"</p>";

			mailService.htmlMail(appointment.getUserEmail(), "Doctors- Appointment Status!", m);
			mailService.htmlMail(appointment.getDoctorEmail(), "Doctors- Appointment Status!", m);
			return true;
		}
	}
	@GetMapping("/getByUserEmail/{email}")
	public List<Appointments> getByUserEmail(@PathVariable String email) {
		return appointmentService.getByUserEmail(email);
	}
	@GetMapping("/getByDoctorEmail/{email}")
	public List<Appointments> getByDoctorEmail(@PathVariable String email) {
		return appointmentService.getByDoctorEmail(email);
	}
	@PostMapping("/addAppointment")
	public String addAppointment(@RequestBody Appointments	appointment) {
		appointment.setStatus("Pending");
		String r=appointmentService.addAppointment(appointment);
		if(r.contains("Success")) {
			//Simple mail
//			String m="Booking Successfully! Date: "+appointment.getDoc_booking_date()+" Time: "+appointment.getBooking_date_time();
//			mailSend.doMailSend(appointment.getUser_email(), "Doctors- Appointment Booked!", m);
			//Html mail
			String m="<h1>Booking Successfully!</h1> <hr/> <p style='background-color:yellow;'>Date: "+appointment.getDocBookingDate()+" Time: "+appointment.getBookingDateTime()+"</p>";

			mailService.htmlMail(appointment.getUserEmail(), "Doctors- Appointment Booked!", m);
		}
		return r;
	}
	
}
