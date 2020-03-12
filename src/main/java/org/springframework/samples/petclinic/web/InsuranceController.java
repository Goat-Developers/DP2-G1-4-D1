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

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Insurance;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class InsuranceController {

	private final PetService petService;

	@Autowired
	public InsuranceController(PetService petService) {
		this.petService = petService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("insurance")
	public Insurance loadPetWithInsurance(@PathVariable("petId") int petId) {
		Pet pet = this.petService.findPetById(petId);
		Insurance insurance = new Insurance();
		pet.setInsurance(insurance);
		return insurance;
	}

	@GetMapping(value = "/owners/*/pets/{petId}/insurance/new")
	public String initNewInsuranceForm(@PathVariable("petId") int petId, Map<String, Object> model) {
		return "pets/createOrUpdateInsuranceForm";
	}

	@PostMapping(value = "/owners/{ownerId}/pets/{petId}/insurance/new")
	public String processNewInsuranceForm(@Valid Insurance insurance, BindingResult result) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateInsuranceForm";
		}
		else {
			this.petService.saveInsurance(insurance);
			return "redirect:/owners/{ownerId}";
		}
	}

	@GetMapping(value = "/owners/*/pets/{petId}/insurance")
	public String showInsurance(@PathVariable int petId, Map<String, Object> model) {
		model.put("insurance", this.petService.findPetById(petId).getInsurance());
		return "insurance";
	}


}
