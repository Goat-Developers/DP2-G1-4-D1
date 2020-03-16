package org.springframework.samples.petclinic.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Vaccine;

public interface VaccineRepository  {
	
	List<Vaccine> findAll() throws DataAccessException;

	Vaccine findById(int id) throws DataAccessException;

	void save(@Valid Vaccine vaccine);
	

	

}
