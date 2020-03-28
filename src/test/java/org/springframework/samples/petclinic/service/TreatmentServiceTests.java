package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.repository.TreatmentRepository;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TreatmentServiceTests {
	
	@Autowired
	protected TreatmentService treatmentService;
	
	@Mock
	private TreatmentRepository treatmentRepository;
	
	@Autowired
	protected PetService petService;
	
	@Test
	void shouldFindSingleTreatmentById() {
		Treatment treatment = this.treatmentService.findById(1);
		assertThat(treatment.getDescription()).isNotEmpty();
		assertThat(treatment.getDescription()).isEqualTo("Limpieza de dientes");
		assertThat(treatment.getPrice()).isEqualTo(65.7);
		assertThat(treatment.getType()).isNotEqualTo("Pelo");
		assertThat(treatment.getPetType()).isNotNull();
		assertThat(treatment.getPetType().getName()).isEqualTo("snake");
		assertThat(treatment.getPetType().getId()).isEqualTo(4);
	}
	
	@ParameterizedTest
	@ValueSource(ints= {0,-6,100})
	void shouldFailFindSingleTreatmentById(int argument) {
		Assertions.assertThrows(NullPointerException.class, () -> {this.treatmentService.findById(argument).getDescription();});
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"Dientes","2020/04/15","null","14.2"})
	void shouldFailSetTreatmentPriceByStrings(String argument) {
		try {
			this.treatmentService.findById(1).setPrice(Double.parseDouble(argument));
		} catch (NumberFormatException ex) {
			Logger.getLogger(TreatmentServiceTests.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	@Test
	void shouldFindAllTreatments() {
		Collection<Treatment> treatments = this.treatmentService.findAll();
		
		Treatment treatment1 = EntityUtils.getById(treatments, Treatment.class, 1);
		assertThat(treatment1.getPrice()).isEqualTo(65.7);
		assertThat(treatment1.getType()).isNotEqualTo("Pelo");
		assertThat(treatment1.getPetType()).isNotNull();
		
		Treatment treatment3 = EntityUtils.getById(treatments, Treatment.class, 3);
		assertThat(treatment3.getType()).isEqualTo("Dientes");
		assertThat(treatment3.getPrice()).isNotEqualTo(10);
	}
	
	@Test
	void shouldFindAllPetTypes() {
		Collection<PetType> petTypes = this.treatmentService.findPetTypes();

		PetType petType2 = EntityUtils.getById(petTypes, PetType.class, 2);
		assertThat(petType2.getName()).isEqualTo("dog");
		PetType petType5 = EntityUtils.getById(petTypes, PetType.class, 5);
		assertThat(petType5.getName()).isEqualTo("bird");
	}
	
	@Test
	@Transactional
	public void shouldInsertTreatmentIntoDatabaseAndGenerateId() {
		Collection<Treatment> treatments = this.treatmentService.findAll();
		int found = treatments.size();
		
		Treatment treatment = new Treatment();
		treatment.setPrice(21.4);
		treatment.setType("Tipo del tratamiento");
		treatment.setDescription("Descripcion de prueba");
			Collection<PetType> petTypes = this.petService.findPetTypes();
        	treatment.setPetType(EntityUtils.getById(petTypes, PetType.class, 4));
        
        this.treatmentService.saveTreatment(treatment);
        
        treatments = this.treatmentService.findAll();
		assertThat(treatments.size()).isEqualTo(found + 1);
        // checks that id has been generated
        assertThat(treatment.getId()).isNotNull();
	}
	
	@Test
	public void addNullTreatment() {
		Treatment dummy = null;
		assertThrows(Exception.class, () -> this.treatmentService.saveTreatment(dummy));
	}
	
	@Test
	public void testInvalidTreatment() throws Exception {
		Treatment treatment = new Treatment();
		treatment.setId(-2);
		TreatmentRepository treatmentRepository = mock(TreatmentRepository.class);
		when(treatmentRepository.findById(-2)).thenThrow(new RuntimeException());
		TreatmentService treatmentService = new TreatmentService(treatmentRepository);
		assertThrows(RuntimeException.class, () -> treatmentService.findById(treatment.getId()));
	}
	
	@Test
	void shouldFindPetTypes() {
		PetType samplePetType = new PetType();
		samplePetType.setId(1);
		samplePetType.setName("Prueba");
		List<PetType> samplePets = new ArrayList<PetType>();
		samplePets.add(samplePetType);
		when(treatmentRepository.findPetTypes()).thenReturn(samplePets);
		TreatmentService treatmentService = new TreatmentService(treatmentRepository);
		Collection<PetType> petTypes = treatmentService.findPetTypes();
		assertThat(petTypes).hasSize(1);
		PetType pet = petTypes.iterator().next();
		assertThat(pet.getId()).isEqualTo(1);
		assertThat(pet.getName()).isEqualTo("Prueba");
	}
	
	/*@Test
	void shouldFailInsertTreatmentIntoDatabaseWithPriceNull() {
		Treatment treatment = new Treatment();
		treatment.setId(1);
		treatment.setDescription("Esta es la descripcion");
		treatment.setPrice(null); //The price cannot be null
		treatment.setType("Este es el tipo de tratamiento");
			PetType dog = new PetType();
			dog.setId(6);
			dog.setName("dog");
		treatment.setPetType(dog);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Treatment>> constraintViolations = validator.validate(treatment);
		assertThat(constraintViolations.size()).isEqualTo(1);
		
		ConstraintViolation<Treatment> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("price");
		assertThat(violation.getMessage()).isEqualTo("no puede ser null");
	}
	
	@Test
	void shouldFailInsertTreatmentIntoDatabaseWithTypeEmpty() {
		Treatment treatment = new Treatment();
		treatment.setId(1);
		treatment.setDescription("Esta es la descripcion");
		treatment.setPrice(40.34);
		treatment.setType(""); //The type cannot be empty
			PetType dog = new PetType();
			dog.setId(6);
			dog.setName("dog");
		treatment.setPetType(dog);
		
		Validator validator = createValidator();
		Set<ConstraintViolation<Treatment>> constraintViolations = validator.validate(treatment);
		assertThat(constraintViolations.size()).isEqualTo(1);
		
		ConstraintViolation<Treatment> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("type");
		assertThat(violation.getMessage()).isEqualTo("no puede estar vacío");
	}

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}*/

}
