package org.springframework.samples.petclinic.service;



import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VaccineService {
	@Autowired
	private VaccineRepository vaccineRepo;
	
	@Transactional
	public Vaccine findById(int id) {
		return vaccineRepo.findById(id);	
	}
	
	@Transactional
	public List<Vaccine> findAll(){
		return vaccineRepo.findAll().stream().collect(Collectors.toList());
		
	}
	
	@Transactional
	public List<Vaccine> findAllExpirated(){
		return vaccineRepo.findAll().stream().filter(v -> v.getExpirated()).collect(Collectors.toList());
		
	}
	
	@Transactional
	public void saveVaccine(@Valid Vaccine vaccine) {
		vaccineRepo.save(vaccine);
		
	}
	
	@Transactional
	public void deleteVaccine(@Valid Vaccine vaccine) {
		vaccineRepo.delete(vaccine);
		
	}

	public List<PetType> findPetTypes() {
		
		return vaccineRepo.findPetTypes();
	}
	

	

}
