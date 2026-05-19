package com.sanjana.findmydoctor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjana.findmydoctor.entity.Doctor;
import com.sanjana.findmydoctor.entity.DoctorAvail;
import com.sanjana.findmydoctor.entity.DoctorNotAvail;
import com.sanjana.findmydoctor.entity.DoctorOnline;
import com.sanjana.findmydoctor.service.DoctorService;



@RestController
@RequestMapping("doctor")
public class DoctorController {
	@Autowired
	DoctorService doctorService;
	
	@PostMapping("/register")
	public boolean register(@RequestBody Doctor doctor) {
		return doctorService.register(doctor);
	}

	@GetMapping("/getDoctorOnline/{email}")
	public DoctorOnline getDoctorOnline(@PathVariable String email) {
		return doctorService.getDoctorOnline(email);
	}
	@GetMapping("/getDoctor/{email}")
	public Doctor getDoctors(@PathVariable String email) {
		return doctorService.getDoctor(email);
	}
	@GetMapping("/getDoctors/{state}/{city}/{speciality}")
	public List<Doctor> getDoctors(@PathVariable String state,@PathVariable String city,@PathVariable String speciality) {
		return doctorService.getDoctors(state,city,speciality);
	}
	@GetMapping("/getDoctorsBySpeciality/{speciality}")
	public List<Doctor> getDoctorsBySpeciality(@PathVariable String speciality) {
		return doctorService.getDoctorsBySpeciality(speciality);
	}
	@GetMapping("/getDoctors")
	public List<Doctor> getDoctors() {
		return doctorService.getDoctors();
	}
	@PutMapping("/updateDocAvail/{email}") 
	public Doctor updateDocAvail(@PathVariable String email,@RequestBody DoctorAvail doctorAvail) {
		return doctorService.updateDocAvail(email, doctorAvail);
	}
	@PutMapping("/updateDoctor") 
	public Doctor updateDoctor(@RequestBody Doctor doctor) {
		return doctorService.updateDoctor(doctor);
	}
	@PutMapping("/updateDoctorPhoto/{email}")
	public boolean updateDoctorPhoto(@PathVariable String email,@RequestBody byte[] photo) {
		return doctorService.updateDoctorPhoto(email, photo);
	}
	@GetMapping("/getDoctorPhoto/{email}")
	public byte[] getDoctorPhoto(@PathVariable String email) {
		return doctorService.getDoctorPhoto(email);
	}
	@PostMapping("/addDocNotAvail")
	public boolean addDocNotAvail(@RequestBody DoctorNotAvail doctorNotAvail) {
		return doctorService.addDocNotAvail(doctorNotAvail);
	}
	@GetMapping("/getDocNotAvail/{email}")
	public List<DoctorNotAvail> getDocNotAvail(@PathVariable String email) {
		return doctorService.getDocNotAvail(email);
	}

	@PutMapping(value = {"/updatePassword","/forgetPassword"})
	public boolean updatePassword(@RequestBody Map<String, String> data) {
		String email=data.get("email");
		String newPassword=data.get("newPassword");
		return doctorService.updatePassword(email,newPassword);
	}
	
	@DeleteMapping("/cancelDocNotAvail/{id}")
	public boolean cancelDocNotAvail(@PathVariable int id) {
		return doctorService.cancelDocNotAvail(id);
	}
	@PostMapping("/doctorOnline/{email}/{speciality}")
	public DoctorOnline doctorOnline(@PathVariable String email, @PathVariable String speciality) {
		return doctorService.doctorOnline(email,speciality);
	}
	@DeleteMapping("/doctorOffline/{email}")
	public void doctorOffline(@PathVariable String email) {
		doctorService.doctorOffline(email);
	}
}
