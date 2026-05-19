package com.sanjana.findmydoctor.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanjana.findmydoctor.entity.DoctorOnline;



@Repository
public interface DoctorOnlineRepo extends JpaRepository<DoctorOnline, String> {

}
