
package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;



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
	@Column(name = "reason")
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
	
	@Column(name="attended")
	private boolean attended;

	@Column(name="observations")
	private String observations;
	
}
