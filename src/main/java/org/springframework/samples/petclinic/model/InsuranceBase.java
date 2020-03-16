
package org.springframework.samples.petclinic.model;

import java.util.List;
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
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "insurances_bases")
public class InsuranceBase extends BaseEntity {

	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "pet_type_id")
	private PetType petType;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "insurance_base_vaccines", joinColumns = @JoinColumn(name = "insurance_base_id"),
			inverseJoinColumns = @JoinColumn(name = "vaccine_id"))
	private Set<Vaccine> vaccines;
	
	@NotBlank
	@Column(name = "conditions")
	private String conditions;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "insurance_base_treatments", joinColumns = @JoinColumn(name = "insurance_base_id"),
			inverseJoinColumns = @JoinColumn(name = "treatment_id"))
	private Set<Treatment> treatments;
	
	
	
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
