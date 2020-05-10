package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Appointment;

import org.springframework.samples.petclinic.repository.AppointmentRepository;

public interface SpringDataAppointmentRepository extends AppointmentRepository, Repository<Appointment, Integer>{
	
	@Override
	@Query("SELECT app from Appointment app where app.id=?1")
	Appointment findAppById(@Param("id") int appointementId);

}
