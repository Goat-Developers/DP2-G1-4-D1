package org.springframework.samples.petclinic.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.samples.petclinic.web.InsuranceBaseController;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InsuranceBaseControllerIntegrationTests {
	
	private static final int TEST_INSURANCE_BASE_ID = 1;
	
	@Autowired
	private InsuranceBaseController insuranceBaseController;
	
	@Autowired
	private InsuranceBaseService insuranceBaseService;
	
	@Test
	void testListInsuranceBase() throws Exception {
		ModelMap model=new ModelMap();
		
		String view = insuranceBaseController.showInsuranceBaseList(model);
		
		assertEquals(view,"insurancesbases/insuranceList");
		assertNotNull(model.get("insurance_base"));
	}
	
	@Test
	void testShowInsuranceBaseDetails() throws Exception {
		ModelMap model=new ModelMap();
		
		String view = insuranceBaseController.ShowAInsuranceBaseDetail(TEST_INSURANCE_BASE_ID, model);
		
		assertEquals(view,"insurancesbases/insuranceDetails");
		assertNotNull(model.get("insurance_base"));
	}

}
