package org.springframework.samples.petclinic.web.e2e;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class) 
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK) 
@AutoConfigureMockMvc
public class AppointmentControllerE2ETest {
	
	
	private static final int TEST_PET_ID =3;
	private static final int TEST_APPOINTMENT_ID = 1;

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
