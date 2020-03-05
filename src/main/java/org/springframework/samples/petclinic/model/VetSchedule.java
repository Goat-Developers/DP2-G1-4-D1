
package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "vetSchedule")
public class VetSchedule extends BaseEntity {
	
	
	@OneToMany
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;
	
	
	
	public Appointment getAppointment() {
		return this.appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	


}
