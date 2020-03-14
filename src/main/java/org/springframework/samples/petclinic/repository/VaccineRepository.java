package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vaccine;

public interface VaccineRepository  {
	
	List<Vaccine> findAll() throws DataAccessException;

	Vaccine findById(int id) throws DataAccessException;
	

	

}
