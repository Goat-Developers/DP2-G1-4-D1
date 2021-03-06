/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Collection;


import javax.mail.MessagingException;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Insurance;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.InsuranceRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.samples.petclinic.util.GmailQuickstart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class InsuranceService {

	private InsuranceRepository insuranceRepository;

	@Autowired
	public InsuranceService(InsuranceRepository insuranceRepository) {
		this.insuranceRepository = insuranceRepository;
	}
	@Transactional(readOnly = true)	
	@Cacheable("insurances")
	public Collection<Insurance> findInsurances() {
		return insuranceRepository.findAllWithTreatmentsAndVaccines();
	}

	@Transactional(readOnly = true)
	@Cacheable("insuranceById")
	public Insurance findInsuranceById(int id) {
		return insuranceRepository.findById(id);
	}
	
	@Transactional(rollbackFor = DuplicatedPetNameException.class)
	@CacheEvict(cacheNames="insuranceById", allEntries=true)
	public void saveInsurance(Insurance insurance) {
        insuranceRepository.save(insurance);                
	}
	

	@Transactional(readOnly = true)
	public Collection<Vaccine> findVaccines() {
		return insuranceRepository.findVaccines();
	}
	@Transactional(readOnly = true)
	@Cacheable("vaccinesByPetTypeId")
	public Collection<Vaccine> findVaccinesByPetTypeId(int id) {
		return insuranceRepository.findVaccinesByPetTypeId(id);
	}
	@Transactional(readOnly = true)
	public Collection<Treatment> findTreatments() {
		return insuranceRepository.findTreatments();
	}
	@Transactional(readOnly = true)
	@Cacheable("treatmentsByPetTypeId")

	public Collection<Treatment> findTreatmentsByPetTypeId(int id) {
		return insuranceRepository.findTreatmentsByPetTypeId(id);
	}
	
	public void sendMessage(@Valid Insurance insurance, Pet pet) throws GeneralSecurityException, IOException, MessagingException, URISyntaxException {
		GmailQuickstart.SendingEmail(insurance,pet);
	}
}