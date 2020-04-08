
package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "shifts")
public class Shift extends BaseEntity {

	@Column(name = "shift_date")        
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime shiftDate;

}
