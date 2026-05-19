package com.sanjana.findmydoctor.service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjana.findmydoctor.entity.Appointments;
import com.sanjana.findmydoctor.entity.Doctor;
import com.sanjana.findmydoctor.entity.DoctorNotAvail;
import com.sanjana.findmydoctor.repo.AppointmentsRepo;
import com.sanjana.findmydoctor.repo.DoctorNotAvailRepo;
import com.sanjana.findmydoctor.repo.DoctorRepo;

@Service
public class AppointmentService {
	@Autowired
	AppointmentsRepo appointmentsRepo;
	@Autowired
	DoctorRepo doctorRepo;
	@Autowired
	DoctorNotAvailRepo doctorNotAvailRepo;

	public List<Appointments> getByUserEmail(String userEmail) {
		return appointmentsRepo.getByUserEmail(userEmail);
	}

	public List<Appointments> getByDoctorEmail(String doctorEmail) {
		return appointmentsRepo.getByDoctorEmail(doctorEmail);
	}
	
	public boolean delete(int id) {
		appointmentsRepo.deleteById(id);
		return true;
	}

//	public String addAppointment(Appointments appointment) {
//		return appointmentsRepo.addAppointment(appointment);
//	}
	public String addAppointment(Appointments appointment) {
	//	Session session = entityManager.unwrap(Session.class);
	
		String user_email = appointment.getUserEmail();
		String doctor_email = appointment.getDoctorEmail();
		java.util.Date doc_booking_date = appointment.getDocBookingDate();
		String doc_booking_time = appointment.getDocBookingTime();
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		appointment.setBookingDateTime(currentDate);
	
		if (doc_booking_date.after(currentDate) || currentDate.toString().equals(doc_booking_date.toString())) {
			List<Appointments> ll = appointmentsRepo.findByDocBookingTimeAndDocBookingDateAndUserEmailAndStatus(
					doc_booking_time, 
					doc_booking_date,
					user_email, 
					"pending");
			if(!(ll.isEmpty())) {
				return "You Already Booked!";
			}
			List<DoctorNotAvail> list = doctorNotAvailRepo.findByDoctorEmailAndDocDate(doctor_email, doc_booking_date);
			if (list.isEmpty()) {
	
				Doctor doctor = doctorRepo.findById(doctor_email).orElse(null);
	
				Calendar cal = Calendar.getInstance();
				cal.setTime(doc_booking_date);
				LocalDate localDate = LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH) + 1,cal.get(Calendar.DAY_OF_MONTH));
				
				String day   = localDate.getDayOfWeek().toString();
				String btime=day.substring(0, 3)+"_"+doc_booking_time.substring(0, 3);
				String docTime="";
				switch(btime.toLowerCase()) {
					case "mon_mor": docTime=doctor.getDoctorAvail().getMon_mor();break;
					case "mon_eve": docTime=doctor.getDoctorAvail().getMon_eve();break;
					case "tue_mor": docTime=doctor.getDoctorAvail().getTue_mor();break;
					case "tue_eve": docTime=doctor.getDoctorAvail().getTue_eve();break;
					case "wed_mor": docTime=doctor.getDoctorAvail().getWed_mor();break;
					case "wed_eve": docTime=doctor.getDoctorAvail().getWed_eve();break;
					case "thu_mor": docTime=doctor.getDoctorAvail().getThu_mor();break;
					case "thu_eve": docTime=doctor.getDoctorAvail().getThu_eve();break;
					case "fri_mor": docTime=doctor.getDoctorAvail().getFri_mor();break;
					case "fri_eve": docTime=doctor.getDoctorAvail().getFri_eve();break;
					case "sat_mor": docTime=doctor.getDoctorAvail().getSat_mor();break;
					case "sat_eve": docTime=doctor.getDoctorAvail().getSat_eve();break;
					case "sun_mor": docTime=doctor.getDoctorAvail().getSun_mor();break;
					case "sun_eve": docTime=doctor.getDoctorAvail().getSun_eve();break;
				}
				if(docTime==null || docTime.trim().equals("")) {
					return "Doctor not availbale on this time!";
				}else {
					List<Appointments> l = appointmentsRepo.findByDocBookingTimeAndDocBookingDateAndDoctorEmail(doc_booking_time, doc_booking_date, doctor_email);
	
					int totalBooked = l.size();
					int maxBooking;
					if (doc_booking_time.equalsIgnoreCase("morning")) {
						maxBooking = doctor.getDoctorAvail().getMax_mor_apmt();
					} else {
						maxBooking = doctor.getDoctorAvail().getMax_eve_apmt();
					}
					if (maxBooking - totalBooked > 0) {
						appointmentsRepo.save(appointment);
						return "Booking Successfull!";
					} else {
						return "Booking Falied! (Slots Full)";
					}
				}
			} else {
				return "Doctor on Leave!";
			}
		} else {
			return "Wrong Date Selected!";
		}
	}
	public Appointments updateStatus(int id, String status) {
		Appointments appointment = appointmentsRepo.findById(id).orElse(null);
	    if (appointment != null) {
	        appointment.setStatus(status);
	        return appointmentsRepo.save(appointment);
	    }
	    return null;
	}
	
}
