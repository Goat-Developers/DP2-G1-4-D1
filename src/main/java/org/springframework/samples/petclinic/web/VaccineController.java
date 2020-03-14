package org.springframework.samples.petclinic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vaccine")
public class VaccineController {
	
	@Autowired
	private VaccineService vaccineService;
	
	@GetMapping()
	public String listadoVaccine(ModelMap modelMap) {
		String vista="vaccine/listadoVaccine";
		
		List<Vaccine> vaccine=vaccineService.findAll();
		modelMap.addAttribute("vaccine",vaccine);
		
		return vista;
	}

}
