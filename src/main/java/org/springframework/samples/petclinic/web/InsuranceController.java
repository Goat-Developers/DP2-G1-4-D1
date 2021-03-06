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
package org.springframework.samples.petclinic.web;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.Map;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Insurance;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.samples.petclinic.model.Insurances;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller

public class InsuranceController {

	private final InsuranceService insuranceService;
	private final InsuranceBaseService insuranceBaseService;
	private final VaccineService vaccineService;
	private final PetService petService;	
	
	private static final String URL_INSURANCES ="insurances/insuranceList"; 
	
	

	
	
	@Autowired
	public InsuranceController(InsuranceService insuranceService, InsuranceBaseService insuranceBaseService, PetService petService,VaccineService vaccineService) {
		this.insuranceService = insuranceService;
		this.insuranceBaseService = insuranceBaseService;
		this.petService = petService;
		this.vaccineService= vaccineService;
	}
	
	@GetMapping("/insurances")
	public String showInsuranceList(Map<String,Object> model) {
		Insurances insurances = new Insurances();
		insurances.getInsuranceList().addAll(insuranceService.findInsurances());
		//Collection<Double> totalPrice = insurances.getInsuranceList().stream().mapToDouble(x -> x.getInsurancePrice()).collect(Collectors.toList());
		Insurance insurance = new Insurance();
		model.put("insurances",insurances);
		model.put("insurance",insurance);
		return URL_INSURANCES;

	}
	
	@GetMapping("/insurances/{insuranceId}")
	public String showInsuranceDetail(@PathVariable("insuranceId")  int insuranceId, Map<String,Object> model) {
		Insurance insurance = insuranceService.findInsuranceById(insuranceId);
		model.put("insurance",insurance);
		return "insurances/insuranceDetails";
	}
	
	@GetMapping(value ="/insurance/new/{petId}")
	public String initInsuranceCreationForm(Map<String,Object>model, @PathVariable("petId") int id) {
		Insurance insurance = new Insurance();
		Pet pet = this.petService.findPetById(id);
		Collection<InsuranceBase> insuranceBase = this.insuranceBaseService.findInsurancesBasesByPetTypeId(pet.getType().getId());
		Collection<Vaccine> vaccines = this.insuranceService.findVaccinesByPetTypeId(pet.getType().getId());
		Collection<Treatment> treatments = this.insuranceService.findTreatmentsByPetTypeId(pet.getType().getId());
		for(InsuranceBase insurBase: insuranceBase) {
			vaccines.removeAll(insurBase.getVaccines());
			treatments.removeAll(insurBase.getTreatments());
		}
		
		
		pet.setInsurance(insurance);
		model.put("pet", pet);
		model.put("treatments", treatments);
		model.put("vaccines", vaccines);
		model.put("insurance", insurance);
		model.put("insurancebase", insuranceBase);
		return "insurances/createOrUpdateInsuranceForm";
	}
	
	@PostMapping(value ="/insurance/new/{petId}")	

	public String postInsuranceCreationForm(@Valid final Insurance ins, BindingResult result, @ModelAttribute("pet")Pet ptt,Map<String,Object>model) throws DataAccessException, DuplicatedPetNameException, GeneralSecurityException, IOException, MessagingException, URISyntaxException {

		if (result.hasErrors()){


			Collection<InsuranceBase> insuranceBase = this.insuranceBaseService.findInsurancesBasesByPetTypeId(ptt.getType().getId());
			Collection<Vaccine> vaccines = this.insuranceService.findVaccinesByPetTypeId(ptt.getType().getId());
			Collection<Treatment> treatments = this.insuranceService.findTreatmentsByPetTypeId(ptt.getType().getId());
			for(InsuranceBase insurBase: insuranceBase) {
				vaccines.removeAll(insurBase.getVaccines());
				treatments.removeAll(insurBase.getTreatments());
			}
			model.put("treatments", treatments);
			model.put("vaccines", vaccines);
			model.put("insurancebase", insuranceBase);
			return "insurances/createOrUpdateInsuranceForm";
		}else {
			for (Vaccine a: ins.getVaccines()) {
				Vaccine vac =this.vaccineService.findById(a.getId());
				vac.setStock(vac.getStock()-1);
				this.vaccineService.saveVaccine(vac);
			}
			this.insuranceService.sendMessage(ins,ptt);
			ins.getVaccines().addAll(ins.getInsuranceBase().getVaccines());
			ins.getTreatments().addAll(ins.getInsuranceBase().getTreatments());
			this.insuranceService.saveInsurance(ins);
			
			ptt.setInsurance(ins);
			this.petService.savePet(ptt);
			return "redirect:/owners/"+ ptt.getOwner().getId();
		}
	}

	
	
}
