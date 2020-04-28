package org.springframework.samples.petclinic.service;





import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Appointment;
import org.springframework.samples.petclinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppointmentService {

	private AppointmentRepository appRepository;
	
	@Autowired
	public AppointmentService(AppointmentRepository appRepository) {
		this.appRepository = appRepository;
	}
	

	public void saveAppointment(Appointment appointment) {

		appRepository.save(appointment);
		
	}

	@Transactional
	public Appointment findAppointmentById(int appointementId) {
		return this.appRepository.findAppById(appointementId);
	}
	
	@Transactional
	public List<Appointment> findAll(){
		return appRepository.findAll().stream().collect(Collectors.toList());
	}

}
