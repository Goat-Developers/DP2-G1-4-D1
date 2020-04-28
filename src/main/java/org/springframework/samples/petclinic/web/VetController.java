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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Shift;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ShiftService;
import org.springframework.samples.petclinic.service.VetScheduleService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	private final VetScheduleService vetScheduleService;
    private final ShiftService shiftService;

    @Autowired
    public VetController(VetService clinicService, ShiftService shiftService, VetScheduleService vetScheduleService) {
        this.vetService = clinicService;
        this.shiftService = shiftService;
        this.vetScheduleService= vetScheduleService;
    }

    @InitBinder("vet")
	public void initPetBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new VetValidator());
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
			Vet vete = this.vetService.findVetById(vetId);
            int turns = vete.getMaxShifts();
            int turns2 = vet.getMaxShifts();
            VetSchedule vetS = vete.getVetSchedule();
            List<Shift> shifts = vetS.getShifts().stream().collect(Collectors.toList());
            List<Shift> all = (List<Shift>) shiftService.findAll();
            if(turns2 < turns) {
                int remove = turns-turns2;
                for (int i=0; i<remove; i++) {
                    shifts.remove(i);
                }

            }else {
                if (turns2>turns) {
                    for(int i=0; i< turns2-turns; i++) {
                        for (int z=0;z<all.size();z++) {
                            Shift test = all.get(z);
                            if(!shifts.contains(test)) {
                                shifts.add(test);
                                break;
                            }
                        }
                    }
                }
            }
            vetS.setShifts(shifts.stream().collect(Collectors.toSet()));
            this.vetScheduleService.saveVtSchedule(vetS);
            vet.setVetSchedule(vetS);
			vet.setId(vetId);
			this.vetService.saveVet(vet);
			return "redirect:/vets/{vetId}";
		}
	}
	
	
	
}
