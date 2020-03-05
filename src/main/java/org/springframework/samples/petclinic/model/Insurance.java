
package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "insurance")
public class Insurance extends BaseEntity {


	@Column(name = "insurance_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate insurance_date;
	
	@Column(name = "price")
	@NotEmpty
	private Double price;
	
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "pet_type_id")
	private PetType petType;
	
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "vaccine_id")
	private Vaccine vaccine;
	
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "treatment_id")
	private Treatment treatment;
	


	public Insurance() {
		this.insurance_date = LocalDate.now();
	}


	public LocalDate getInsurance_date() {
		return this.insurance_date;
	}


	public void setInsurance_date(LocalDate insurance) {
		this.insurance_date = insurance;
	}
	
	
	public Double getPrice() {
		return this.price;
	}


	public void setPrice(Double price) {
		this.price= price;
	}
	
	public PetType getPetType() {
		return this.petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}
	
	
	public Vaccine getVaccine() {
		return this.vaccine;
	}

	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}
	
	public Treatment getTreatment() {
		return this.treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

}
