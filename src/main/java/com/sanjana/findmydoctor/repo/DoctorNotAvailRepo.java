package com.sanjana.findmydoctor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanjana.findmydoctor.entity.DoctorNotAvail;



@Repository
public interface DoctorNotAvailRepo extends JpaRepository<DoctorNotAvail, Integer> {
	boolean existsByDoctorEmailAndDocDate(String doctorEmail, java.util.Date docDate);
	List<DoctorNotAvail> findByDoctorEmail(String doctorEmail);
	public List<DoctorNotAvail> findByDoctorEmailAndDocDate(
            String doctorEmail,
            java.util.Date docDate
    );
}
