package org.springframework.samples.petclinic.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vaccine;

public interface VaccineRepository  {
	
	List<Vaccine> findAll();

	Vaccine findById(int id);

	void save(@Valid Vaccine vaccine);
	
	List<PetType> findPetTypes();
	
	void delete(@Valid Vaccine vaccine);
	
	List<Vaccine> findVaccinesWithLowStock();
	

}
