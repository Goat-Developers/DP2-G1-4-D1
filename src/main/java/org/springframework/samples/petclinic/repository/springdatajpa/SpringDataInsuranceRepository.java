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
package org.springframework.samples.petclinic.repository.springdatajpa;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Insurance;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.InsuranceRepository;

public interface SpringDataInsuranceRepository extends InsuranceRepository, Repository<Insurance, Integer> {

	@Override
	@Query("SELECT insurance FROM Insurance insurance WHERE insurance.id =?1")
	Insurance findById(@Param("id") int id);
	
	@Override
	@Query("SELECT insurance FROM Insurance insurance")
	Collection<Insurance> findAll();
	
	@Query("SELECT vaccine from Vaccine vaccine WHERE vaccine.expiration > current_date")
	Collection<Vaccine> findVaccines();
	
	@Query("SELECT vaccine from Vaccine vaccine WHERE vaccine.expiration > current_date AND vaccine.petType.id=?1")
	Collection<Vaccine> findVaccinesByPetTypeId(int id);
	
	@Query("SELECT treatment from Treatment treatment")
	Collection<Treatment> findTreatments();
	
	@Query("SELECT treatment from Treatment treatment WHERE treatment.petType.id=?1")
	Collection<Treatment> findTreatmentsByPetTypeId(int id);
}
