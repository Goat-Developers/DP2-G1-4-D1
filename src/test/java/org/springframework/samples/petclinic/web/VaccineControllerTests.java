package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class for {@link OwnerController}
 *
 * @author Colin But
 */

@WebMvcTest(controllers=VaccineController.class,
		includeFilters = @ComponentScan.Filter(value = PetTypeFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
class VaccineControllerTests {

	private static final int TEST_VACCINE_ID = 1;
	
	private static final int TEST_PET_TYPE_ID = 8;

	@Autowired
	private VaccineController vaccineController;
	
	@MockBean
	private VaccineService clinicService;
	
	@MockBean
	@Autowired(required=true)
	private InsuranceService insuranceService;
	
	@MockBean
	@Autowired(required=true)
	private InsuranceBaseService insuranceBaseService;
	
	@MockBean
	private PetService petService;
        
    @MockBean
	private UserService userService;
        
    @MockBean
    private AuthoritiesService authoritiesService; 

	@Autowired
	private MockMvc mockMvc;

	private Vaccine vaccineCoronavirus;

	@BeforeEach
	void setup() {
		
		vaccineCoronavirus = new Vaccine();
		PetType human = new PetType();
		human.setId(TEST_PET_TYPE_ID);
		human.setName("humano");
		vaccineCoronavirus.setId(TEST_VACCINE_ID);
		vaccineCoronavirus.setInformation("Vacuna del coronavirus en pruebas, testeado en monos");
		vaccineCoronavirus.setExpiration(LocalDate.of(2021, Month.APRIL, 3));
		vaccineCoronavirus.setName("Vacuna contra el coronavirus");
		vaccineCoronavirus.setPetType(human);
		vaccineCoronavirus.setPrice(325.25);
		vaccineCoronavirus.setProvider("China");
		vaccineCoronavirus.setSideEffects("Puede provocar crisis nerviosas");
		vaccineCoronavirus.setStock(235);
		given(this.clinicService.findById(TEST_VACCINE_ID)).willReturn(vaccineCoronavirus);
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/vaccine/new")).andExpect(status().isOk()).andExpect(model().attributeExists("vaccine"))
				.andExpect(view().name("vaccine/vaccineCreate"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/vaccine/new")
							.with(csrf())
							.param("name", "Vacuna de la rabia")
							.param("information", "Vacuna contra la rabia totalmente probada en todo tipo de animales")
							.param("price", "324.21")
							.param("expiration", "2020/03/16")
							.param("provider", "China town")
							.param("sideEffects", "Puede provocar ceguera")
							.param("stock", "325")
							.param("petType", "dog"))
				.andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/vaccine/new")
							.with(csrf())
							.param("name", "Vacuna de la rabia")
							.param("information", "Vacuna contra la rabia totalmente probada en todo tipo de animales")
							.param("provider", "China town"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("vaccine"))
				.andExpect(model().attributeHasFieldErrors("vaccine", "petType"))
				.andExpect(model().attributeHasFieldErrors("vaccine", "stock"))
				.andExpect(model().attributeHasFieldErrors("vaccine", "expiration"))
				.andExpect(model().attributeHasFieldErrors("vaccine", "price"))
				.andExpect(view().name("vaccine/vaccineCreate"));
	}

        @WithMockUser(value = "spring")
	@Test
	void testShowVaccine() throws Exception {
		mockMvc.perform(get("/vaccine/{vaccineId}", TEST_VACCINE_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("vaccine", hasProperty("name", is("Vacuna contra el coronavirus"))))
				.andExpect(model().attribute("vaccine", hasProperty("information", is("Vacuna del coronavirus en pruebas, testeado en monos"))))
				.andExpect(model().attribute("vaccine", hasProperty("expiration", is(LocalDate.of(2021, Month.APRIL, 3)))))
				.andExpect(model().attribute("vaccine", hasProperty("petType", is(vaccineCoronavirus.getPetType()))))
				.andExpect(model().attribute("vaccine", hasProperty("provider", is("China"))))
				.andExpect(model().attribute("vaccine", hasProperty("sideEffects", is("Puede provocar crisis nerviosas"))))
				.andExpect(model().attribute("vaccine", hasProperty("stock",is(235))))
				.andExpect(view().name("vaccine/vaccineDetails"));
	}

}
