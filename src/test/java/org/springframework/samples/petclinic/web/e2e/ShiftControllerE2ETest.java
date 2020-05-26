package org.springframework.samples.petclinic.web.e2e;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.Shift;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class) 
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK) 
@AutoConfigureMockMvc
public class ShiftControllerE2ETest {
	
	private static final int TEST_SHIFT_ID = 20;
	private static final int TEST_VET_ID = 2;
	@Autowired 
	private MockMvc mockMvc;

	@WithMockUser(username="vet1",authorities= {"veterinarian"}) 
	@Test
	void initCreateshiftForm() throws Exception {
		Shift a = new Shift();
		a.setId(TEST_SHIFT_ID);
		a.setShiftDate(LocalTime.of(16, 00, 00));
		mockMvc.perform(get("/shifts/{shiftId}/new/{vetId}",TEST_SHIFT_ID,TEST_VET_ID))
		.andExpect(status().is3xxRedirection());
	}
	
	@WithMockUser(username="vet1",authorities= {"veterinarian"}) 
    @Test
    void testListShifts() throws Exception {
       mockMvc.perform(get("/shifts/{vetId}", TEST_VET_ID)).andExpect(status().isOk());
       			
    }
		
}
