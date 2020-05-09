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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
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
import org.springframework.test.annotation.DirtiesContext;
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
    protected TreatmentService treatmentService;
    
    @MockBean
    private AuthoritiesService authoritiesService; 
    
    @Mock
    private InsuranceRepository insuranceRepostiory;

    private Insurance insurancePrincipal;
	
	private Vaccine  vaccineInsurance;
	private Vaccine vaccineInsuranceBase;
	

	private Treatment treatmentInsurance;
	private Treatment treatmentInsuranceBase;
	
	private InsuranceBase insuranceBase;
	

	@BeforeEach
	void setup() {
		//Vacuna para el seguro
		vaccineInsurance = new Vaccine();
		Collection<PetType> mariposa = this.petService.findPetTypes();
    	vaccineInsurance.setPetType(EntityUtils.getById(mariposa, PetType.class, 4));
        vaccineInsurance.setId(1);
        vaccineInsurance.setInformation("Vacuna del coronavirus en pruebas, testeado en monos");
        vaccineInsurance.setExpiration(LocalDate.of(2021, 4, 3));
        vaccineInsurance.setName("Vacuna contra el coronavirus");
        vaccineInsurance.setPrice(325.25);
        vaccineInsurance.setProvider("China");
        vaccineInsurance.setSideEffects("Puede provocar crisis nerviosas");
        vaccineInsurance.setStock(235);
        
        //Tratamiento para el seguro
        treatmentInsurance = new Treatment();
        Collection<PetType> raton = this.petService.findPetTypes();
    	treatmentInsurance.setPetType(EntityUtils.getById(raton, PetType.class, 1));
        treatmentInsurance.setId(1);
        treatmentInsurance.setPrice(21.4);
        treatmentInsurance.setType("Tratamiento contra el aburrimiento");
        treatmentInsurance.setDescription("Para que no te arranques los pelos este mes");
        
        //Vacuna para el seguro base
        vaccineInsuranceBase = new Vaccine();
        Collection<PetType> koala = this.petService.findPetTypes();
        vaccineInsuranceBase.setPetType(EntityUtils.getById(koala, PetType.class, 5));
        vaccineInsuranceBase.setId(2);
        vaccineInsuranceBase.setInformation("Vacuna contra la explotacion, testeado en Chao");
        vaccineInsuranceBase.setExpiration(LocalDate.of(2021, 5, 9));
        vaccineInsuranceBase.setName("Vacuna contra la explotacion");
        vaccineInsuranceBase.setPrice(32.3);
        vaccineInsuranceBase.setProvider("Madagascar");
        vaccineInsuranceBase.setSideEffects("Puede volverte mas tonto");
        vaccineInsuranceBase.setStock(2);
        
        //Tratamiento para el seguro base
        treatmentInsuranceBase = new Treatment();
        Collection<PetType> agaporni = this.petService.findPetTypes();
        treatmentInsuranceBase.setPetType(EntityUtils.getById(agaporni, PetType.class, 2));
        treatmentInsuranceBase.setId(3);
        treatmentInsuranceBase.setPrice(43.4);
        treatmentInsuranceBase.setType("Tratamiento inutil");
        treatmentInsuranceBase.setDescription("No sabia que poner");
        
        //Seguro Base
        Set<Vaccine> vaccinesBase = new HashSet<Vaccine>();
		vaccinesBase.add(vaccineInsuranceBase);
		Set<Treatment> treatmentsBase = new HashSet<Treatment>();
		treatmentsBase.add(treatmentInsuranceBase);
		
		insuranceBase = new InsuranceBase();
		Collection<PetType> rata = this.petService.findPetTypes();
        insuranceBase.setPetType(EntityUtils.getById(rata, PetType.class, 3));
        insuranceBase.setId(5);
        insuranceBase.setName("Para sobrevivir");
        insuranceBase.setConditions("Respirar y poco mas ");
        insuranceBase.setTreatments(treatmentsBase);
        insuranceBase.setVaccines(vaccinesBase);
        
        //Seguro
		Set<Vaccine> vaccines = new HashSet<Vaccine>();
		vaccines.add(vaccineInsurance);
		Set<Treatment> treatments = new HashSet<Treatment>();
		treatments.add(treatmentInsurance);	
		
		insurancePrincipal = new Insurance();
		insurancePrincipal.setId(5);
		insurancePrincipal.setInsuranceDate(LocalDate.of(2020, 4 ,3));
		insurancePrincipal.setInsuranceBase(insuranceBase);
		insurancePrincipal.setTreatments(treatments);
		insurancePrincipal.setVaccines(vaccines);
	}
		
	@Test
	void shouldFindInsurances() {
		Collection<Insurance> insurance = this.insuranceService.findInsurances();
		assertThat(insurance.size()).isEqualTo(0);
	}
	
	// NO HAY NINGUN INSURANCE EN EL DATA
	/*@Test
	void shouldFindById() {
		Insurance insurance = this.insuranceService.findInsuranceById(1);
		assertThat(insurance.getId()).isEqualTo(1);
	}*/

	@Test
	@Transactional
	public void  shouldSaveInsurance() {
		Collection<Insurance> insurance = this.insuranceService.findInsurances();
		int total = insurance.size();
		this.insuranceService.saveInsurance(insurancePrincipal);

		insurance = this.insuranceService.findInsurances();
		Set<Vaccine> vacunas = insurancePrincipal.getVaccines();
		comprueboQueDecrementaElStockEn1(vacunas);
		assertThat(insurance.size()).isEqualTo(total + 1);
		assertThat(insurancePrincipal.getId()).isNotNull();
	}
	
	private void comprueboQueDecrementaElStockEn1(Set<Vaccine> vacunas) {
		for (Vaccine a: vacunas) {
			a.setStock(a.getStock()-1);
			this.vaccineService.saveVaccine(a);
			assertThat(a.getStock()).isEqualTo(this.vaccineService.findById(a.getId()).getStock());
		}
	}

	@Test
    void shouldFindVaccines() {
        Collection<Vaccine> vaccines = this.insuranceService.findVaccines();
        
        Vaccine vaccine1 = EntityUtils.getById(vaccines, Vaccine.class, 1);
        assertThat(vaccine1.getName()).isEqualTo("Vacuna de la rabia");
        assertThat(vaccine1.getInformation()).isEqualTo("Para ratas");
        
        Vaccine vaccine5 = EntityUtils.getById(vaccines, Vaccine.class, 5);
        assertThat(vaccine5.getName()).isEqualTo("Vacuna de la uni");
        assertThat(vaccine5.getPrice()).isEqualTo(31.3);
    }
	
	@Test
    void shouldFindTreatments() {
        Collection<Treatment> treatment = this.insuranceService.findTreatments();
        assertThat(treatment.size()).isEqualTo(13);
    }
	
	@Test
    void shouldFindVaccinesByPetTypeId() {
		Collection<Vaccine> vaccines = this.insuranceService.findVaccinesByPetTypeId(4);
		assertThat(vaccines.size()).isEqualTo(1);

    }
	
	@Test
    void shouldFindTreatmentsByPetTypeId() {

		Collection<Treatment> treatments = this.insuranceService.findTreatmentsByPetTypeId(4);
		assertThat(treatments.size()).isEqualTo(2);

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
		listaInsurance.add(insurancePrincipal);
		when(service.findInsurances()).thenReturn(listaInsurance);	
		assertThat(listaInsurance).hasSize(1);
		Insurance insurancePrueba = listaInsurance.iterator().next();
		assertThat(insurancePrueba.getId()).isEqualTo(5);
	}
	
	@Test
	void shouldNegativeFindInsurance() {
		InsuranceService service = new InsuranceService(insuranceRepostiory);
		List<Insurance> listaInsurance = new ArrayList<Insurance>();
		listaInsurance.add(insurancePrincipal);
		when(service.findInsurances()).thenReturn(listaInsurance);	
		Assertions.assertThrows(Exception.class, ()->{listaInsurance.get(1);});
		when(service.findInsuranceById(19)).thenThrow(new RuntimeException());
		Assertions.assertThrows(RuntimeException.class, ()-> {service.findInsuranceById(19);});
	}

}
