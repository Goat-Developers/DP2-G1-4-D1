package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TreatmentController {
	
	@Autowired
	private TreatmentService treatmentService;
	
	
	@GetMapping("/treatment")
	public String listTreatment(ModelMap modelMap) {
		String vista="treatment/treatmentList";
		
		List<Treatment> treatment= treatmentService.findAll();
		modelMap.addAttribute("treatment",treatment);
		
		return vista;
	}
	
	
	@GetMapping("/treatment/{treatmentId}")
	public String ShowtreatmentDetail(@PathVariable("treatmentId")  int treatmentId, Map<String,Object>  model ) {
		Treatment v = treatmentService.findById(treatmentId);
		model.put("treatment",v);
		return "treatment/treatmentDetails";
	}
	
	@GetMapping(value = "/treatment/new")
	public String initCreationTreatmentForm(Map<String, Object> model) {
		Treatment treatment = new Treatment();
		List<PetType> types = treatmentService.findPetTypes();
		model.put("treatment", treatment);
		model.put("types",types);
		
		return "treatment/treatmentCreate";
	}

	@PostMapping(value = "/treatment/new")
	public String processTreatmentForm(@Valid Treatment treatment, BindingResult result, Map<String,Object>  model) {
		if (result.hasErrors()) {
			List<PetType> types = treatmentService.findPetTypes();
			model.put("types",types);
			return "treatment/treatmentCreate";
		}
		else {
			this.treatmentService.saveTreatment(treatment);
			
			return "redirect:/treatment/" + treatment.getId();
		}
	}

}
