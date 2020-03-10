
package org.springframework.samples.petclinic.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.javamoney.moneta.Money;


@Entity
@Table(name = "treatments")
public class Treatment extends BaseEntity {


	@NotEmpty
	@Column(name = "type")
	private String type;
	
	@NotNull
	@Column(name = "price")
	private Money price;
	
	@NotEmpty
	@Column(name = "description")
	private String description;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Money getPrice() {
		return price;
	}

	public void setPrice(Money price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	



}
