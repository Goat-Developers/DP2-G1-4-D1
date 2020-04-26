package org.springframework.samples.petclinic.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.samples.petclinic.web.TreatmentController;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TreatmentControllerIntegrationTests {
	
	private static final int TEST_TREATMENT_ID = 1;
	
	@Autowired
	private TreatmentController treatmentController;
	
	@Autowired
	private TreatmentService treatmentService;
	
	@Autowired
	private PetService petService;
	
	@Test
    void testListTreatments() throws Exception {
		ModelMap model=new ModelMap();
		
		String view = treatmentController.listTreatment(model);
		
		assertEquals(view,"treatment/treatmentList");
		assertNotNull(model.get("treatment"));
	}
	
	@Test
	void testShowTreatment() throws Exception {
		ModelMap model=new ModelMap();
		Treatment treatment = treatmentService.findById(TEST_TREATMENT_ID);

		String view = treatmentController.ShowtreatmentDetail(TEST_TREATMENT_ID, model);
		
		assertEquals(view,"treatment/treatmentDetails");
		//assertEquals(model.getAttribute("treatment"), treatment); da dos treatments distintos????
	}
	
	@Test
	void testInitCreationForm() throws Exception {
		ModelMap model=new ModelMap();
		
		String view= treatmentController.initCreationTreatmentForm(model);
		
		assertEquals(view,"treatment/treatmentCreate");
		assertNotNull(model.get("treatment"));
	}
	
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		ModelMap model = new ModelMap();
		Treatment newTreatment = new Treatment();
		PetType petType = petService.findPetTypes().iterator().next();
		newTreatment.setDescription("Esta es la descripcion");
		newTreatment.setPetType(petType);
		newTreatment.setPrice(42.5);
		newTreatment.setType("Este es el tipo de tratamiento");
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		
		String view = treatmentController.processTreatmentForm(newTreatment, bindingResult, model);
		
		assertEquals(view,"redirect:/treatment/"+newTreatment.getId());
	}
	
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		ModelMap model = new ModelMap();
		Treatment newTreatment = new Treatment();
		newTreatment.setDescription("Esta es la descripcion");
		newTreatment.setPrice(42.5);
		newTreatment.setType("Este es el tipo de tratamiento");
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		bindingResult.reject("petType", "Required!");
		
		String view = treatmentController.processTreatmentForm(newTreatment, bindingResult, model);
		
		assertEquals(view, "treatment/treatmentCreate");
	}

}
