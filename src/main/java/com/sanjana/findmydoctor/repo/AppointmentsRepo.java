package com.sanjana.findmydoctor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanjana.findmydoctor.entity.Appointments;


@Repository
public interface AppointmentsRepo extends JpaRepository<Appointments,Integer> {
	public List<Appointments> getByUserEmail(String userEmail);
	public List<Appointments> getByDoctorEmail(String doctorEmail);
	public List<Appointments> findByDocBookingTimeAndDocBookingDateAndUserEmailAndStatus(
            String docBookingTime,
            java.util.Date  docBookingDate,
            String userEmail,
            String status
    );
	public List<Appointments> findByDocBookingTimeAndDocBookingDateAndDoctorEmail(
            String docBookingTime,
            java.util.Date docBookingDate,
            String doctorEmail
    );
}
