package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "vaccination_schedule")
public class VaccinationSchedule extends BaseEntity {


	

	@JoinColumn(name = "vaccine_id")
	@OneToMany
	private List<Vaccine> vaccines;

	
	
	
	@Column(name = "dates")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@ElementCollection
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
