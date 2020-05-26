package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 */
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

	
	@Column(name = "visit_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate date;

	@NotEmpty
	@Column(name = "description")
	private String description;

	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	public Visit() {
		this.date = LocalDate.now();
	}

	
	public LocalDate getDate() {
		return this.date;
	}

	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	public String getDescription() {
		return this.description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	
	public Pet getPet() {
		return this.pet;
	}

	
	public void setPet(Pet pet) {
		this.pet = pet;
	}

}
