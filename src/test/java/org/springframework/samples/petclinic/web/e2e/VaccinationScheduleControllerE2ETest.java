package org.springframework.samples.petclinic.web.e2e;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class) 
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK) 
@AutoConfigureMockMvc
public class VaccinationScheduleControllerE2ETest {
	
	private static final int TEST_PET_ID = 1;
	@Autowired 
	private MockMvc mockMvc;

	@WithMockUser(username="vet1",authorities= {"veterinarian"}) 
	@Test
	void initVaccinationScheduleForm() throws Exception {
		mockMvc.perform(get("/vaccinationSchedule/{petId}",TEST_PET_ID))
		.andExpect(status().isOk())
		.andExpect(view().name("vaccine/createVaccSchedule"))
		.andExpect(model().attributeExists("vaccinationSchedule"));
		
	}

	@WithMockUser(username="vet1",authorities= {"veterinarian"}) 
	@Test
	void processVaccinationScheduleForm() throws Exception{
		List<Vaccine> vaccines = new ArrayList<>();
		Vaccine vaccineCoronavirus = new Vaccine();
		PetType cat = new PetType();
		vaccineCoronavirus.setId(26);
        vaccineCoronavirus.setInformation("Vacuna del coronavirus en pruebas, testeado en monos");
        vaccineCoronavirus.setExpiration(LocalDate.of(2021, 4, 3));
        vaccineCoronavirus.setName("Vacuna contra el coronavirus");
        vaccineCoronavirus.setPetType(cat);
        vaccineCoronavirus.setPrice(325.25);
        vaccineCoronavirus.setProvider("China");
        vaccineCoronavirus.setSideEffects("Puede provocar crisis nerviosas");
        vaccineCoronavirus.setStock(235);
        vaccines.add(vaccineCoronavirus);
			mockMvc.perform(post("/vaccinationSchedule/{petId}",TEST_PET_ID)
					.with(csrf())
					.param("vaccines", vaccines.toString())
					)
			.andExpect(status().isOk());

	}
	
}
