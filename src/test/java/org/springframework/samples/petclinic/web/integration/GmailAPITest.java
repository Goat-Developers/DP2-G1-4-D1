package org.springframework.samples.petclinic.web.integration;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.samples.petclinic.web.InsuranceController;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.java.Log;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log
public class GmailAPITest {
	
	private static final int TEST_INSURANCE_ID = 2;
	private static final int TEST_INSURANCE_BASE_ID = 12;
	private static final int TEST_VACCINE_ID = 15;
	private static final int TEST_TREATMENT_ID = 122;
	private static final int TEST_PET_ID = 8;
	
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
	void testSendingEmail() throws Exception{
		when().
		post("/insurance/new/{petId}",TEST_PET_ID)
		.then()
		.statusCode(200);
		
	}
	
}
