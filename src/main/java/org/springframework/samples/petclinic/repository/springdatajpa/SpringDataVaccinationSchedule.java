package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.samples.petclinic.model.VaccinationSchedule;
import org.springframework.samples.petclinic.repository.VaccinationScheduleRepository;
import org.springframework.data.repository.Repository;


public interface SpringDataVaccinationSchedule extends VaccinationScheduleRepository, Repository<VaccinationSchedule,Integer>{

}
