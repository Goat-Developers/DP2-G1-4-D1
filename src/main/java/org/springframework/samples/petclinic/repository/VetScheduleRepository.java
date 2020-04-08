package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.stereotype.Repository;

@Repository
public interface VetScheduleRepository {

	Collection<VetSchedule> findAll();

	void save(VetSchedule vtSchedule);

}
