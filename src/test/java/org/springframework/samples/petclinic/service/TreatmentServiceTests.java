package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class TreatmentServiceTests {
	
	@Autowired
	protected TreatmentService treatmentService;
	
	@Autowired
	protected PetService petService;
	
	@Test
	void shouldFindSingleTreatmentById() {
		Treatment treatment = this.treatmentService.findById(1);
		assertThat(treatment.getDescription()).isNotEmpty();
		assertThat(treatment.getDescription()).isEqualTo("Limpieza de dientes");
		assertThat(treatment.getPrice()).isNotNull();
		assertThat(treatment.getPrice()).isEqualTo(65.7);
		assertThat(treatment.getPrice()).isNotEqualTo(40.2);
		assertThat(treatment.getType()).isNotEqualTo("Pelo");
		assertThat(treatment.getPetType()).isNotNull();
		assertThat(treatment.getPetType().getName()).isEqualTo("Snake");
		assertThat(treatment.getPetType().getId()).isEqualTo(4);
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
		assertThat(petType2.getName()).isEqualTo("Dog");
		PetType petType5 = EntityUtils.getById(petTypes, PetType.class, 5);
		assertThat(petType5.getName()).isEqualTo("Bird");
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

}
