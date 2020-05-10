package org.springframework.samples.petclinic.web.e2e;



import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.model.Appointment;
import org.springframework.samples.petclinic.model.Insurance;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Shift;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.VaccinationSchedule;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.service.AppointmentService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.ShiftService;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.samples.petclinic.service.VetScheduleService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.samples.petclinic.web.AppointmentController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class) 
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK) 
@AutoConfigureMockMvc
public class AppointmentControllerE2ETest {
	
	
	private static final int TEST_PET_ID =3;
	private static final int TEST_APPOINTMENT_ID = 1;
	private static final int TEST_VACCINE_ID = 4;
	private static final int TEST_TREATMENT_ID = 1;

	@Autowired 
	private MockMvc mockMvc;

	
	@WithMockUser(username="owner1",authorities= {"owner"}) 
	@Test
	void initAppointmentCreationForm() throws Exception{
		
		mockMvc.perform(get("/appointment/new/{petId}",TEST_PET_ID))
		.andExpect(status().isOk());
		
	}

	@WithMockUser(username="owner1",authorities= {"owner"})
		@Test
		void testShowAppointmentDetails() throws Exception {
		
			mockMvc.perform(get("/appointment/{appointementId}", TEST_APPOINTMENT_ID)).andExpect(status().isOk());
		}
	
	@WithMockUser(username="vet1",authorities= {"v3t"})
	@Test
	void testVetObserverApplication() throws Exception{
		mockMvc.perform(post("/appointment/observe")
		.with(csrf())  
		.param("attended", "true")).
		andExpect(status().isOk());
		
	}
}
