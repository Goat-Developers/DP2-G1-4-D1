package org.springframework.samples.petclinic.repository;

import org.springframework.samples.petclinic.model.VaccinationSchedule;

public interface VaccinationScheduleRepository {
	
	
	void save(VaccinationSchedule vaccinationSchedule);

	VaccinationSchedule findById(int id);

}
