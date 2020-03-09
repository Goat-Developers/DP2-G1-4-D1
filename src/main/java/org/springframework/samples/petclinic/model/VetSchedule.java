
package org.springframework.samples.petclinic.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "vet_schedule")
public class VetSchedule extends BaseEntity {
	
	@OneToMany
	@JoinColumn(name = "appointment_id")
	private List<Appointment> appointments;
	
	
	@OneToMany
	@JoinColumn(name = "shift_id")
	private List<Shift> shifts;
	
	
	


}
