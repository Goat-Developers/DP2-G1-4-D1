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

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Appointment;
import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VetScheduleRepository;

/**
 * Spring Data JPA specialization of the {@link VetRepository} interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface SpringDataVetScheduleRepository extends VetScheduleRepository, Repository<VetSchedule, Integer> {
	
	@Override
	@Query("SELECT vet FROM VetSchedule vet WHERE vet.id =:id")
	public VetSchedule findById(@Param("id") int id);
	
	@Query("SELECT app FROM Appointment app WHERE app.appointmentDate =:day")
	List<Appointment> findAppointmentsByDay(@Param("day") LocalDate day);
  
  @Query("SELECT vt FROM VetSchedule vt")
	Collection<VetSchedule> findAll();
}

