package org.springframework.samples.petclinic.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Treatment;

public interface TreatmentRepository {
	
	Treatment findById(int id);
	
	List<Treatment> findAll();

	void save(@Valid Treatment treatment);
	
	List<PetType> findPetTypes();


}
