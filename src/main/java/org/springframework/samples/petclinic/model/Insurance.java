
package org.springframework.samples.petclinic.model;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.javamoney.moneta.Money;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "insurances")
public class Insurance extends BaseEntity {


	@Column(name = "insurance_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate insuranceDate;
	
	@JoinColumn(name = "insurance_base_id")
	//@NotNull
	@ManyToOne
	private InsuranceBase insuranceBase;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "insurance_vaccines", joinColumns = @JoinColumn(name = "insurance_id"),
			inverseJoinColumns = @JoinColumn(name = "vaccine_id"))
	private Set<Vaccine> vaccines;
	
	@Column(name = "conditions")
	@NotBlank
	private String conditions;
	

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "insurance_treatments", joinColumns = @JoinColumn(name = "insurance_id"),
			inverseJoinColumns = @JoinColumn(name = "treatment_id"))
	private Set<Treatment> treatments;
	


	public Insurance() {
		this.insuranceDate = LocalDate.now();
	}

	//Propiedades derivadas - Derivated properties
//	@Transient
//	public Money getInsurancePrice() {
//		Double amount;
//		String s = "EUR";
//		amount = insuranceBase.getPrice().getNumber().doubleValue() +
//				vaccines.stream().mapToDouble(a-> a.getPrice().getNumber().doubleValue()).sum() +
//				treatments.stream().mapToDouble(b->b.getPrice().getNumber().doubleValue()).sum();
//		return Money.of(amount, s);
//	}
//	@Transient
//	public PetType getPetType() {
//		return insuranceBase.getPetType();
//	}

}
