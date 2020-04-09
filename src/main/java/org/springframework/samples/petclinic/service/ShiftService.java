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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Shift;
import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.repository.ShiftRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.stereotype.Service;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ShiftService {

	private VetRepository vetRepository;
	private ShiftRepository shiftRepository;


	@Autowired
	public ShiftService(VetRepository vetRepository, ShiftRepository shiftRepository) {
		this.vetRepository = vetRepository;
		this.shiftRepository= shiftRepository;
	}		

	public void saveShift(Shift shift) {
		shiftRepository.save(shift);
	}	
	
	public Collection<Shift> findAll(){
		return shiftRepository.findAll();
	}
	
	public Shift findById(int id) {
		return shiftRepository.findById(id);
	}
	public List<Shift> orderShifts(VetSchedule vetSchedule) {
		List<Shift> shifts = vetSchedule.getShifts().stream().collect(Collectors.toList());
		Collections.sort(shifts, (o1,o2)-> o1.getShiftDate().compareTo(o2.getShiftDate()));
		return shifts;
	}

}
