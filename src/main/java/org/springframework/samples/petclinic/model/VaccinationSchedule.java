package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;


import javax.persistence.Table;


import java.time.LocalDate;
import java.util.List;



@Entity
@Table(name = "vaccination_schedule")
public class VaccinationSchedule extends BaseEntity {


	

	@JoinColumn(name = "vaccine_id")
	@ManyToMany
	private List<Vaccine> vaccines;

	
	
	
	@Column(name = "dates")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private List<LocalDate> dates;

	public List<Vaccine> getVaccines() {
		return vaccines;
	}

	public void setVaccines(List<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}

	public List<LocalDate> getDates() {
		return dates;
	}

	public void setDates(List<LocalDate> dates) {
		this.dates = dates;
	}
	
	


	
}
