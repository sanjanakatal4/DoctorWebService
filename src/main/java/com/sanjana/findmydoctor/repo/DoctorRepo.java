package com.sanjana.findmydoctor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanjana.findmydoctor.entity.Doctor;



@Repository
public interface DoctorRepo extends JpaRepository<Doctor, String> {


	List<Doctor> findBySpeciality(String speciality);
	List<Doctor> findByStateAndCityAndSpeciality(String state,String city,String speciality);
	boolean existsByEmail(String doctorEmail);
//	boolean existsByEmailAndDocDate(String doctorEmail, java.util.Date docDate);

}
