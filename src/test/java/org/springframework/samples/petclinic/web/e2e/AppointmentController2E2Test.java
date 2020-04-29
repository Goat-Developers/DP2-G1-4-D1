package org.springframework.samples.petclinic.web.e2e;



import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.PetclinicApplication;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.web.PetController;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class) 
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK) 
@AutoConfigureMockMvc
public class AppointmentController2E2Test {
	
	private static final int TEST_PET_ID =1;
	
	@Autowired 
	private MockMvc mockMvc;
	
	@WithMockUser(username="owner1",authorities= {"owner"}) 
	@Test
	void initAppointmentCreationForm() throws Exception{
		
		mockMvc.perform(get("/appointment/new/{petId}",TEST_PET_ID))
		.andExpect(status().isOk())
		.andExpect(view().name("appointments/createAppointment"))
		.andExpect(model().attributeExists("appointment"));
		
	}
	@WithMockUser(username="owner1",authorities= {"owner"})
    @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("appointment/new/{petId}", TEST_PET_ID)
							.with(csrf())
							.param("appointmentDate", "2020/05/16")
							.param("appointmentTime", "12:00:00")
							.param("reason", "No quiero darla")
							.param("observations", "nada")
							.param("billing", "32")
							.param("attended","false"))
		
							.andExpect(status().isOk());
}
	
	@WithMockUser(username="owner1",authorities= {"owner"})
    @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("appointment/new/{petId}", TEST_PET_ID)
							.with(csrf())
							.param("reason", "No quiero darla"))

				
				.andExpect(model().attributeHasErrors("appointment"))
				.andExpect(status().isOk());
			
	}
}
