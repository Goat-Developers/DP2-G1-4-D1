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

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Announcement;
import org.springframework.samples.petclinic.model.Insurance;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.samples.petclinic.model.Insurances;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class InsuranceController {

private final InsuranceService insuranceService;
private final PetService petService;	
private final InsuranceBaseService insuranceBaseService;
	private static final String URL_INSURANCES ="insurances/insuranceList"; 
	
	@Autowired
	public InsuranceController(InsuranceService insuranceService, PetService petService, InsuranceBaseService insuranceBaseService) {
		this.insuranceService = insuranceService;
		this.petService = petService;
		this.insuranceBaseService = insuranceBaseService;
	}
	


	@GetMapping("/insurances")
	public String showInsuranceList(Map<String,Object> model) {
		
		Insurances insurances = new Insurances();
		insurances.getInsuranceList().addAll(insuranceService.findInsurances());
		Insurance insurance = new Insurance();
		model.put("insurances",insurances);
		model.put("insurance",insurance);
		return URL_INSURANCES;
		
	}
	
	@GetMapping("/insurances/{insuranceId}")
	public String ShowInsuranceDetail(@PathVariable("insuranceId")  int insuranceId, Map<String,Object>  model ) {
		Insurance a = insuranceService.findInsuranceById(insuranceId);
		model.put("insurance",a);
		return "insurances/insuranceDetails";
	}
	
	@GetMapping(value ="/insurance/new")
	public String initAnnouncementCreationForm(Map<String,Object>model) {
		Insurance insurance = new Insurance();
		Collection<String> insuranceBase = this.insuranceBaseService.findInsurancesBases().stream().map(InsuranceBase::getConditions).collect(Collectors.toList());
		model.put("insurance", insurance);
		model.put("insurancebase", insuranceBase);
		return "insurances/createOrUpdateInsuranceForm";
	}
	@PostMapping(value ="/insurance/new")
	public String initAnnouncementCreationForm(@Valid Insurance insurance, BindingResult result) {
		if (result.hasErrors()){
			return "insurances/createOrUpdateInsuranceForm";
		}else {
			this.petService.saveInsurance(insurance);
			this.insuranceService.saveInsurance(insurance);
			return "redirect:/insurances";
		}
		
		
	}

}
