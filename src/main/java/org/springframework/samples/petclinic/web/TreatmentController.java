package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class TreatmentController {
	
	@Autowired
	private TreatmentService treatmentService;
	
	
	@GetMapping("/treatment")
	public String listadoTreatment(ModelMap modelMap) {
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

}
