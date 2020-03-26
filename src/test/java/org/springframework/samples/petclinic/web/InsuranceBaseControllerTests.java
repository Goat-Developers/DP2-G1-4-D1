package org.springframework.samples.petclinic.web;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers=InsuranceBaseController.class,includeFilters = @ComponentScan.Filter(value = PetTypeFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
public class InsuranceBaseControllerTests {
	
	private static final int TEST_INSURANCE_BASE_ID = 1;
	private static final int TEST_VACCINE_ID = 1;
	private static final int TEST_TREATMENT_ID = 1;
	
	
	
	@Autowired
	private InsuranceBaseController insuranceBaseController;

	@MockBean
	private InsuranceBaseService insuranceBaseService;
        
	@MockBean
    private PetService petService;
	
        @MockBean
	private UserService userService;
        
        @MockBean
        private AuthoritiesService authoritiesService; 
	
	@Autowired
	private MockMvc mockMvc;
	
	private InsuranceBase dameDinero;
	private Vaccine vaccineCoronavirus;
	private Treatment tratamientoParaAburrimiento;
	
	@BeforeEach
	void setup() {
		
	Set<Vaccine> vacunas = new HashSet<Vaccine>();
	vacunas.add(vaccineCoronavirus);
	Set<Treatment> tratamientos = new HashSet<Treatment>();
	tratamientos.add(tratamientoParaAburrimiento);	
		
	dameDinero = new InsuranceBase();
	PetType mascota = new PetType();
	dameDinero.setId(TEST_INSURANCE_BASE_ID);
	dameDinero.setName("Seguro para ganar dinero");
	dameDinero.setPetType(mascota);
	dameDinero.setVaccines(vacunas);
	dameDinero.setTreatments(tratamientos);
	dameDinero.setConditions("Ser rico");
	
	
	
	
	//Creo la vacuna
    vaccineCoronavirus = new Vaccine();
    PetType human = new PetType();
    vaccineCoronavirus.setId(TEST_VACCINE_ID);
    vaccineCoronavirus.setInformation("Vacuna del coronavirus en pruebas, testeado en monos");
    vaccineCoronavirus.setExpiration(LocalDate.of(2021, Month.APRIL, 3));
    vaccineCoronavirus.setName("Vacuna contra el coronavirus");
    vaccineCoronavirus.setPetType(human);
    vaccineCoronavirus.setPrice(75.0);
    vaccineCoronavirus.setProvider("China");
    vaccineCoronavirus.setSideEffects("Puede provocar crisis nerviosas");
    vaccineCoronavirus.setStock(235);

    //Creo el tratamiento
    tratamientoParaAburrimiento = new Treatment();
    PetType raton = new PetType();
    tratamientoParaAburrimiento.setId(TEST_TREATMENT_ID);
    tratamientoParaAburrimiento.setPetType(raton);
    tratamientoParaAburrimiento.setPrice(25.0);
    tratamientoParaAburrimiento.setType("Tratamiento contra el aburrimiento");
    tratamientoParaAburrimiento.setDescription("Para que no te arranques los pelos este mes");

	}
    @WithMockUser(value = "spring")
	@Test
	void testShowInsuranceBaseList() throws Exception {
		mockMvc.perform(get("/insurancesbases")).andExpect(status().isOk())
				.andExpect(model().attributeExists("insurances_bases"))
				.andExpect(model().attributeExists("insurance_base"))
				.andExpect(view().name("insurancesbases/insuranceList"));
	}
    @WithMockUser(value = "spring")
	@Test
	void testShowInsuranceBaseDetails() throws Exception {
    	given(insuranceBaseService.findInsuranceBaseById(TEST_INSURANCE_BASE_ID)).willReturn(dameDinero);
    	mockMvc.perform(get("/insurancesbases/{insuranceBaseId}", TEST_INSURANCE_BASE_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("insurance_base"))
				.andExpect(view().name("insurancesbases/insuranceDetails"));
	}
	
}
