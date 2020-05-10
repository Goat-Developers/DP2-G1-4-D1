package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.repository.InsuranceBaseRepository;
import org.springframework.samples.petclinic.repository.InsuranceRepository;
import org.springframework.samples.petclinic.repository.VaccineRepository;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace=Replace.NONE)
class VaccineServiceTests {      

	private static final int TEST_VACCINE_DELETE = 14;
	private static final int TEST_VACCINE_EXPIRATED_ID1 = 2;
	private static final int TEST_VACCINE_EXPIRATED_ID2 = 4;
	private static final int TEST_VACCINE_EXPIRATED_ID3 = 8;

	@Mock
	private VaccineRepository vaccineRepository;
	
    @Autowired
	protected VaccineService vaccineService;

    @Autowired
    protected PetService petService;
    
    @Mock
    private InsuranceRepository insuranceRepository;

    @Autowired
    protected InsuranceService insuranceService; 
    
    @Mock
    private InsuranceBaseRepository insuranceBaseRepository;

    @Autowired
    protected InsuranceBaseService insuranceBaseService;

    private Vaccine vaccineCoronavirus;

	@Test
	void shouldFindAll() {
		Collection<Vaccine> vaccines = this.vaccineService.findAll();
		assertThat(vaccines.size()).isEqualTo(14);
	}

	@Test
	void shouldFindById() {
		Vaccine vaccine = this.vaccineService.findById(1);
		assertThat(vaccine.getId()).isEqualTo(1);
	}

	@Test
	void shouldFindAllExpirated() {
		Vaccine vaccine2 = this.vaccineService.findById(TEST_VACCINE_EXPIRATED_ID1);
		Vaccine vaccine4 = this.vaccineService.findById(TEST_VACCINE_EXPIRATED_ID2);
		Vaccine vaccine8 = this.vaccineService.findById(TEST_VACCINE_EXPIRATED_ID3);
		List<Vaccine> vaccinesList1 = this.vaccineService.findAllExpirated();
		List<Vaccine> vaccinesList2 = new ArrayList<>();
		vaccinesList2.add(vaccine2);
		vaccinesList2.add(vaccine4);
		vaccinesList2.add(vaccine8);
		assertThat(vaccinesList1.get(0).getExpiration()).isBefore(LocalDate.now());
		assertThat(vaccinesList1.get(1).getExpiration()).isBefore(LocalDate.now());
		assertThat(vaccinesList1.get(1)).isEqualTo(vaccinesList2.get(1));
		assertThat(vaccinesList1.get(2)).isEqualTo(vaccinesList2.get(2));
		assertThat(vaccinesList1.size()).isEqualTo(4);
	}
	
	@ParameterizedTest
	@ValueSource(ints= {8,7,3,2})
	void shouldFindVaccinesWithLowStock(int argument) {
		Vaccine test = this.vaccineService.findById(argument);
		List<Vaccine> listaStock = this.vaccineService.findVaccinesWithLowStock();
		assertThat(listaStock).contains(test);
	}

	
	@ParameterizedTest
	@ValueSource(ints= {1,5})
	void shouldNotFindVaccinesWithLowStock(int argument) {
		Vaccine test = this.vaccineService.findById(argument);
		List<Vaccine> listaStock = this.vaccineService.findVaccinesWithLowStock();
		Assertions.assertFalse(listaStock.contains(test));
	}
	@Test
	@Transactional
	public void shouldInsertVaccine() {
		Collection<Vaccine> vaccines = this.vaccineService.findAll();
		int found = vaccines.size();

		vaccineCoronavirus = new Vaccine();
        vaccineCoronavirus.setInformation("Vacuna del coronavirus en pruebas, testeado en monos");
        vaccineCoronavirus.setExpiration(LocalDate.of(2021, Month.APRIL, 3));
        vaccineCoronavirus.setName("Vacuna contra el coronavirus");
        vaccineCoronavirus.setPrice(325.25);
        vaccineCoronavirus.setProvider("China");
        vaccineCoronavirus.setSideEffects("Puede provocar crisis nerviosas");
        vaccineCoronavirus.setStock(235);
        
        Collection<PetType> petTypes = this.petService.findPetTypes();
        vaccineCoronavirus.setPetType(EntityUtils.getById(petTypes, PetType.class, 4));

		this.vaccineService.saveVaccine(vaccineCoronavirus);

		vaccines = this.vaccineService.findAll();
		assertThat(vaccines.size()).isEqualTo(found + 1);
		// checks that id has been generated
        assertThat(vaccineCoronavirus.getId()).isNotNull();
	}

	@Test
	@Transactional
	void shouldDeleteVaccine() {
		Collection<Vaccine> vaccines = this.vaccineService.findAll();
		int found = vaccines.size();
		assertThat(vaccines.size()).isEqualTo(14);
		
		Vaccine vaccine = this.vaccineService.findById(TEST_VACCINE_DELETE);
		this.vaccineService.deleteVaccine(vaccine);
		
		vaccines = this.vaccineService.findAll();
		assertThat(vaccines.size()).isEqualTo(found - 1);
		assertThat(this.vaccineService.findById(TEST_VACCINE_DELETE)).isNull();

	}
	
	@ParameterizedTest
	@ValueSource(ints= {15,-6,100})
	void shouldFailFindSingleVaccineById(int argument) {
		Assertions.assertThrows(NullPointerException.class, () -> {this.vaccineService.findById(argument).getInformation();});
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"Dientes","2020/04/15","null","14.2"})
	void shouldFailSetTreatmentPriceByStrings(String argument) {
		try {
			this.vaccineService.findById(1).setPrice(Double.parseDouble(argument));
		} catch (NumberFormatException ex) {
			Logger.getLogger(VaccineServiceTests.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Test
	public void addNullVaccineTest() {
		Vaccine dummy = null;
		assertThrows(Exception.class, () -> this.vaccineService.saveVaccine(dummy));
	} 
	 
	@Test
	public void testInvalidVaccine() throws Exception {
       Vaccine vaccine = new Vaccine();
       vaccine.setId(-3);
       VaccineRepository vaccineRepository = mock(VaccineRepository.class);
       when(vaccineRepository.findById(-3)).thenThrow(new RuntimeException());
       VaccineService vaccineService = new VaccineService(vaccineRepository);
       assertThrows(RuntimeException.class, () -> vaccineService.findById((vaccine.getId())));   
	}
	
	@Test
    void shouldFindPetTypes() {
        PetType samplePetType = new PetType();
        samplePetType.setId(1);
        samplePetType.setName("Test");
        List<PetType> samplePets = new ArrayList<PetType>();
        samplePets.add(samplePetType);
        when(vaccineRepository.findPetTypes()).thenReturn(samplePets);
        VaccineService vacService = new VaccineService(vaccineRepository);
        Collection<PetType> petTypes = vacService.findPetTypes();
        assertThat(petTypes).hasSize(1);
        PetType pet = petTypes.iterator().next();
        assertThat(pet.getId()).isEqualTo(1);
        assertThat(pet.getName()).isEqualTo("Test");
    }
	
}
