package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.repository.TreatmentRepository;


public interface SpringDataTreatmentRepository  extends TreatmentRepository, Repository<Treatment, Integer>{
	
	@Query("SELECT t FROM Treatment t WHERE t.id=?1")
	Treatment findById (@Param("id") int id);
	
	@Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
	List<PetType> findPetTypes();
	
	@Query("SELECT t FROM Treatment t ORDER BY t.id")
	List<Treatment> findAll();

}
