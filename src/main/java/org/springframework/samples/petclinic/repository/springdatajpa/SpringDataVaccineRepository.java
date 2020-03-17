package org.springframework.samples.petclinic.repository.springdatajpa;




import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.VaccineRepository;

public interface SpringDataVaccineRepository  extends VaccineRepository, Repository<Vaccine, Integer>{
	
	
	@Query("SELECT v FROM Vaccine v WHERE v.id=?1")
	Vaccine findById (@Param("id") int id);
	

	@Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
	List<PetType> findPetTypes() throws DataAccessException;
	

	
	

}
