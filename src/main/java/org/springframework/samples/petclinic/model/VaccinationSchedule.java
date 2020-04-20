package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "vaccination_schedule")
public class VaccinationSchedule extends BaseEntity {


	

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "vaccination_schedule_vaccines", joinColumns = @JoinColumn(name = "vaccination_schedule_id"),
			inverseJoinColumns = @JoinColumn(name = "vaccine_id"))
	private Set<Vaccine> vaccines;

	
	
	
	@Column(name = "vaccination_schedule_dates")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@ElementCollection
	private List<LocalDate> dates;

	
	


	
}
