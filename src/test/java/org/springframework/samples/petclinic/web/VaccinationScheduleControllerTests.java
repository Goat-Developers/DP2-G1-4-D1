package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Insurance;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.VaccinationSchedule;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.VaccinationScheduleService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=VaccinationScheduleController.class,includeFilters = @ComponentScan.Filter(value = PetTypeFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class VaccinationScheduleControllerTests {
	
	private static final int TEST_VACCINATION_SCHEDULE_ID = 1;
	private static final int TEST_VACCINE_ID = 1;
	private static final int TEST_PET_ID = 1;
	private static final int TEST_INSURANCE_ID = 1;
	private static final int TEST_INSURANCE_BASE_ID = 1;
	
	@Autowired 
	private MockMvc mockMvc;
	
	@Autowired
	private VaccinationScheduleController vaccinationScheduleController;

	@MockBean
	private VaccinationScheduleService vaccinationScheduleService;
	
	@MockBean
	private VaccineService vaccineService;
	
	@MockBean
    private PetService petService;
	
	@MockBean
	private  InsuranceService insuranceService; 
	
	@MockBean
	private  InsuranceBaseService insuranceBaseService; 
	
	private VaccinationSchedule vaccSchedule;
	private Vaccine vaccine;
	private Pet pet;
	private InsuranceBase insBase;
	private Insurance insurance;
	
	@BeforeEach
	void setup() {
		
		vaccine = new Vaccine();
		PetType cat = new PetType();
		vaccine.setId(TEST_VACCINE_ID);
		vaccine.setInformation("Vacuna del coronavirus en pruebas, testeado en monos");
		vaccine.setExpiration(LocalDate.of(2021, 4, 3));
		vaccine.setName("Vacuna contra el coronavirus");
		vaccine.setPetType(cat);
		vaccine.setPrice(325.25);
		vaccine.setProvider("China");
		vaccine.setSideEffects("Puede provocar crisis nerviosas");
		vaccine.setStock(235);
		
		Set<Vaccine> vaccines = new HashSet<Vaccine>();
		vaccines.add(vaccine);
		List<Vaccine> vaccines1 = new ArrayList<Vaccine>();
		vaccines1.addAll(vaccines);
		
		insurance = new Insurance();
		insurance.setId(TEST_INSURANCE_ID);
		insurance.setInsuranceDate(LocalDate.of(2020, 4 ,3));
		insurance.setInsuranceBase(insBase);
		insurance.setVaccines(vaccines);
		
		
		pet = new Pet();
		PetType rata = new PetType();
		rata.setId(7);
		rata.setName("rata");
		pet.setBirthDate(LocalDate.of(2015, Month.APRIL, 14));
		pet.setId(TEST_PET_ID);
		pet.setName("Anton");
		pet.setType(rata);
		
		pet.setInsurance(insurance);
		
		List<LocalDate> dates = new ArrayList<>();
		dates.add(LocalDate.of(2020, Month.SEPTEMBER, 01));
		
		vaccSchedule = new VaccinationSchedule();
        vaccSchedule.setId(TEST_VACCINATION_SCHEDULE_ID);
        vaccSchedule.setVaccines(vaccines1);
        vaccSchedule.setDates(dates);
        
        pet.setSchedule(vaccSchedule);
        
		given(vaccineService.findById(TEST_VACCINE_ID)).willReturn(vaccine);
		given(vaccinationScheduleService.findById(TEST_VACCINE_ID)).willReturn(vaccSchedule);
		given(petService.findPetById(TEST_PET_ID)).willReturn(pet);
		given(insuranceService.findInsuranceById(TEST_INSURANCE_ID)).willReturn(insurance);
		given(insuranceService.findVaccinesByPetTypeId(TEST_PET_ID)).willReturn(vaccines);

	}

	@WithMockUser(value = "spring") 
	@Test
	void testInitVaccinationScheduleForm() throws Exception {
		mockMvc.perform(get("/vaccinationSchedule/{petId}",TEST_PET_ID))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("vaccinationSchedule"))
		.andExpect(model().attributeExists("vaccines"))
		.andExpect(view().name("vaccine/createVaccSchedule"));		
		
	}

	@WithMockUser(value = "spring") 
	@Test
	void testProcessVaccinationScheduleForm() throws Exception{
		Set<Vaccine> vaccines = new HashSet<Vaccine>();
		vaccines.add(vaccine);
			mockMvc.perform(post("/vaccinationSchedule/{petId}",TEST_PET_ID)
					.with(csrf())
					.param("vaccines", vaccines.toString())
					)
			.andExpect(status().isOk());

	}
	
	@WithMockUser(value = "spring") 
	@Test
	void testshowVaccScheduleDetails() throws Exception {
		mockMvc.perform(get("/vaccinationSchedule/{petId}/show",TEST_PET_ID)).andExpect(status().isOk())
		.andExpect(model().attributeExists("vaccinationSchedule"))
		.andExpect(model().attributeExists("pet"))
		.andExpect(view().name("vaccine/vaccScheduleDetails"));		
		
	}
	
}

