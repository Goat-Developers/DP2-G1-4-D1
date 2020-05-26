
package org.springframework.samples.petclinic.web;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Appointment;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Shift;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.service.AppointmentService;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.samples.petclinic.service.VetScheduleService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AppointmentController {
	
	private final VetScheduleService vtSchService;
	
	private final AppointmentService appService;
	
	private final PetService petService;
	
	private final VaccineService vaccineService;
	
	private final InsuranceService insuranceService;
	
	private final String appo= "appointment";
	
	@Autowired
	public AppointmentController(AppointmentService appointmentService, PetService petService,InsuranceService insuranceService,VetScheduleService vtSchService,VaccineService vaccineService) {
		this.vtSchService = vtSchService;
		this.vaccineService = vaccineService;
		this.insuranceService = insuranceService;
		this.appService = appointmentService;
		this.petService = petService;
	}

	@InitBinder("appointment")
	public void initPetBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new AppointmentValidator(vtSchService));
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "appointment/new/{petId}")
	public String initAppointmentCreationForm(Map<String,Object>model, @PathVariable("petId") int id) {
		Appointment appointment = new Appointment();
		Pet pet = this.petService.findPetById(id);	

		List<LocalTime> times= new ArrayList<>();
		for(int i=8; i<16; i++) {
			times.add(LocalTime.of(i, 0,0));
		}
		model.put("pet",pet);
		model.put(appo, appointment);
		model.put("times",times);
		

			int petTypeId = pet.getType().getId();
			Collection<Vaccine> vaccines = this.insuranceService.findVaccinesByPetTypeId(petTypeId);
			Collection<Treatment> treatments= this.insuranceService.findTreatmentsByPetTypeId(petTypeId);
		
			model.put("vaccines", vaccines);
			model.put("treatments", treatments);
		
		return "appointments/createAppointment";
		
	}
	@PostMapping(value = "appointment/new/{petId}")
	public String postAppointmentCreationForm(@Valid Appointment app, BindingResult bindingResult,Map<String,Object>model, @PathVariable("petId") int id) throws DataAccessException, DuplicatedPetNameException {
		
		Pet pet = this.petService.findPetById(id);	
		if(bindingResult.hasErrors()) {
			
			List<LocalTime> times= new ArrayList<>();
			for(int i=8; i<16; i++) {
				times.add(LocalTime.of(i, 0,0));
			}
			model.put("pet",pet);
			model.put(appo, app);
			model.put("times",times);
			
				int petTypeId = pet.getType().getId();
				Collection<Vaccine> vaccines = this.insuranceService.findVaccinesByPetTypeId(petTypeId);
				Collection<Treatment> treatments= this.insuranceService.findTreatmentsByPetTypeId(petTypeId);
			
				model.put("vaccines", vaccines);
				model.put("treatments", treatments);
			
	
			
			return "appointments/createAppointment";
		}else {
			Double res = 0.;
		
			if(pet.getInsurance() == null) {
				if((app.getVaccine()!=null)) {
					res += app.getVaccine().getPrice();
					
				}
				if((app.getTreatment()!=null)) {
					res += app.getTreatment().getPrice();
				}
			}else {
				if(!(pet.getInsurance().getVaccines().contains(app.getVaccine())) && (app.getVaccine() !=null)) {
					res += app.getVaccine().getPrice();
				}
				if(!(pet.getInsurance().getTreatments().contains(app.getTreatment())) && (app.getTreatment() !=null) ) {
					res += app.getTreatment().getPrice();
				}
			}
			
			if((app.getVaccine()!=null)) {
				Vaccine vacuna = app.getVaccine();
				vacuna.setStock(vacuna.getStock()-1);
				this.vaccineService.saveVaccine(vacuna);
			}
			
			app.setBilling(res);
			this.appService.saveAppointment(app);
			pet.getAppointments().add(app);
			if(pet.getInsurance()!=null && (app.getVaccine() != null)) {
				pet.getInsurance().getVaccines().remove(app.getVaccine());
				
			}
			if(pet.getInsurance()!=null && (app.getTreatment() != null)) {
				pet.getInsurance().getTreatments().remove(app.getTreatment());
			}
			this.petService.savePet(pet);
			VetSchedule vtSchedule = asignaAppointment(app);
			vtSchedule.getAppointments().add(app);
			this.vtSchService.saveVtSchedule(vtSchedule);
			
			
		}
		return "redirect:/owners/"+pet.getOwner().getId();
	}
	private VetSchedule asignaAppointment(@Valid Appointment app) {
		VetSchedule res = new VetSchedule();
		List<VetSchedule> allVetSchedule = (List<VetSchedule>) this.vtSchService.findAll();
		for(int i=0; i<allVetSchedule.size(); i++) {
			List<LocalTime> shifts = allVetSchedule.get(i).getShifts().stream().map(Shift::getShiftDate).collect(Collectors.toList());
			if(shifts.contains(app.getAppointmentTime())) {
				List<Appointment> appVetSchedule = allVetSchedule.get(i).getAppointments().stream().filter(x->x.getAppointmentDate().equals(app.getAppointmentDate())&& x.getAppointmentTime().equals(app.getAppointmentTime())).collect(Collectors.toList());
				if(appVetSchedule.isEmpty()) {
					res= allVetSchedule.get(i);
					break;
				}
			}
			
		}
		return res;
	}

	@GetMapping(value="/appointment/{appointementId}")
	public String showAppointmentDetails(@PathVariable("appointementId") int appointementId, Map<String,Object>  model ) {
		Appointment appointment = this.appService.findAppointmentById(appointementId);
		model.put(appo, appointment);
		return "appointments/appointmentDetails";
		
	}
	

	@PostMapping(value = "/appointment/observe")
	public String VetObserveApplication(@ModelAttribute("appointment") Appointment app, @ModelAttribute("id") int id) {

		Appointment appt = this.appService.findAppointmentById(id);
		appt.setObservations(app.getObservations());
		appt.setAttended(true);
		this.appService.saveAppointment(appt);
		return "redirect:/vets";
	}



	

}
