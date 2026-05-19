package com.sanjana.findmydoctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanjana.findmydoctor.entity.DoctorDetails;



@Repository
public interface DoctorDetailsRepo extends JpaRepository<DoctorDetails, Integer> {

}
