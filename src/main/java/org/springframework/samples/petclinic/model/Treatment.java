
package org.springframework.samples.petclinic.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "treatments")
public class Treatment extends BaseEntity {


	@NotEmpty
	@Column(name = "type")
	private String type;
	
	@NotNull
	@Column(name = "price")
	private Double price;
	
	@NotEmpty
	@Column(name = "description")
	private String description;


	



}
