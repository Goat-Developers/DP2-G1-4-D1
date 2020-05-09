package org.springframework.samples.petclinic.web;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.remote.server.AccessManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Insurance;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.VaccinationSchedule;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ModelMap;

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

	
	
	private static final int TEST_PET_TYPE_ID = 8;
	
	private static final int TEST_VACCINE_ID = 1;
	private static final int TEST_TREATMENT_ID = 13;
	private static final int TEST_VACCINEC_ID = 2;
	private static final int TEST_VACCINEC_BASE_ID = 4;
	private static final int TEST_TREATMENT_BASE_ID = 32;
	private static final int TEST_INSURANCE_ID = 4;
	private static final int TEST_INSURANCE_BASE_ID = 5;

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
	private TreatmentService treatmentService;
        
    @MockBean
	private UserService userService;
        
    @MockBean
    private AuthoritiesService authoritiesService; 

	@Autowired
	private MockMvc mockMvc;

	private Vaccine vaccine;
	private Vaccine vaccineCaducated;
	private Vaccine vaccineCaducated2;
	private Treatment treatment;
	private Treatment treatment2;
	private Insurance insurance;
	private InsuranceBase insuranceBase;

	@BeforeEach
	void setup() {
		
		vaccine = new Vaccine();
	    PetType human = new PetType();
	    human.setId(5);
	    human.setName("human");
	    vaccine.setId(TEST_VACCINE_ID);
	    vaccine.setInformation("Vacuna del coronavirus en pruebas, testeado en monos");
		vaccine.setExpiration(LocalDate.of(2021, Month.APRIL, 3));
	    vaccine.setName("Vacuna contra el coronavirus");
	    vaccine.setPetType(human);
	    vaccine.setPrice(75.0);
	    vaccine.setProvider("China");
	    vaccine.setSideEffects("Puede provocar crisis nerviosas");
	    vaccine.setStock(235);

	    treatment = new Treatment();
	    PetType raton = new PetType();
	    raton.setId(6);
	    raton.setName("raton");
	    treatment.setId(TEST_TREATMENT_ID);
	    treatment.setPetType(raton);
	    treatment.setPrice(25.0);
	    treatment.setType("Tratamiento contra el aburrimiento");
	    treatment.setDescription("Para que no te arranques los pelos este mes");
	    
	    vaccineCaducated = new Vaccine();
	    PetType humano = new PetType();
	    humano.setId(5);
	    humano.setName("human");
	    vaccineCaducated.setId(TEST_VACCINEC_ID);
	    vaccineCaducated.setInformation("Vacuna del coronavirus en pruebas, testeado en monos");
	    vaccineCaducated.setExpiration(LocalDate.of(2012, Month.APRIL, 3));
	    vaccineCaducated.setName("Vacuna contra el coronavirus");
	    vaccineCaducated.setPetType(human);
	    vaccineCaducated.setPrice(75.0);
	    vaccineCaducated.setProvider("China");
	    vaccineCaducated.setSideEffects("Puede provocar crisis nerviosas");
	    vaccineCaducated.setStock(235);
	    
	    vaccineCaducated2 = new Vaccine();
        PetType koala = new PetType();
        koala.setId(12);
        koala.setName("koala");
        vaccineCaducated2.setId(TEST_VACCINEC_BASE_ID);
        vaccineCaducated2.setInformation("Vacuna contra la explotacion, testeado en Chao");
        vaccineCaducated2.setExpiration(LocalDate.of(2010, 5, 9));
        vaccineCaducated2.setName("Vacuna contra la explotacion");
        vaccineCaducated2.setPetType(koala);
        vaccineCaducated2.setPrice(32.3);
        vaccineCaducated2.setProvider("Madagascar");
        vaccineCaducated2.setSideEffects("Puede volverte mas tonto");
        vaccineCaducated2.setStock(2);
        
        
        treatment2 = new Treatment();
        PetType agaporni = new PetType();
        agaporni.setId(11);
        agaporni.setName("agaporni");
        treatment2.setId(TEST_TREATMENT_BASE_ID);
        treatment2.setPetType(agaporni);
        treatment2.setPrice(43.4);
        treatment2.setType("Tratamiento inutil");
        treatment2.setDescription("No sabia que poner");

	    Set<Vaccine> vacunas = new HashSet<Vaccine>();
		vacunas.add(vaccineCaducated);
		Set<Treatment> tratamientos = new HashSet<Treatment>();
		tratamientos.add(treatment);
		Set<Vaccine> vacunas2 = new HashSet<Vaccine>();
		vacunas2.add(vaccineCaducated2);
		Set<Treatment> tratamientos2 = new HashSet<Treatment>();
		tratamientos.add(treatment2);
		LocalDate date1 = LocalDate.of(2020, Month.JULY, 10);
		List<LocalDate> dates = new ArrayList<>();
		dates.add(date1);
		
		
		
		insuranceBase = new InsuranceBase();
		PetType mascota = new PetType();
		mascota.setId(8);
		mascota.setName("mascota");
		insuranceBase.setId(TEST_INSURANCE_BASE_ID);
		insuranceBase.setName("Seguro para ganar dinero");
		insuranceBase.setPetType(mascota);
		insuranceBase.setVaccines(vacunas2);
		insuranceBase.setTreatments(tratamientos2);
		insuranceBase.setConditions("Ser rico");	
		
		insurance = new Insurance();
		insurance.setId(TEST_INSURANCE_ID);
		insurance.setInsuranceDate(LocalDate.of(2020, 4 ,3));
		insurance.setInsuranceBase(insuranceBase);
		insurance.setTreatments(tratamientos);
		insurance.setVaccines(vacunas);
		
		Collection<Insurance> insurances = new ArrayList<Insurance>();
		insurances.add(insurance);
		List<InsuranceBase> insurancesBase = new ArrayList<InsuranceBase>();
		insurancesBase.add(insuranceBase);
		
		
		given(this.clinicService.findById(TEST_VACCINE_ID)).willReturn(vaccine);
		given(insuranceService.findInsuranceById(TEST_INSURANCE_ID)).willReturn(insurance);
		given(insuranceBaseService.findInsuranceBaseById(TEST_INSURANCE_BASE_ID)).willReturn(insuranceBase);
		given(insuranceService.findInsurances()).willReturn(insurances);
		given(insuranceBaseService.findInsurancesBases()).willReturn(insurancesBase);

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
				.andExpect(model().attribute("vaccine", hasProperty("petType", is(vaccine.getPetType()))))
				.andExpect(model().attribute("vaccine", hasProperty("provider", is("China"))))
				.andExpect(model().attribute("vaccine", hasProperty("sideEffects", is("Puede provocar crisis nerviosas"))))
				.andExpect(model().attribute("vaccine", hasProperty("stock",is(235))))
				.andExpect(view().name("vaccine/vaccineDetails"));
	}
        
        @WithMockUser(value = "spring")
    	@Test
    	void testListadoVaccine() throws Exception {
    		mockMvc.perform(get("/vaccine")).andExpect(status().isOk())
    				.andExpect(model().attributeExists("vaccine"))
    				.andExpect(view().name("vaccine/vaccineList"));
    	}
        
        
        
        @WithMockUser(value = "spring")
    @Test
    void testListVaccineExpirated() throws Exception {
       mockMvc.perform(get("/vaccine/expirated")).andExpect(status().isOk()).andExpect(model().attributeExists("vaccine"))
       			.andExpect(view().name("vaccine/vaccineExpirated"));
    }	
        
        @WithMockUser(value = "spring")
    @Test
    void testListVaccineLowStock() throws Exception {
       mockMvc.perform(get("/vaccine/stock")).andExpect(status().isOk()).andExpect(model().attributeExists("vaccine"))
       			.andExpect(view().name("vaccine/vaccineStock"));
    }	 
        
        @WithMockUser(value = "spring")
        @Test
        void testDeleteVaccine() throws Exception {
    		
        	mockMvc.perform(get("/vaccine/{vaccineId}/delete", TEST_VACCINEC_ID))
        			.andExpect(status().is3xxRedirection())		
        			.andExpect(view().name("redirect:/vaccine"));  
        	assertThat(clinicService.findById(TEST_VACCINEC_ID)).isEqualTo(null);
        	assertThat(insuranceService.findInsuranceById(TEST_INSURANCE_ID).getVaccines()
        			.contains(clinicService.findById(TEST_VACCINEC_ID))).isFalse();
           
           mockMvc.perform(get("/vaccine/{vaccineId}/delete", TEST_VACCINEC_BASE_ID))
           .andExpect(status().is3xxRedirection())
  			.andExpect(view().name("redirect:/vaccine"));
       	assertThat(clinicService.findById(TEST_VACCINEC_BASE_ID)).isEqualTo(null);
       	assertThat(insuranceBaseService.findInsuranceBaseById(TEST_INSURANCE_BASE_ID).getVaccines()
    			.contains(clinicService.findById(TEST_VACCINEC_BASE_ID))).isFalse();


        }	   
        
  
    	
        


}
