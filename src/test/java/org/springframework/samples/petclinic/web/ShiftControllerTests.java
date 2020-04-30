package org.springframework.samples.petclinic.web;

import java.time.LocalTime;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Shift;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.ShiftService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.VetScheduleService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=ShiftController.class,includeFilters = @ComponentScan.Filter(value = PetTypeFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class ShiftControllerTests {

	private static final int TEST_SHIFT_ID = 3;
	
	private static final int TEST_VET_ID = 1;
	
	@Autowired
	private ShiftController shiftController;
	
	@MockBean
	private ShiftService shiftService;
	
	@MockBean
	private VetService vetService;
	
	@MockBean
	private VetScheduleService vetScheduleService;
	
	@MockBean
	private PetService petService;
	
	@MockBean
	private UserService userService;
	        
	@MockBean
	private AuthoritiesService authoritiesService; 
		
	@Autowired
	private MockMvc mockMvc;
		
	private Shift turno;
	private Vet vet;
	
	@BeforeEach
	void setup() {
		
		vet = new Vet();
		vet.setId(TEST_VET_ID);
		vet.setFirstName("Pablo");
		vet.setLastName("Castillo");
		vet.setMaxShifts(10);
		
		turno = new Shift();
		turno.setId(TEST_SHIFT_ID);
		LocalTime time = LocalTime.of(16, 00, 00);
		turno.setShiftDate(time);
		
		given(shiftService.findById(TEST_SHIFT_ID)).willReturn(turno);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testShowVetList() throws Exception {
		mockMvc.perform(get("/shifts/{vetId}", TEST_VET_ID)).andExpect(status().isOk())
//				.andExpect(model().attributeExists("vet"))
//				.andExpect(model().attributeExists("max"))
//				.andExpect(model().attributeExists("shifts"))
//				.andExpect(model().attributeExists("actual"))
//				.andExpect(model().attributeExists("avalableShifts"))
				.andExpect(view().name("shifts/shiftList"));
		
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testInitCreationForm() throws Exception {

	mockMvc.perform(get("/shifts/{shiftId}/new/{vetId}", TEST_SHIFT_ID, TEST_VET_ID)).andExpect(status().isOk())		
			.andExpect(view().name("redirect:/shifts/"+TEST_VET_ID));
	
	}
	
}
