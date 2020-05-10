package org.springframework.samples.petclinic.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.VaccinationSchedule;

public interface VaccinationScheduleRepository {
	
	
	void save(VaccinationSchedule vaccinationSchedule);

	VaccinationSchedule findById(int id) throws DataAccessException;;

}
