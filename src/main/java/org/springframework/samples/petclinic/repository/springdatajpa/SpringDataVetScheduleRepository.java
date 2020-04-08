package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.repository.VetScheduleRepository;

public interface SpringDataVetScheduleRepository extends VetScheduleRepository, Repository<VetSchedule, Integer>{
	
	
	@Query("SELECT vt FROM VetSchedule vt")
	Collection<VetSchedule> findAll();

}
