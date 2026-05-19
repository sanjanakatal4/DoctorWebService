package com.sanjana.findmydoctor.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjana.findmydoctor.entity.Doctor;
import com.sanjana.findmydoctor.entity.DoctorAvail;
import com.sanjana.findmydoctor.entity.DoctorDetails;
import com.sanjana.findmydoctor.entity.DoctorNotAvail;
import com.sanjana.findmydoctor.entity.DoctorOnline;
import com.sanjana.findmydoctor.repo.DoctorAvailRepo;
import com.sanjana.findmydoctor.repo.DoctorDetailsRepo;
import com.sanjana.findmydoctor.repo.DoctorNotAvailRepo;
import com.sanjana.findmydoctor.repo.DoctorOnlineRepo;
import com.sanjana.findmydoctor.repo.DoctorRepo;


@Service
public class DoctorService {
	@Autowired
	private DoctorRepo doctorRepo;
	@Autowired
	private DoctorAvailRepo doctorAvailRepo;
	@Autowired
	private DoctorDetailsRepo doctorDetailsRepo;
	@Autowired
	private DoctorNotAvailRepo doctorNotAvailRepo;
	@Autowired
	private DoctorOnlineRepo doctorOnlineRepo;
	
	public List<Doctor> getDoctors() {
		return doctorRepo.findAll();
	}

	public List<Doctor> getDoctorsBySpeciality(String speciality) {
		return doctorRepo.findBySpeciality(speciality);
	}
	public List<Doctor> getDoctors(String state, String city, String speciality) {
		return doctorRepo.findByStateAndCityAndSpeciality(state, city, speciality);
	}
	public boolean register(Doctor doctor) {
		if (doctorRepo.existsByEmail(doctor.getEmail())) {
	        return false;
	    }

	    DoctorDetails doctorDetails = new DoctorDetails();
	    DoctorAvail doctorAvail = new DoctorAvail();

	    doctor.setDoctorDetails(doctorDetails);
	    doctor.setDoctorAvail(doctorAvail);

	    doctorRepo.save(doctor); // saves everything (cascade)

	    return true;
	}

	public Doctor getDoctor(String email) {
		return doctorRepo.findById(email).orElse(null);
	}

	public Doctor updateDocAvail(String email, DoctorAvail doctorAvail) {
		Doctor doctor = doctorRepo.findById(email).orElse(null);
		if (doctor != null) {
	        DoctorAvail da = doctor.getDoctorAvail();
	        da.setMax_eve_apmt(doctorAvail.getMax_eve_apmt());
	        da.setMax_mor_apmt(doctorAvail.getMax_mor_apmt());
	        da.setMon_eve(doctorAvail.getMon_eve());
	        da.setMon_mor(doctorAvail.getMon_mor());
	        da.setTue_eve(doctorAvail.getTue_eve());
	        da.setTue_mor(doctorAvail.getTue_mor());
	        da.setWed_eve(doctorAvail.getWed_eve());
	        da.setWed_mor(doctorAvail.getWed_mor());
	        da.setThu_eve(doctorAvail.getThu_eve());
	        da.setThu_mor(doctorAvail.getThu_mor());
	        da.setFri_eve(doctorAvail.getFri_eve());
	        da.setFri_mor(doctorAvail.getFri_mor());
	        da.setSat_eve(doctorAvail.getSat_eve());
	        da.setSat_mor(doctorAvail.getSat_mor());
	        da.setSun_eve(doctorAvail.getSun_eve());
	        da.setSun_mor(doctorAvail.getSun_mor());
	        doctorAvailRepo.save(da);
	        return doctor;
	    }

	    return null;
	}

	public Doctor updateDoctor(Doctor doctor) {
		Doctor existingDoctor = doctorRepo.findById(doctor.getEmail()).orElse(null);
	    if (existingDoctor != null) {
	        // 🔹 Update Doctor fields
	        existingDoctor.setArea(doctor.getArea());
	        existingDoctor.setCity(doctor.getCity());
	        existingDoctor.setName(doctor.getName());
	        existingDoctor.setState(doctor.getState());
	        existingDoctor.setSpeciality(doctor.getSpeciality());
	        // 🔹 Update DoctorDetails
	        DoctorDetails dd = existingDoctor.getDoctorDetails();
	        DoctorDetails newDd = doctor.getDoctorDetails();
	        if (dd != null && newDd != null) {
	            dd.setDob(newDd.getDob());
	            dd.setExperience(newDd.getExperience());
	            dd.setGender(newDd.getGender());
	            dd.setPhone(newDd.getPhone());
	            dd.setQualification(newDd.getQualification());
	        }
	        doctorRepo.save(existingDoctor);
	        doctorDetailsRepo.save(dd);
	        return existingDoctor;
	    }
	    return null;
	}

	public boolean updateDoctorPhoto(String email, byte[] photo) {
		Doctor doctor = doctorRepo.findById(email).orElse(null);
	    if (doctor == null) {
	        return false;
	    }
	    DoctorDetails dd = doctor.getDoctorDetails();
	    if (dd != null) {
	        dd.setPhoto(photo);
	        doctorDetailsRepo.save(dd);
	    }
	    return true;
	}

	public boolean addDocNotAvail(DoctorNotAvail doctorNotAvail) {
		if (doctorNotAvailRepo.existsByDoctorEmailAndDocDate(
	            doctorNotAvail.getDoctorEmail(),
	            doctorNotAvail.getDocDate())) {
	        return false;
	    }
	    doctorNotAvailRepo.save(doctorNotAvail);
	    return true;
	}

	public byte[] getDoctorPhoto(String email) {
		Doctor doctor = doctorRepo.findById(email).orElse(null);
	    if (doctor == null || doctor.getDoctorDetails() == null) {
	        return null;
	    }
	    return doctor.getDoctorDetails().getPhoto();
	}

	public boolean updatePassword(String email, String newPassword) {
		Doctor doctor = doctorRepo.findById(email).orElse(null);
	    if (doctor == null) {
	        return false;
	    }
	    doctor.setPassword(newPassword); 
	    doctorRepo.save(doctor);
	    return true;
	}

	public List<DoctorNotAvail> getDocNotAvail(String email) {
		return doctorNotAvailRepo.findByDoctorEmail(email);
	}

	public boolean cancelDocNotAvail(int id) {
		if (!doctorNotAvailRepo.existsById(id)) {
	        return false;
	    }
	    doctorNotAvailRepo.deleteById(id);
	    return true;
	}

	public DoctorOnline getDoctorOnline(String email) {
		return doctorOnlineRepo.findById(email).orElse(null);
	}

	public DoctorOnline doctorOnline(String email, String speciality) {
		DoctorOnline doctorOnline = doctorOnlineRepo.findById(email).orElse(null);
	    if (doctorOnline == null) {
	        doctorOnline = new DoctorOnline();
	        doctorOnline.setDoctorEmail(email);
	        doctorOnline.setSpeciality(speciality);
	        // room ID generation
	        String roomID = UUID.randomUUID().toString();
	        doctorOnline.setRoomId(roomID);
	        doctorOnlineRepo.save(doctorOnline);
	    }
	    return doctorOnline;
	}

	public void doctorOffline(String email) {
		doctorOnlineRepo.deleteById(email);
	}
}
