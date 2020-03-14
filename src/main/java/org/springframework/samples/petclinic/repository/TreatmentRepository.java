package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Treatment;

public interface TreatmentRepository {
	
	Treatment findById(int id) throws DataAccessException;
	
	List<Treatment> findAll() throws DataAccessException;


}
