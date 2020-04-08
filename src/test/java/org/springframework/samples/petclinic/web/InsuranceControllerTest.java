package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import org.hamcrest.collection.IsArray;
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
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@WebMvcTest(value = InsuranceController.class, 
includeFilters = { @ComponentScan.Filter(value = PetTypeFormatter.class,type = FilterType.ASSIGNABLE_TYPE), @ComponentScan.Filter(value = VaccineFormatter.class, type = FilterType.ASSIGNABLE_TYPE)
,@ComponentScan.Filter(value = TreatmentFormatter.class,type = FilterType.ASSIGNABLE_TYPE)
,@ComponentScan.Filter(value = InsuranceBaseFormatter.class,type = FilterType.ASSIGNABLE_TYPE)},
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class InsuranceControllerTest {
	
	private static final int TEST_INSURANCE_ID = 1;
	
	private static final int TEST_INSURANCE_BASE_ID = 12;
	
	private static final int TEST_VACCINE_ID = 15;
	
	private static final int TEST_VACCINE_SEGURO_BASE_ID = 8;
	
	private static final int TEST_TREATMENT_ID = 122;
	
	private static final int TEST_TREATMENT_SEGURO_BASE_ID = 11;
	
	private static final int TEST_PET_ID = 54;
	
	

	@Autowired
	private InsuranceController insuranceController;
	
	
	
	

	@MockBean
	private InsuranceService clinicService;
        
       @MockBean
	 private UserService userService;
       
       @MockBean
  	 private InsuranceBaseService insuranceBaseService;
       
       @MockBean
  	 private VaccineService vaccineService;
       
       @MockBean
     private TreatmentService treatmentService;
       
       @MockBean
       private PetService petService;
          
        
      @MockBean
     private AuthoritiesService authoritiesService; 

	@Autowired
	private MockMvc mockMvc;

	private Insurance insuranceParaMamporreo;
	
	private Vaccine  vaccineCoronavirus;
	
	private Vaccine  vaccineExplotacion;
	
	private Treatment treatmentParaAburrimiento;
	
	private Treatment treatmentParaNada;
	
	private InsuranceBase insuranceBaseCalvo;
	
	
	
	@BeforeEach
	void setup() {
		//Creo la vacuna para seguro
		vaccineCoronavirus = new Vaccine();
        PetType mariposa = new PetType();
        mariposa.setId(8);
        mariposa.setName("mariposa");
        vaccineCoronavirus.setId(TEST_VACCINE_ID);
        vaccineCoronavirus.setInformation("Vacuna del coronavirus en pruebas, testeado en monos");
        vaccineCoronavirus.setExpiration(LocalDate.of(2021, 4, 3));
        vaccineCoronavirus.setName("Vacuna contra el coronavirus");
        vaccineCoronavirus.setPetType(mariposa);
        vaccineCoronavirus.setPrice(325.25);
        vaccineCoronavirus.setProvider("China");
        vaccineCoronavirus.setSideEffects("Puede provocar crisis nerviosas");
        vaccineCoronavirus.setStock(235);
      
        
        //Creo el tratamiento
        treatmentParaAburrimiento = new Treatment();
        PetType raton = new PetType();
        raton.setId(9);
        raton.setName("raton");
        treatmentParaAburrimiento.setId(TEST_TREATMENT_ID);
        treatmentParaAburrimiento.setPetType(raton);
        treatmentParaAburrimiento.setPrice(21.4);
        treatmentParaAburrimiento.setType("Tratamiento contra el aburrimiento");
        treatmentParaAburrimiento.setDescription("Para que no te arranques los pelos este mes");
        
        
      //Creo la vacuna para seguro base
        vaccineExplotacion = new Vaccine();
        PetType koala = new PetType();
        koala.setId(12);
        koala.setName("koala");
        vaccineExplotacion.setId(TEST_VACCINE_SEGURO_BASE_ID);
        vaccineExplotacion.setInformation("Vacuna contra la explotacion, testeado en Chao");
        vaccineExplotacion.setExpiration(LocalDate.of(2021, 5, 9));
        vaccineExplotacion.setName("Vacuna contra la explotacion");
        vaccineExplotacion.setPetType(koala);
        vaccineExplotacion.setPrice(32.3);
        vaccineExplotacion.setProvider("Madagascar");
        vaccineExplotacion.setSideEffects("Puede volverte mas tonto");
        vaccineExplotacion.setStock(2);
        
        
      //Creo el tratamiento para seguro base
        treatmentParaNada = new Treatment();
        PetType agaporni = new PetType();
        agaporni.setId(11);
        agaporni.setName("agaporni");
        treatmentParaNada.setId(TEST_TREATMENT_SEGURO_BASE_ID);
        treatmentParaNada.setPetType(agaporni);
        treatmentParaNada.setPrice(43.4);
        treatmentParaNada.setType("Tratamiento inutil");
        treatmentParaNada.setDescription("No sabia que poner");
        
        
        
        //Crear Seguro Base
        
        Set<Vaccine> vacunasBase = new HashSet<Vaccine>();
		vacunasBase.add(vaccineExplotacion);
		Set<Treatment> tratamientosBase = new HashSet<Treatment>();
		tratamientosBase.add(treatmentParaNada);
		insuranceBaseCalvo = new InsuranceBase();
        PetType rata = new PetType();
        rata.setId(14);
        rata.setName("rata");
        insuranceBaseCalvo.setId(TEST_INSURANCE_BASE_ID);
        insuranceBaseCalvo.setPetType(rata);
        insuranceBaseCalvo.setName("Para sobrevivir");
        insuranceBaseCalvo.setConditions("Respirar y poco mas ");
        insuranceBaseCalvo.setTreatments(tratamientosBase);
        insuranceBaseCalvo.setVaccines(vacunasBase);
        
        
        
        
        //Creo el seguro
        
		Set<Vaccine> vacunas = new HashSet<Vaccine>();
		vacunas.add(vaccineCoronavirus);
		Set<Treatment> tratamientos = new HashSet<Treatment>();
		tratamientos.add(treatmentParaAburrimiento);	
		
		insuranceParaMamporreo = new Insurance();
		insuranceParaMamporreo.setId(TEST_INSURANCE_ID);
		insuranceParaMamporreo.setInsuranceDate(LocalDate.of(2020, 4 ,3));
		insuranceParaMamporreo.setInsuranceBase(insuranceBaseCalvo);
		insuranceParaMamporreo.setTreatments(tratamientos);
		insuranceParaMamporreo.setVaccines(vacunas);
		
		Pet oruga = new Pet();
		oruga.setId(38);
		oruga.setName("oruga");
		oruga.setBirthDate(LocalDate.of(2012, 1, 23));
		oruga.setType(rata);
		
		
		given(this.clinicService.findInsuranceById(TEST_INSURANCE_ID)).willReturn(insuranceParaMamporreo);
		given(this.insuranceBaseService.findInsuranceBaseById(TEST_INSURANCE_BASE_ID)).willReturn(insuranceBaseCalvo);
		given(this.vaccineService.findById(TEST_VACCINE_ID)).willReturn(vaccineCoronavirus);
		given(this.vaccineService.findById(TEST_VACCINE_SEGURO_BASE_ID)).willReturn(vaccineExplotacion);
		given(this.treatmentService.findById(TEST_TREATMENT_ID)).willReturn(treatmentParaAburrimiento);
		given(this.treatmentService.findById(TEST_TREATMENT_SEGURO_BASE_ID)).willReturn(treatmentParaNada);
		given(this.petService.findPetById(TEST_PET_ID)).willReturn(oruga);
		
		
	}  


	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {

		mockMvc.perform(get("/insurance/new/{petId}", TEST_PET_ID)).andExpect(status().isOk()).andExpect(model().attributeExists("insurance"))
				.andExpect(view().name("insurances/createOrUpdateInsuranceForm"));
		
		
		
		
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		Set<Vaccine> vacunasBase = new HashSet<Vaccine>();
		vacunasBase.add(vaccineExplotacion);
		Set<Treatment> tratamientosBase = new HashSet<Treatment>();
		tratamientosBase.add(treatmentParaNada);
		Set<InsuranceBase> seguroBase = new HashSet<InsuranceBase>();
		seguroBase.add(insuranceBaseCalvo);
		mockMvc.perform(post("/insurance/new/{petId}", TEST_PET_ID)
							.with(csrf())
							
							.param("insuranceBase", seguroBase.toString())
							.param("vaccines",vacunasBase.toString() )
							.param("treatments", tratamientosBase.toString()))
							
				.andExpect(status().isOk());
	}

//		@WithMockUser(value = "spring")
//    @Test
//    void testProcessCreationFormHasErrors() throws Exception {
//			Set<Vaccine> vacunasBase = new HashSet<Vaccine>();
//			vacunasBase.add(vaccineExplotacion);
//			Set<Treatment> tratamientosBase = new HashSet<Treatment>();
//			tratamientosBase.add(treatmentParaNada);
//			Set<InsuranceBase> seguroBase = new HashSet<InsuranceBase>();
//			seguroBase.add(insuranceBaseCalvo);
//			
//    mockMvc.perform(post("/insurance/new/{petId}", TEST_PET_ID)
//                        .with(csrf())
//                        .param("insuranceBase", "")
//						.param("vaccines",vacunasBase.toString() )
//						.param("treatments", tratamientosBase.toString()))
//                        
//            .andExpect(model().attributeHasErrors(("insurance")))
//            .andExpect(model().attributeHasFieldErrors("insurance", "insuranceBase"))
//            
//            .andExpect(view().name("insurances/createOrUpdateInsuranceForm"));
//}

	
    	@WithMockUser(value = "spring")
    @Test
    void testShowInsurance() throws Exception {
    		Set<Vaccine> vacunas = new HashSet<Vaccine>();
    		vacunas.add(vaccineCoronavirus);
    		Set<Treatment> tratamientos = new HashSet<Treatment>();
    		tratamientos.add(treatmentParaAburrimiento);	
    		
    	mockMvc.perform(get("/insurances/{insuranceId}", TEST_INSURANCE_ID)).andExpect(status().isOk())
    	.andExpect(model().attribute("insurance", hasProperty("insuranceDate", is(LocalDate.of(2020, 4, 3)))))
    	.andExpect(model().attribute("insurance", hasProperty("insuranceBase", is(insuranceBaseCalvo))))
    	.andExpect(model().attribute("insurance", hasProperty("vaccines", is(vacunas))))
    	.andExpect(model().attribute("insurance", hasProperty("treatments", is(tratamientos))))
			
			.andExpect(view().name("insurances/insuranceDetails"))	;
}
	


}
