
package org.springframework.samples.petclinic.model;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.HashSet;
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
import javax.validation.constraints.NotNull;

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
	@NotNull
	@ManyToOne
	private InsuranceBase insuranceBase;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "insurance_vaccines", joinColumns = @JoinColumn(name = "insurance_id"),
			inverseJoinColumns = @JoinColumn(name = "vaccine_id"))
	private Set<Vaccine> vaccines;

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "insurance_treatments", joinColumns = @JoinColumn(name = "insurance_id"),
			inverseJoinColumns = @JoinColumn(name = "treatment_id"))
	private Set<Treatment> treatments;
	

	protected Set<Treatment> getTreatmentsInternal() {
		if (this.treatments == null) {
			this.treatments = new HashSet<>();
		}
		return this.treatments;
	}
	
	protected void setTreatmentsInternal(Set<Treatment> treatments) {
		this.treatments = treatments;
	}
	public Set<Treatment> getTreatments(){
		return this.getTreatmentsInternal();
	}
	public void setTreatments(Set<Treatment> treatments) {
		this.setTreatmentsInternal(treatments);
	}
	
	public Insurance() {
		this.insuranceDate = LocalDate.now();
		
	}

	//Propiedades derivadas - Derivated properties
	

	@Transient
	public Double getInsurancePrice() {
		Double res = 0.;
		res += insuranceBase.getPrice();
		res += vaccines.stream().filter(x-> ! insuranceBase.getVaccines().contains(x)).mapToDouble(v -> v.getPrice()).sum();
		res += treatments.stream().filter(x-> ! insuranceBase.getTreatments().contains(x)).mapToDouble(t -> t.getPrice()).sum();
		
		
		return res;
	}
	
	
}
