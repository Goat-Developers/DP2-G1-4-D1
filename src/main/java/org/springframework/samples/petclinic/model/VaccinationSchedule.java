package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "vaccination_schedule")
public class VaccinationSchedule extends BaseEntity {


	@Column(name = "vaccination_schedule_vaccines")        
	@ElementCollection
	private List<Vaccine> vaccines;

	
	
	@Column(name = "vaccination_schedule_dates")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@ElementCollection
	private List<LocalDate> dates;

	
	


	
}
