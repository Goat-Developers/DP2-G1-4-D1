package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.repository.TreatmentRepository;


@ExtendWith(MockitoExtension.class)
public class TreatmentServiceMockedTests {
	
	@Mock
	private TreatmentRepository treatmentRepository;
	
	protected TreatmentService treatmentService;
	
	@BeforeEach
	void setup() {
		treatmentService = new TreatmentService(treatmentRepository);
	}
	
	@Test
	void shouldFindTreatments() {
		Treatment sampleTreatment = new Treatment();
		sampleTreatment.setDescription("Esta es la descripcion");
		sampleTreatment.setType("Este es el tipo");
		//sampleTreatment.setPrice(20.4);
		List<Treatment> sampleTreatments = new ArrayList<Treatment>();
		sampleTreatments.add(sampleTreatment);
		when(treatmentRepository.findAll()).thenReturn(sampleTreatments);
		
		Collection<Treatment> treatments = this.treatmentService.findAll();
		
		assertThat(treatments).hasSize(1);
		Treatment treatment = treatments.iterator().next();
		assertThat(treatment.getType()).isEqualTo("Este es el tipo");
		assertThat(treatment.getPrice()).isNull();
		assertThat(treatment.getDescription()).isNotEmpty();
	}

}
