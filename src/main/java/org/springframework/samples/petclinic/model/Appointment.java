
package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "appointment")
public class Appointment extends BaseEntity {


	@Column(name = "appointment_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate appointment_date;


	@NotEmpty
	@Column(name = "reason")
	private String reason;
	
	
	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
//	@ManyToOne
//	@JoinColumn(name = "treatment_id")
//	private Treatment treatment;
//	



	public Appointment() {
		this.appointment_date = LocalDate.now();
	}


	public LocalDate getAppointment_date() {
		return this.appointment_date;
	}


	public void setAppointment_date(LocalDate appointment_date) {
		this.appointment_date = appointment_date;
	}


	public String getReason() {
		return this.reason;
	}

	
	public void setReason(String reason) {
		this.reason = reason;
	}
	


	
	public Pet getPet() {
		return this.pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
//	public Treatment getTreatment() {
//		return this.treatment;
//	}
//
//	public void setTreatment(Treatment treatment) {
//		this.treatment = treatment;
//	}

}
