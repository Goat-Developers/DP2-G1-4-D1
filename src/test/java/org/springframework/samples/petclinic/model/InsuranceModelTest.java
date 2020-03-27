package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.samples.petclinic.web.VaccineController;

public class InsuranceModelTest {
	
	private static final int TEST_INSURANCE_ID = 1;
	
	private static final int TEST_INSURANCE_BASE_ID = 12;
	
	private static final int TEST_VACCINE_ID = 15;
	
	private static final int TEST_VACCINE_SEGURO_BASE_ID = 8;
	
	private static final int TEST_TREATMENT_ID = 122;
	
	private static final int TEST_TREATMENT_SEGURO_BASE_ID = 11;

	  
      @MockBean
 	 private InsuranceBaseService insuranceBaseService;
      
      @MockBean
 	 private VaccineService vaccineService;
      
      @MockBean
    private TreatmentService treatmentService;
	  
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
		}
	@Test
	public void testGetprecioInsurance() throws Exception{
			
		
		Double vaccinePrice= vaccineCoronavirus.getPrice();
		Double treatmentPrice= treatmentParaAburrimiento.getPrice();
		Double insuranceBasePrice= (vaccineExplotacion.getPrice() + treatmentParaNada.getPrice())*0.7;
		Double res = vaccinePrice + treatmentPrice + insuranceBasePrice;
		
		Assertions.assertEquals(insuranceParaMamporreo.getInsurancePrice(), res);
			
	}

}
