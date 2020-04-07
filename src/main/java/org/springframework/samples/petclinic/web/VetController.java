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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Appointment;
import org.springframework.samples.petclinic.model.Shift;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private final VetService vetService;

	@Autowired
	public VetController(VetService clinicService) {
		this.vetService = clinicService;
	}

	@GetMapping(value = { "/vets" })
	public String showVetList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}

	@GetMapping(value = { "/vets.xml"})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		return vets;
	}

	
	@GetMapping("/vets/{vetId}")
	public ModelAndView showVet(@PathVariable("vetId") int vetId) {
		ModelAndView mav = new ModelAndView("vets/vetDetails");
		mav.addObject(this.vetService.findVetById(vetId));
		return mav;
	}
	
	@GetMapping(value = "/vets/{vetId}/edit")
	public String initUpdateVetForm(@PathVariable("vetId") int vetId, Model model) {
		Vet vet = this.vetService.findVetById(vetId);
		model.addAttribute(vet);
		return "vets/updateVetForm";
	}

	@PostMapping(value = "/vets/{vetId}/edit")
	public String processUpdateVetForm(@Valid Vet vet, BindingResult result,
			@PathVariable("vetId") int vetId) {
		if (result.hasErrors()) {
			return "vets/updateVetForm";
		}
		else {
			vet.setId(vetId);
			this.vetService.saveVet(vet);
			return "redirect:/vets/{vetId}";
		}
	}
	
	@GetMapping("/vetSchedule/{day}")
	public String ShowScheduleDetail(@PathVariable("day")  String day, Map<String,Object>  model ) {
		List<Appointment> appointments = vetService.findAppointmentsByDay(LocalDate.parse(day));
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		Vet vet = this.vetService.findVetByPrincipal(currentUsername);
		VetSchedule vetSchedule = vet.getVetSchedule();
		
		Set<Appointment> apps= vetSchedule.getAppointments();
		
		appointments = appointments.stream().filter(a->apps.contains(a)).collect(Collectors.toList());
		
		
		//Ordeno los turnos
		List<Shift> shifts= orderShifts(vetSchedule);
		
		model.put("shifts",shifts);
		model.put("appointments",appointments);
		return "vets/scheduleDetails";
	}
	
	@GetMapping(value = { "/vetSchedule" })
	public String showVetSchedule(Map<String, Object> model) {
		
		Month month = LocalDate.now().getMonth();
		List<LocalDate> listaDias = new ArrayList<>();
		List<LocalDate> week = new ArrayList<LocalDate>();
		
		
		
		//Añado los dias de la semana
		List<DayOfWeek> dias = new ArrayList<>();
		dias.add(DayOfWeek.MONDAY);
		dias.add(DayOfWeek.TUESDAY);
		dias.add(DayOfWeek.WEDNESDAY);
		dias.add(DayOfWeek.THURSDAY);
		dias.add(DayOfWeek.FRIDAY);
		dias.add(DayOfWeek.SATURDAY);
		dias.add(DayOfWeek.SUNDAY);
		
		//Busco todos los días del mes
		listaDias = findDates(dias, month);
		
		DayOfWeek firstDay =listaDias.get(0).getDayOfWeek();
		
		
		int z =0;
		if (firstDay.equals(dias.get(0))) {
			z=7;
			week = addFirstDays(listaDias,z);
		}
		if (firstDay.equals(dias.get(1))) {
			z=6;
			week = addFirstDays(listaDias,z);
		}
		if (firstDay.equals(dias.get(2))) {
			z=5;
			week = addFirstDays(listaDias,z);
		}
		if (firstDay.equals(dias.get(3))) {
			z=4;
			week = addFirstDays(listaDias,z);
		}
		if (firstDay.equals(dias.get(4))) {
			z=3;
			week = addFirstDays(listaDias,z);
		}
		if (firstDay.equals(dias.get(5))) {
			z=2;
			week = addFirstDays(listaDias,z);
		}
		if (firstDay.equals(dias.get(6))) {
			z=1;
			week = addFirstDays(listaDias,z);
		}
		
		listaDias.removeAll(week);
		
		int numSemanas = (month.maxLength()-listaDias.get(0).getDayOfMonth())/7;
		numSemanas++;
		
		switch (numSemanas) {
		
		case 3:
			List<LocalDate> semana13 = insertDays(listaDias, month);
			listaDias.removeAll(semana13);
			List<LocalDate> semana23 = insertDays(listaDias, month);
			listaDias.removeAll(semana23);
			List<LocalDate> semana33 = insertDays(listaDias, month);
			listaDias.removeAll(semana33);
			
			model.put("semana1", semana13);
			model.put("semana2", semana23);
			model.put("semana3", semana33);
			
			break;
			
		
		case 4:
			List<LocalDate> semana14 = insertDays(listaDias, month);
			listaDias.removeAll(semana14);
			List<LocalDate> semana24 = insertDays(listaDias, month);
			listaDias.removeAll(semana24);
			List<LocalDate> semana34 = insertDays(listaDias, month);
			listaDias.removeAll(semana34);
			List<LocalDate> semana44 = insertDays(listaDias,month);
			
			
			model.put("semana1", semana14);
			model.put("semana2", semana24);
			model.put("semana3", semana34);
			model.put("semana4", semana44);
			
			break;
			
		case 5:	
			
			List<LocalDate> semana15 = insertDays(listaDias, month);
			listaDias.removeAll(semana15);
			List<LocalDate> semana25 = insertDays(listaDias, month);
			listaDias.removeAll(semana25);
			List<LocalDate> semana35 = insertDays(listaDias, month);
			listaDias.removeAll(semana35);
			List<LocalDate> semana45 = insertDays(listaDias,month);
			listaDias.removeAll(semana45);
			List<LocalDate> semana55 = insertDays(listaDias,month);
			
			
			model.put("semana1", semana15);
			model.put("semana2", semana25);
			model.put("semana3", semana35);
			model.put("semana4", semana45);
			model.put("semana5", semana55);
			break;
		default:
			break;
			
		}
		
		model.put("firstWeek", week);
		model.put("z", z);
		model.put("dias", dias);
		

		
		return "vets/vetSchedule";
	}

	private List<LocalDate> addFirstDays(List<LocalDate> listaDias, int z) {
		List<LocalDate> week = new ArrayList<>();
		for (int i =0; i<z;i++) {
			week.add(listaDias.get(i));
		}
		return week;
	}

	

	private List<LocalDate> insertDays(List<LocalDate> listaDias, Month month) {
		List<LocalDate> res = new ArrayList<>();
		int i =0;
		while(i<7) {
			res.add(listaDias.get(i));
			if (listaDias.get(i).getDayOfMonth()==month.maxLength()) {
				break;
			}else {
				i++;
			}
		}
		return res;
		
	}

	private List<Shift> orderShifts(VetSchedule vetSchedule) {
		List<Shift> shifts = vetSchedule.getShifts().stream().collect(Collectors.toList());
		Collections.sort(shifts, (o1,o2)-> o1.getShiftDate().compareTo(o2.getShiftDate()));
		return shifts;
	}
	
	private List<LocalDate> findDates(List<DayOfWeek> day, Month month){
		List<LocalDate> fechas = new ArrayList<>();
		for (int i=1; i<=month.maxLength(); i++) {
			LocalDate fecha =LocalDate.of(LocalDate.now().getYear(), month, i);
			fechas.add(fecha);
		}
		return fechas;
		
	}
	
}
