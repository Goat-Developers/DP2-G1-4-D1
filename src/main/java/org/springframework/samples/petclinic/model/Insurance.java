
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.javamoney.moneta.Money;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "insurances")
public class Insurance extends BaseEntity {


	@Column(name = "insurance_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate insuranceDate;
	
	@Column(name = "price")
	@NotEmpty
	private Money price;
	
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "pet_type_id")
	private PetType petType;
	
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "vaccine_id")
	private List<Vaccine> vaccines;
	
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "treatment_id")
	private List<Treatment> treatments;
	


	public Insurance() {
		this.insuranceDate = LocalDate.now();
	}


	public LocalDate getInsurance_date() {
		return this.insuranceDate;
	}


	public void setInsurance_date(LocalDate insurance) {
		this.insuranceDate = insurance;
	}
	
	
	public Money getPrice() {
		return this.price;
	}


	public void setPrice(Money price) {
		this.price= price;
	}
	
	public PetType getPetType() {
		return this.petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}
	
	
	public List<Vaccine> getVaccines() {
		return this.vaccines;
	}

	public void setVaccine(List<Vaccine> vaccine) {
		this.vaccines = vaccine;
	}
	
	public List<Treatment> getTreatments() {
		return this.treatments;
	}

	public void setTreatment(List<Treatment> treatment) {
		this.treatments = treatment;
	}

}
