package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Insurance;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.InsuranceRepository;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class InsuranceServiceTest {
	
	
   @Autowired
   	protected InsuranceService insuranceService; 
	@Autowired
	protected VaccineService vaccineService;

    @Autowired
    protected PetService petService;

    @Autowired
    protected InsuranceBaseService insuranceBaseService;
    
    @Autowired
    protected 
    TreatmentService treatmentService;
    @MockBean
    private AuthoritiesService authoritiesService; 
    
    @Mock
    private InsuranceRepository insuranceRepostiory;

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
		Collection<PetType> mariposa = this.petService.findPetTypes();
    	vaccineCoronavirus.setPetType(EntityUtils.getById(mariposa, PetType.class, 4));
        vaccineCoronavirus.setId(1);
        vaccineCoronavirus.setInformation("Vacuna del coronavirus en pruebas, testeado en monos");
        vaccineCoronavirus.setExpiration(LocalDate.of(2021, 4, 3));
        vaccineCoronavirus.setName("Vacuna contra el coronavirus");
        
        vaccineCoronavirus.setPrice(325.25);
        vaccineCoronavirus.setProvider("China");
        vaccineCoronavirus.setSideEffects("Puede provocar crisis nerviosas");
        vaccineCoronavirus.setStock(235);
      
        
        //Creo el tratamiento
        treatmentParaAburrimiento = new Treatment();
        Collection<PetType> raton = this.petService.findPetTypes();
    	treatmentParaAburrimiento.setPetType(EntityUtils.getById(raton, PetType.class, 1));
        treatmentParaAburrimiento.setId(1);
        treatmentParaAburrimiento.setPrice(21.4);
        treatmentParaAburrimiento.setType("Tratamiento contra el aburrimiento");
        treatmentParaAburrimiento.setDescription("Para que no te arranques los pelos este mes");
        
        
      //Creo la vacuna para seguro base
        vaccineExplotacion = new Vaccine();
        Collection<PetType> koala = this.petService.findPetTypes();
        vaccineExplotacion.setPetType(EntityUtils.getById(koala, PetType.class, 5));
      
        vaccineExplotacion.setId(2);
        vaccineExplotacion.setInformation("Vacuna contra la explotacion, testeado en Chao");
        vaccineExplotacion.setExpiration(LocalDate.of(2021, 5, 9));
        vaccineExplotacion.setName("Vacuna contra la explotacion");
        vaccineExplotacion.setPrice(32.3);
        vaccineExplotacion.setProvider("Madagascar");
        vaccineExplotacion.setSideEffects("Puede volverte mas tonto");
        vaccineExplotacion.setStock(2);
        
        
      //Creo el tratamiento para seguro base
        treatmentParaNada = new Treatment();
        Collection<PetType> agaporni = this.petService.findPetTypes();
        treatmentParaNada.setPetType(EntityUtils.getById(agaporni, PetType.class, 2));
       
        treatmentParaNada.setId(3);
        treatmentParaNada.setPrice(43.4);
        treatmentParaNada.setType("Tratamiento inutil");
        treatmentParaNada.setDescription("No sabia que poner");
        
        
        
        //Crear Seguro Base
        
        Set<Vaccine> vacunasBase = new HashSet<Vaccine>();
		vacunasBase.add(vaccineExplotacion);
		Set<Treatment> tratamientosBase = new HashSet<Treatment>();
		tratamientosBase.add(treatmentParaNada);
		insuranceBaseCalvo = new InsuranceBase();
		 Collection<PetType> rata = this.petService.findPetTypes();
	        vaccineExplotacion.setPetType(EntityUtils.getById(rata, PetType.class, 3));
        
        insuranceBaseCalvo.setId(5);
       
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
		insuranceParaMamporreo.setId(5);
		insuranceParaMamporreo.setInsuranceDate(LocalDate.of(2020, 4 ,3));
		insuranceParaMamporreo.setInsuranceBase(insuranceBaseCalvo);
		insuranceParaMamporreo.setTreatments(tratamientos);
		insuranceParaMamporreo.setVaccines(vacunas);
	}
		

	
	
	@Test
	void shouldFindInsurances() {
		Collection<Insurance> insurance = this.insuranceService.findInsurances();
		assertThat(insurance.size()).isEqualTo(2);
	}
	
	@Test
	void shouldFindById() {
		Insurance insurance = this.insuranceService.findInsuranceById(1);
		assertThat(insurance.getId()).isEqualTo(1);
	}
	
	
	@Test
	@Transactional
	public void  shouldSaveInsurance() {
		Collection<Insurance> insurance = this.insuranceService.findInsurances();
		int total = insurance.size();
		this.insuranceService.saveInsurance(insuranceParaMamporreo);

		insurance = this.insuranceService.findInsurances();
		Set<Vaccine> vacunas = insuranceParaMamporreo.getVaccines();
		comprueboQueDecrementaElStockEn1(vacunas);
		assertThat(insurance.size()).isEqualTo(total + 1);
		assertThat(insuranceParaMamporreo.getId()).isNotNull();
	}
	
	private void comprueboQueDecrementaElStockEn1(Set<Vaccine> vacunas) {
		for (Vaccine a: vacunas) {
			a.setStock(a.getStock()-1);
			this.vaccineService.saveVaccine(a);
			assertThat(a.getStock()).isEqualTo(this.vaccineService.findById(a.getId()).getStock());
		}
	}




	@Test
    void shouldFindVaccinesl() {
        Collection<Vaccine> vaccines = this.vaccineService.findAll();
        assertThat(vaccines.size()).isEqualTo(8);
    }
	
	@Test
    void shouldFindTreatments() {
        Collection<Treatment> treatment = this.treatmentService.findAll();
        assertThat(treatment.size()).isEqualTo(6);
    }
	
	@Test
    void shouldFindVaccinesByPetTypeId() {
      
		Collection<Vaccine> vaccines = this.insuranceService.findVaccinesByPetTypeId(4);
		assertThat(vaccines.size()).isEqualTo(0);
		
    }
	
	@Test
    void shouldFindTreatmentsByPetTypeId() {
      
		Collection<Treatment> treatment = this.insuranceService.findTreatmentsByPetTypeId(4);
		assertThat(treatment.size()).isEqualTo(1);
		
    }
	
	
	@ParameterizedTest
	@ValueSource(ints= {-3,0,230})
	void shouldFailFindSingleInsureById(int argument) {
		Assertions.assertThrows(NullPointerException.class, () -> {this.insuranceService.findInsuranceById(argument).getId();});
	}
	
	@ParameterizedTest
	@ValueSource(ints= {-4,0,760})
	void shouldFailFindVaccinesByPetTypeId(Integer argument) {
		Collection<Vaccine> vaccines = this.insuranceService.findVaccinesByPetTypeId(argument);
		assertThat(vaccines);

	}
	@ParameterizedTest
	@ValueSource(ints= {-49,0,123})
	void shouldFailFindTreatmentsByPetTypeId(Integer argument) {
		Collection<Treatment> treatment = this.insuranceService.findTreatmentsByPetTypeId(argument);
		assertThat(treatment);

	}
	
	@Test
	void shoulPositiveFindInsurance() {

		InsuranceService service = new InsuranceService(insuranceRepostiory);
		List<Insurance> listaInsurance = new ArrayList<Insurance>();
		listaInsurance.add(insuranceParaMamporreo);
		when(service.findInsurances()).thenReturn(listaInsurance);	
		assertThat(listaInsurance).hasSize(1);
		Insurance insurancePrueba = listaInsurance.iterator().next();
		assertThat(insurancePrueba.getId()).isEqualTo(5);
		
	}
	@Test
	void shouldNegativeFindInsurance() {

		
		InsuranceService service = new InsuranceService(insuranceRepostiory);
		List<Insurance> listaInsurance = new ArrayList<Insurance>();
		listaInsurance.add(insuranceParaMamporreo);
		when(service.findInsurances()).thenReturn(listaInsurance);	
		Assertions.assertThrows(Exception.class, ()->{listaInsurance.get(1);});
		when(service.findInsuranceById(19)).thenThrow(new RuntimeException());
		Assertions.assertThrows(RuntimeException.class, ()-> {service.findInsuranceById(19);});
		
	}
	
	
	
	
	
	
	
	

}
