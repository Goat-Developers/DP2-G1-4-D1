
package org.springframework.samples.petclinic.model;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.javamoney.moneta.Money;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "insurances")
public class Insurance extends BaseEntity {


	@Column(name = "insurance_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate insuranceDate;
	
	@JoinColumn(name = "insurance_base_id")
	@NotNull
	@ManyToOne
	private InsuranceBase insuranceBase;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="insurance")
	@JoinColumn(name = "vaccine_id")
	private List<Vaccine> vaccines;
	
	@Column(name = "conditions")
	@NotBlank
	private String conditions;
	

	@OneToMany
	@JoinColumn(name = "treatment_id")
	private List<Treatment> treatments;
	


	public Insurance() {
		this.insuranceDate = LocalDate.now();
	}


	public LocalDate getInsurance_date() {
		return this.insuranceDate;
	}
	
	public InsuranceBase getInsuranceBase() {
		return insuranceBase;
	}


	public void setInsuranceBase(InsuranceBase insuranceBase) {
		this.insuranceBase = insuranceBase;
	}

	public void setInsurance_date(LocalDate insurance) {
		this.insuranceDate = insurance;
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
	
	
	
	public String getConditions() {
		return conditions;
	}


	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	//Propiedades derivadas - Derivated properties
	@Transient
	public Money getInsurancePrice() {
		Double amount;
		String s = "EUR";
		amount = insuranceBase.getPrice().getNumber().doubleValue() +
				vaccines.stream().mapToDouble(a-> a.getPrice().getNumber().doubleValue()).sum() +
				treatments.stream().mapToDouble(b->b.getPrice().getNumber().doubleValue()).sum();
		return Money.of(amount, s);
	}
	@Transient
	public PetType getPetType() {
		return insuranceBase.getPetType();
	}

}
