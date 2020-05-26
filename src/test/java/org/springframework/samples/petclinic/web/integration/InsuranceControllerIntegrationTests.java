package org.springframework.samples.petclinic.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.Insurance;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.samples.petclinic.web.InsuranceController;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InsuranceControllerIntegrationTests {

	private static final int TEST_INSURANCE_ID = 1;
	private static final int TEST_VACCINE_ID = 1;
	private static final int TEST_TREATMENT_ID = 4;
	private static final int TEST_PET_ID = 1;

	@Autowired
	private InsuranceController insuranceController;
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private InsuranceBaseService insuranceBaseService;
	
	@Autowired
	private TreatmentService treatmentService;
	
	@Autowired
	private VaccineService vaccineService;
	
	@Autowired
	private PetService petService;
	
	@Test
    void testListInsurance() throws Exception {
		ModelMap model=new ModelMap();
		
		String view = insuranceController.showInsuranceList(model);
		
		assertEquals(view,"insurances/insuranceList");
		assertNotNull(model.get("insurance"));
	}
	
	@Test
    void testShowInsurance() throws Exception {
		ModelMap model=new ModelMap();
		
		String view = insuranceController.showInsuranceDetail(TEST_INSURANCE_ID, model);
		
		assertEquals(view,"insurances/insuranceDetails");
	}
	
	@Test
	void testInitCreationForm() throws Exception {
		ModelMap model=new ModelMap();
		
		String view = insuranceController.initInsuranceCreationForm(model,TEST_PET_ID);
		
		assertEquals(view,"insurances/createOrUpdateInsuranceForm");
		assertNotNull(model.get("insurance"));
	}
	
	
	
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		ModelMap model=new ModelMap();
		Insurance newInsurance = new Insurance();
		newInsurance.setInsuranceDate(LocalDate.now());
			Treatment treatment = new Treatment();
			treatment = treatmentService.findById(TEST_TREATMENT_ID);
			Set<Treatment> treatments = new HashSet<>();
			treatments.add(treatment);
		newInsurance.setTreatments(treatments);
			Vaccine vaccine = new Vaccine();
			vaccine = vaccineService.findById(TEST_VACCINE_ID);
			Set<Vaccine> vaccines = new HashSet<>();
			vaccines.add(vaccine);
		newInsurance.setVaccines(vaccines);
		
		
		Pet pet = petService.findPetById(TEST_PET_ID);
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		bindingResult.reject("insuranceBase", "Required!");
		
		String view = insuranceController.postInsuranceCreationForm(newInsurance, bindingResult, pet, model);
		
		assertEquals(view,"insurances/createOrUpdateInsuranceForm");
	}
}
