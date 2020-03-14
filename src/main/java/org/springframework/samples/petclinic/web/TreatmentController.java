package org.springframework.samples.petclinic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/treatment")
public class TreatmentController {
	
	@Autowired
	private TreatmentService treatmentService;
	
	
	@GetMapping()
	public String listadoTreatment(ModelMap modelMap) {
		String vista="treatment/listadoTreatment";
		
		List<Treatment> treatment= treatmentService.findAll();
		modelMap.addAttribute("treatment",treatment);
		
		return vista;
	}

}
