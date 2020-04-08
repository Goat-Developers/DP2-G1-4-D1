
package org.springframework.samples.petclinic.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "vet_schedule")
public class VetSchedule extends BaseEntity {
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "schedule_appointments", joinColumns = @JoinColumn(name = "vet_schedule_id"),
			inverseJoinColumns = @JoinColumn(name = "appointment_id"))
	private Set<Appointment> appointments;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "schedule_shifts", joinColumns = @JoinColumn(name = "vet_schedule_id"),
			inverseJoinColumns = @JoinColumn(name = "shift_id"))
	private Set<Shift> shifts;

}
