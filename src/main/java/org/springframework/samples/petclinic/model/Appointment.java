
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "appointments")
public class Appointment extends BaseEntity {

	@Column(name = "appointment_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate appointmentDate;
	
	@Column(name = "appointment_time")
	@DateTimeFormat( pattern = "HH:mm:ss")
	private LocalTime appointmentTime;

	@NotEmpty
	@Column(name ="appointement_reason")
	private String reason;
	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	@ManyToOne
	@JoinColumn(name = "treatment_id")
	private Treatment treatment;
	
	@ManyToOne
	@JoinColumn(name = "vaccine_id")
	private Vaccine vaccine;
	
	@Column(name="appointment_attended")
	private boolean attended;

	@Column(name="appointment_observations")
	private String observations;
	
	@Column(name="appointment_billing")
	private Double billing;
	
	
	public Appointment() {
		this.observations="";
		this.attended = false;
		this.billing = 0.;
	}

}
