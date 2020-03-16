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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.samples.petclinic.repository.InsuranceBaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsuranceBaseService {

	private InsuranceBaseRepository insuranceBaseRepository;

	@Autowired
	public InsuranceBaseService(InsuranceBaseRepository insuranceBaseRepository) {
		this.insuranceBaseRepository = insuranceBaseRepository;
	}
	
	@Transactional(readOnly = true)	
	public Collection<InsuranceBase> findInsurancesBases() throws DataAccessException {
		return insuranceBaseRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Collection<InsuranceBase> findInsurancesBasesByPetTypeId(int id) throws DataAccessException {
		return insuranceBaseRepository.findByPetTypeId(id);
	}
	
	public InsuranceBase findInsuranceBaseById(int id) {
		return insuranceBaseRepository.findById(id);
	}
}
