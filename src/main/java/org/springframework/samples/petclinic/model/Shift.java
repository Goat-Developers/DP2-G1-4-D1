
package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;



@Entity
@Table(name = "shifts")
public class Shift extends BaseEntity {


	@Column(name = "shift_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate shiftDate;


	@NotEmpty
	@Column(name = "picked")
	private Boolean picked ;
	
	

	public Shift() {
		this.shiftDate = LocalDate.now();
	}


	public LocalDate getTurn_date() {
		return this.shiftDate;
	}


	public void setTurn_date(LocalDate shiftDate) {
		this.shiftDate = shiftDate;
	}


	public Boolean getPicked() {
		return this.picked;
	}

	
	public void setPicked(Boolean picked) {
		this.picked = picked;
	}
	

	

}
