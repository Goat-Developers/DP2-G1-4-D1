package org.springframework.samples.petclinic.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Appointment;
import org.springframework.samples.petclinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository appRepository;
	
	public void saveAppointment(@Valid Appointment appointment) {
		appRepository.save(appointment);
		
	}

	public Appointment findAppointmentById(int appointementId) {
	
		return this.appRepository.findAppById(appointementId);
	}

}
