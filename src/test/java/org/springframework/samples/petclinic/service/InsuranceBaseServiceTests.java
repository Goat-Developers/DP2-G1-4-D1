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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.repository.InsuranceBaseRepository;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace=Replace.NONE)
class InsuranceBaseServiceTests {                
     
	@Autowired
	protected InsuranceBaseService insuranceBaseService;
	
	@Mock
	private InsuranceBaseRepository insuranceBaseRepository;
	
	private InsuranceBase sampleInsuranceBase;
	
	@BeforeEach
	void setup() {
		Vaccine sampleVaccine = new Vaccine();
	    PetType human = new PetType();
	    sampleVaccine.setId(1);
	    sampleVaccine.setInformation("Vacuna del coronavirus en pruebas, testeado en monos");
	    sampleVaccine.setExpiration(LocalDate.of(2021, Month.APRIL, 3));
	    sampleVaccine.setName("Vacuna contra el coronavirus");
	    sampleVaccine.setPetType(human);
	    sampleVaccine.setPrice(75.0);
	    sampleVaccine.setProvider("China");
	    sampleVaccine.setSideEffects("Puede provocar crisis nerviosas");
	    sampleVaccine.setStock(235);
	    
	    Treatment sampleTreatment = new Treatment();
	    PetType raton = new PetType();
	    sampleTreatment.setId(2);
	    sampleTreatment.setPetType(raton);
	    sampleTreatment.setPrice(25.0);
	    sampleTreatment.setType("Tratamiento contra el aburrimiento");
	    sampleTreatment.setDescription("Para que no te arranques los pelos este mes");
	    
	    Set<Vaccine> vacunas = new HashSet<Vaccine>();
		vacunas.add(sampleVaccine);
		Set<Treatment> tratamientos = new HashSet<Treatment>();
		tratamientos.add(sampleTreatment);
	    
	    sampleInsuranceBase = new InsuranceBase();
		PetType mascota = new PetType();
		sampleInsuranceBase.setId(3);
		sampleInsuranceBase.setName("Seguro para ganar dinero");
		sampleInsuranceBase.setPetType(mascota);
		sampleInsuranceBase.setVaccines(vacunas);
		sampleInsuranceBase.setTreatments(tratamientos);
		sampleInsuranceBase.setConditions("Ser rico");
	}
	
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
	
	@ParameterizedTest
	@ValueSource(ints= {-8,0,90})
	void shouldNotFindInsuranceBaseByPetTypeId(Integer argument) {
		Collection<InsuranceBase> segurosBase = this.insuranceBaseService.findInsurancesBasesByPetTypeId(argument);
		assertThat(segurosBase);

	}
	
	@Test
    void shouldFindInsurancesBases() {
		List<InsuranceBase> sampleIBS = new ArrayList<InsuranceBase>();
	 	sampleIBS.add(sampleInsuranceBase);
        when(insuranceBaseRepository.findAll()).thenReturn(sampleIBS);

        InsuranceBaseService service = new InsuranceBaseService(insuranceBaseRepository);
	    Collection<InsuranceBase> insurancesBases = service.findInsurancesBases();
	    assertThat(insurancesBases).hasSize(1);
	    InsuranceBase IB = insurancesBases.iterator().next();
	    assertThat(IB.getId()).isEqualTo(3);
	    assertThat(IB.getName()).isEqualTo("Seguro para ganar dinero");
    }
	
	@Test
    void shouldNotFindInsurancesBases() {
	   	List<InsuranceBase> sampleIBS = new ArrayList<InsuranceBase>();
		sampleIBS.add(sampleInsuranceBase);
	    when(insuranceBaseRepository.findAll()).thenReturn(sampleIBS);
	    
	    InsuranceBaseService service = new InsuranceBaseService(insuranceBaseRepository);	        
	    Assertions.assertThrows(Exception.class, ()->{sampleIBS.get(1);});
		when(service.findInsuranceBaseById(30)).thenThrow(new RuntimeException());
		Assertions.assertThrows(RuntimeException.class, ()-> {service.findInsuranceBaseById(30);});
    }

}
