
package org.springframework.samples.petclinic.model;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "treatment")
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	



}
