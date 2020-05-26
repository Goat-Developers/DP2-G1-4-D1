package org.springframework.samples.petclinic.web.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.Month;


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
public class VetScheduleE2ETest {
	

	@Autowired
	private MockMvc mockMvc;

	

	
	@WithMockUser(username="vet1",authorities= {"veterinarian"}) 
	@Test
	void testShowScheduleDetail() throws Exception {
		mockMvc.perform(get("/vetSchedule/{day}", LocalDate.of(2020, Month.MAY, 3)))
		.andExpect(status().isOk())		
		.andExpect(view().name("vets/scheduleDetails"));
    }
	
	@WithMockUser(username="vet1",authorities= {"veterinarian"})
	@Test
	void testShowSchedule() throws Exception {	    	
	 	mockMvc.perform(get("/vetSchedule")).andExpect(status().isOk())
			
				.andExpect(view().name("vets/vetSchedule"));
	}
	
	@WithMockUser(username="vet1",authorities= {"veterinarian"})
	@Test
	void testShowVetScheduleDetail() throws Exception {	    	
	 	mockMvc.perform(get("/vetSchedule/vet/{vetId}", 1)).andExpect(status().isOk())
				
				.andExpect(view().name("vets/vetSchedule"));
	}
	

}
