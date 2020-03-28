package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers=TreatmentController.class,
		includeFilters = @ComponentScan.Filter(value = PetTypeFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
public class TreatmentControllerTests {
	
	private static final int TEST_TREATMENT_ID = 1;
	
	@Autowired
	private TreatmentController treatmentController;
        
    @MockBean
	private UserService userService;
	
	@MockBean
	private TreatmentService treatmentService;
	
	@MockBean
	private PetService petService;
	
	@MockBean
    private AuthoritiesService authoritiesService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Treatment treatment;
	
	@BeforeEach
	void setup() {
		treatment = new Treatment();
		treatment.setId(TEST_TREATMENT_ID);
		treatment.setDescription("Esta es la descripcion");
		treatment.setPrice(40.);
		treatment.setType("Este es el tipo");
			PetType dog = new PetType();
			dog.setId(6);
			dog.setName("dog");
		treatment.setPetType(dog);
		given(this.treatmentService.findPetTypes()).willReturn(Lists.newArrayList(dog));
		given(this.treatmentService.findById(TEST_TREATMENT_ID)).willReturn(treatment);
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testListTreatments() throws Exception {
       mockMvc.perform(get("/treatment")).andExpect(status().isOk()).andExpect(model().attributeExists("treatment"))
       			.andExpect(view().name("treatment/treatmentList"));
    }
	
	@WithMockUser(value = "spring")
	@Test
	void testShowTreatment() throws Exception {
		mockMvc.perform(get("/treatment/{treatmentId}", TEST_TREATMENT_ID))
				.andExpect(status().isOk())
				.andExpect(model().attribute("treatment", hasProperty("type", is("Este es el tipo"))))
				.andExpect(model().attribute("treatment", hasProperty("petType", is(treatment.getPetType()))))
				.andExpect(model().attribute("treatment", hasProperty("price", is(40.0))))
				.andExpect(model().attribute("treatment", hasProperty("description", is("Esta es la descripcion"))))
				.andExpect(view().name("treatment/treatmentDetails"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testInitCreationTreatmentForm() throws Exception {
		mockMvc.perform(get("/treatment/new")).andExpect(status().isOk())
				.andExpect(model().attributeExists("treatment"))
				.andExpect(model().attributeExists("types"))
				.andExpect(view().name("treatment/treatmentCreate"));
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationTreatmentFormSuccess() throws Exception {
		mockMvc.perform(post("/treatment/new")
						.with(csrf())
						.param("type", "Dientes")
						.param("price", "20.5")
						.param("description", "Descripcion")
						.param("petType", "dog"))
				.andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
    @Test
    void testProcessCreationTreatmentFormHasError() throws Exception {
		mockMvc.perform(post("/treatment/new")
						.with(csrf())
						.param("type", "Dientes")
						.param("price", "fail")
						.param("description", "Descripcion")
						.param("petType", "dog"))
				.andExpect(model().attributeHasErrors("treatment"))
				.andExpect(model().attributeHasFieldErrors("treatment", "price"))
				.andExpect(view().name("treatment/treatmentCreate"));
	}

}
