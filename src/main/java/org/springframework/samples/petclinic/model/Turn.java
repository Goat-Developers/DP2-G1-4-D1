
package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;



@Entity
@Table(name = "turn")
public class Turn extends BaseEntity {


	@Column(name = "turn_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate turn_date;


	@NotEmpty
	@Column(name = "picked")
	private Boolean picked ;
	
	

	public Turn() {
		this.turn_date = LocalDate.now();
	}


	public LocalDate getTurn_date() {
		return this.turn_date;
	}


	public void setTurn_date(LocalDate turn_date) {
		this.turn_date = turn_date;
	}


	public Boolean getPicked() {
		return this.picked;
	}

	
	public void setPicked(Boolean picked) {
		this.picked = picked;
	}
	

	

}
