package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.repository.VetScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class VetScheduleService {

	@Autowired
	VetScheduleRepository vSchRepo;

	public Collection<VetSchedule> findAll() {
		
		return this.vSchRepo.findAll();
	}

	public void saveVtSchedule(VetSchedule vtSchedule) {
		 this.vSchRepo.save(vtSchedule);
		
	}
}
