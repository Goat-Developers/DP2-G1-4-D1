package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.VaccinationSchedule;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.VaccinationScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VaccinationScheduleController {
	
	private final InsuranceService insService; 
	private final VaccinationScheduleService vcsService;
	private final PetService petService;
	
	@Autowired
	public VaccinationScheduleController(VaccinationScheduleService vcsService,InsuranceService insService,PetService petService) {
		this.vcsService = vcsService;
		this.insService = insService;
		this.petService = petService;
	}
	
	@GetMapping(value="/vaccinationSchedule/{petId}")
	public String initVaccinationScheduleForm(@PathVariable("petId") int id,Map<String,Object> model) {
		VaccinationSchedule vaccSchedule = new VaccinationSchedule();
		Pet pet= this.petService.findPetById(id);
		Collection<Vaccine> vaccines = this.insService.findVaccinesByPetTypeId(pet.getType().getId()); 
		model.put("vaccinationSchedule", vaccSchedule);
		model.put("vaccines", vaccines);
		return "vaccine/createVaccSchedule";
	}
	@PostMapping(value="/vaccinationSchedule/{petId}")
	public String processVaccinationScheduleForm(@Valid VaccinationSchedule vaccSchedule, BindingResult bindingResult, @PathVariable("petId") int id,Map<String,Object> model) {
		Pet pet= this.petService.findPetById(id);
		if(bindingResult.hasErrors()) {
			Collection<Vaccine> vaccines = this.insService.findVaccinesByPetTypeId(pet.getType().getId());
			model.put("vaccinationSchedule", vaccSchedule);
			model.put("vaccines", vaccines);
			return "vaccine/createVaccSchedule";
		}
		List<LocalDate> dates = new ArrayList<LocalDate>();
		LocalDate primer =  LocalDate.now();
		Collection<Vaccine> choosedVaccines= vaccSchedule.getVaccines();
		for(int i=0; i<choosedVaccines.size();i++) {
			dates.add(primer.plus(1+i,ChronoUnit.WEEKS));
		}
		vaccSchedule.setDates(dates);
		this.vcsService.SaveVaccSchedule(vaccSchedule);
		return "redirect:/owners/"+pet.getOwner().getId();
	}

}
