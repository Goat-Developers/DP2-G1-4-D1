
package org.springframework.samples.petclinic.model;

import java.beans.Transient;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.javamoney.moneta.Money;


@Entity
@Table(name = "insurances_bases")
public class InsuranceBase extends BaseEntity {

	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "pet_type_id")
	private PetType petType;
	
	
	@OneToMany
	@JoinColumn(name = "vaccine_id")
	private List<Vaccine> vaccines;
	
	
	@OneToMany
	@JoinColumn(name = "treatment_id")
	private List<Treatment> treatments;
	
	

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

	
	
//	@Transient
//	public Money getPrice() {
//		Double amount;
//		String s = "EUR";
//		amount = vaccines.stream().mapToDouble(a-> a.getPrice().getNumber().doubleValue()).sum() +
//				treatments.stream().mapToDouble(b->b.getPrice().getNumber().doubleValue()).sum();
//		return Money.of(amount, s);
//	}
//	
}
