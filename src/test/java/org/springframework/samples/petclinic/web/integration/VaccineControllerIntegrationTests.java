package org.springframework.samples.petclinic.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.samples.petclinic.web.VaccineController;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VaccineControllerIntegrationTests {
	
	private static final int TEST_VACCINE_ID = 1;

	@Autowired
	private VaccineController vaccineController;
	
	@Autowired
	private PetService petService;
	
	@Test
    void testListVaccines() throws Exception {
		ModelMap model=new ModelMap();
		
		String view = vaccineController.listadoVaccine(model);
		
		assertEquals(view,"vaccine/vaccineList");
		assertNotNull(model.get("vaccine"));
	}
	
	@Test
	void testShowVaccine() throws Exception {
		ModelMap model=new ModelMap();

		String view = vaccineController.ShowVaccineDetail(TEST_VACCINE_ID, model);
		
		assertEquals(view,"vaccine/vaccineDetails");
		assertNotNull(model.get("vaccine"));
	}
	
	@Test
	void testShowExpiratedVaccine() throws Exception {
		ModelMap model=new ModelMap();

		String view = vaccineController.showExpiratedVaccine(model);
		
		assertEquals(view,"vaccine/vaccineExpirated");
		assertNotNull(model.get("vaccine"));
	}
	
	@Test
	void testShowLowStockVaccine() throws Exception {
		ModelMap model=new ModelMap();

		String view = vaccineController.showLowStockVaccine(model);
		
		assertEquals(view,"vaccine/vaccineStock");
		assertNotNull(model.get("vaccine"));
	}
	
	@Test
	void testInitCreationForm() throws Exception {
		ModelMap model=new ModelMap();
		
		String view = vaccineController.initCreationVaccineForm(model);
		
		assertEquals(view,"vaccine/vaccineCreate");
		assertNotNull(model.get("vaccine"));
	}
	
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		ModelMap model = new ModelMap();
		Vaccine newVaccine = new Vaccine();
		PetType petType = petService.findPetTypes().iterator().next();
		newVaccine.setInformation("Informacion");
		newVaccine.setPetType(petType);
		newVaccine.setExpiration(LocalDate.of(2022, 10, 10));
		newVaccine.setName("Nombre de la vacuna");
		newVaccine.setPrice(22.);
		newVaccine.setProvider("Este es el proveedor");
		newVaccine.setSideEffects("Efectos secundarios");
		newVaccine.setStock(10);
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		
		String view = vaccineController.processVaccineForm(newVaccine, bindingResult, model);
		
		assertEquals(view,"redirect:/vaccine/"+newVaccine.getId());
	}
	
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		ModelMap model = new ModelMap();
		Vaccine newVaccine = new Vaccine();
		newVaccine.setInformation("Informacion");
		newVaccine.setExpiration(LocalDate.of(2022, 10, 10));
		newVaccine.setName("Nombre de la vacuna");
		newVaccine.setPrice(22.);
		newVaccine.setProvider("Este es el proveedor");
		newVaccine.setSideEffects("Efectos secundarios");
		newVaccine.setStock(10);
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		bindingResult.reject("petType", "Required!");
		
		String view = vaccineController.processVaccineForm(newVaccine, bindingResult, model);
		
		assertEquals(view,"vaccine/vaccineCreate");
	}
	
	/*@Test
	void testDeleteVaccine() throws Exception {
		ModelMap model = new ModelMap();
		
		String view = vaccineController.delete(TEST_VACCINE_ID, model);
		
		assertEquals(view,"redirect:/vaccine");
	}*/
	
}
