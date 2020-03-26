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

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class InsuranceBaseServiceTests {                
     
	@Autowired
	protected InsuranceBaseService insuranceBaseService;
	
	@Test
	void shouldFindAll() {
		Collection<InsuranceBase> segurosBase = this.insuranceBaseService.findInsurancesBases();
		assertThat(segurosBase.size()).isEqualTo(6);
	}
	
	@Test
	void shouldFindById() {
		InsuranceBase seguroBase = this.insuranceBaseService.findInsuranceBaseById(1);
		assertThat(seguroBase.getId()).isEqualTo(1);
	}
	@Test
	void shouldfindInsuranceBaseByPetTypeId() {
		Collection<InsuranceBase> segurosBase = this.insuranceBaseService.findInsurancesBasesByPetTypeId(1);
		assertThat(segurosBase.size()).isEqualTo(1);

		segurosBase = this.insuranceBaseService.findInsurancesBasesByPetTypeId(100);
		assertThat(segurosBase.isEmpty()).isTrue();
	}	
	

}
