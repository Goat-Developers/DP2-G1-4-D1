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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.samples.petclinic.model.InsurancesBases;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class InsuranceBaseController {

private final InsuranceBaseService insuranceBaseService;
	
	private static final String URL_INSURANCES_BASES ="insurances_bases/insuranceList"; 

	@Autowired
	public InsuranceBaseController(InsuranceBaseService insuranceBaseService) {
		this.insuranceBaseService = insuranceBaseService;
	}

	@GetMapping("/insurances_bases")
	public String showInsuranceBaseList(Map<String,Object> model) {
		InsurancesBases insurances = new InsurancesBases();
		insurances.getInsuranceBaseList().addAll(insuranceBaseService.findInsurancesBases());
		InsuranceBase insurance = new InsuranceBase();
		model.put("insurances_bases",insurances);
		model.put("insurance_base",insurance);
		return URL_INSURANCES_BASES;
		
	}
	
	@GetMapping("/insurances_bases/{insuranceBaseId}")
	public String ShowAInsuranceBaseDetail(@PathVariable("insuranceBaseId")  int insuranceId, Map<String,Object>  model ) {
		InsuranceBase a = insuranceBaseService.findInsuranceBaseById(insuranceId);
		model.put("insurance_base",a);
		return "insurances_bases/insuranceDetails";
	}

}
