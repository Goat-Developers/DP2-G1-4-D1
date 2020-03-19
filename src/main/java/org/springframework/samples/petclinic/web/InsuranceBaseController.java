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
	
	private static final String URL_INSURANCES_BASES ="insurancesbases/insuranceList"; 

	@Autowired
	public InsuranceBaseController(InsuranceBaseService insuranceBaseService) {
		this.insuranceBaseService = insuranceBaseService;
	}

	@GetMapping("/insurancesbases")
	public String showInsuranceBaseList(Map<String,Object> model) {
		InsurancesBases insurancesBases = new InsurancesBases();
		insurancesBases.getInsuranceBaseList().addAll(insuranceBaseService.findInsurancesBases());
		InsuranceBase insuranceBase = new InsuranceBase();
		model.put("insurances_bases",insurancesBases);
		model.put("insurance_base",insuranceBase);
		return URL_INSURANCES_BASES;
	}
	
	@GetMapping("/insurancesbases/{insuranceBaseId}")
	public String ShowAInsuranceBaseDetail(@PathVariable("insuranceBaseId")  int insuranceBaseId, Map<String,Object> model) {
		InsuranceBase insuranceBase = insuranceBaseService.findInsuranceBaseById(insuranceBaseId);
		model.put("insurance_base",insuranceBase);
		return "insurancesbases/insuranceDetails";
	}

}
