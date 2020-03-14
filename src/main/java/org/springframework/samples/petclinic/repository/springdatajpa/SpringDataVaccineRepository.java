package org.springframework.samples.petclinic.repository.springdatajpa;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.VaccineRepository;

public interface SpringDataVaccineRepository  extends VaccineRepository, Repository<Vaccine, Integer>{
	
	
	@Query("SELECT v FROM Vaccine v WHERE v.id=?1")
	Vaccine findById (@Param("id") int id);
	

	
	

}
