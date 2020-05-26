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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Shift;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.service.ShiftService;
import org.springframework.samples.petclinic.service.VetScheduleService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class ShiftController {

	private final VetService vetService;
	private final ShiftService shiftService;
	private final VetScheduleService vetScheduleService;

	@Autowired
	public ShiftController(VetService clinicService,ShiftService shiftService,VetScheduleService vetScheduleService) {
		this.vetService = clinicService;
		this.shiftService= shiftService;
		this.vetScheduleService= vetScheduleService;
	}

	@GetMapping(value = { "/shifts/{vetId}" })
	public String showVetList(@PathVariable("vetId") int vetId, Map<String, Object> model) {
		Vet vet = this.vetService.findVetById(vetId);
		List<Shift> shifts= this.shiftService.orderShifts(vet.getVetSchedule());
		int actualShifts = shifts.size();
		Collection<Shift> allShifts = this.shiftService.findAll();
		List<Shift> avShifts = new ArrayList<>();
		avShifts.addAll(allShifts);
		avShifts.removeAll(shifts);
		int max = vet.getMaxShifts();
		model.put("vet", vet);
		model.put("max", max);
		model.put("shifts", shifts);
		model.put("actual", actualShifts);
		model.put("availableShifts", avShifts);
		return "shifts/shiftList";
	}
	
	@GetMapping(value = "/shifts/{shiftId}/new/{vetId}")
	public String initCreateshiftForm(@PathVariable("vetId") int vetId, @PathVariable("shiftId") int shiftId) {
			
		Shift a = this.shiftService.findById(shiftId);
		Vet vet = this.vetService.findVetById(vetId);
		VetSchedule vtSchedule = vet.getVetSchedule();
		vtSchedule.getShifts().add(a);
		this.vetScheduleService.saveVtSchedule(vtSchedule);
		return "redirect:/shifts/{vetId}";
	}

	
	
}
