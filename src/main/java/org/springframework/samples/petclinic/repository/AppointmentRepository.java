package org.springframework.samples.petclinic.repository;

import javax.validation.Valid;

import org.springframework.samples.petclinic.model.Appointment;
import org.springframework.stereotype.Repository;


public interface AppointmentRepository {

	void save(@Valid Appointment appointment);

	Appointment findAppById(int appointementId);

}
