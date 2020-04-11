package org.springframework.samples.petclinic.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Appointment;


public interface AppointmentRepository {

	void save(@Valid Appointment appointment);

	Appointment findAppById(int appointementId);

	List<Appointment> findAll() throws DataAccessException;

}
