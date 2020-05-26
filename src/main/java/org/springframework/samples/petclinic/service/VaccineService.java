package org.springframework.samples.petclinic.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VaccineService {
	

	
	@Autowired
	private VaccineRepository vaccineRepo;

	@Autowired
	public VaccineService(VaccineRepository vacRepository) {
		this.vaccineRepo = vacRepository;
	}


	@Transactional
	@Cacheable("vaccineById")
	public Vaccine findById(int id) {
		return vaccineRepo.findById(id);	
	}
	
	@Transactional(readOnly = true)
	@Cacheable("allVaccines")
	public List<Vaccine> findAll(){
		return vaccineRepo.findAll().stream().collect(Collectors.toList());
		
	}
	
	@Transactional(readOnly = true)
	@Cacheable("allVaccinesExpirated")
	public List<Vaccine> findAllExpirated(){
		return vaccineRepo.findAll().stream().filter(v -> expirado(v)).collect(Collectors.toList());	
	}
	
	private Boolean expirado(Vaccine v) {
		return v.getExpirated();
	}

	
	@Transactional
	@CacheEvict(cacheNames = "vaccineById", allEntries = true)
	public void saveVaccine(@Valid Vaccine vaccine) {
		vaccineRepo.save(vaccine);
		
	}
	
	@Transactional
	public void deleteVaccine(@Valid Vaccine vaccine) {
		vaccineRepo.delete(vaccine);
		
	}

	@Transactional(readOnly = true)
	public List<PetType> findPetTypes() {
		
		return vaccineRepo.findPetTypes();
	}
	
	@Transactional(readOnly = true)
	@Cacheable("vaccinesLow")
	public List<Vaccine> findVaccinesWithLowStock(){
		
		return vaccineRepo.findVaccinesWithLowStock();
	}

	

}
