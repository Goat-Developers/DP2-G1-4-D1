package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.samples.petclinic.web.InsuranceBaseController;


import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;


public class InsuranceBaseTests {
	
	private static final int TEST_INSURANCE_BASE_ID = 1;
	private static final int TEST_VACCINE_ID = 1;
	private static final int TEST_TREATMENT_ID = 1;
	
	@Autowired
	private InsuranceBaseController insuranceBaseController;

	@MockBean
	private InsuranceBaseService insuranceBaseService;
	
	@MockBean
	private VaccineService vaccineService;
	
	@MockBean
	private TreatmentService treatmentService;
	
	private InsuranceBase dameDinero;
	private Vaccine vaccineCoronavirus;
	private Treatment tratamientoParaAburrimiento;
	
	@BeforeEach
	void setup() {	
	
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

    tratamientoParaAburrimiento = new Treatment();
    PetType raton = new PetType();
    tratamientoParaAburrimiento.setId(TEST_TREATMENT_ID);
    tratamientoParaAburrimiento.setPetType(raton);
    tratamientoParaAburrimiento.setPrice(25.0);
    tratamientoParaAburrimiento.setType("Tratamiento contra el aburrimiento");
    tratamientoParaAburrimiento.setDescription("Para que no te arranques los pelos este mes");
    
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
	
	
	}
	
	@Test
	void testGetInsuranceBasePrice() throws Exception {	       
	      
		 
		 Double priceVaccine = vaccineCoronavirus.getPrice();
		 Double priceTreatment = tratamientoParaAburrimiento.getPrice();
		 Double price2 = 0.7*(priceVaccine+priceTreatment);
		 
		 Assertions.assertEquals(dameDinero.getPrice(), price2);
	    }

}
