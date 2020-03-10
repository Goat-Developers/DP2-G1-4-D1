package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.javamoney.moneta.Money;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "vaccines")
public class Vaccine extends BaseEntity {

	
	@NotEmpty
	@Column(name = "name")        
	private String name;

	
	@NotEmpty
	@Column(name = "information")
	private String information;

	
	@NotNull
	@Column(name ="price")
	private Money price;
	
	@NotEmpty
	@Column(name="provider")
	private String provider;
	
	
	@Column(name = "expiration")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate expiration;
	
	@NotNull
	@Column(name="stock")
	private Integer stock;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getInformation() {
		return information;
	}


	public void setInformation(String information) {
		this.information = information;
	}


	public Money getPrice() {
		return price;
	}


	public void setPrice(Money price) {
		this.price = price;
	}


	public String getProvider() {
		return provider;
	}


	public void setProvider(String provider) {
		this.provider = provider;
	}


	public LocalDate getExpiration() {
		return expiration;
	}


	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}
	public Integer getStock() {
		return stock;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * Creates a new instance of Visit for the current date
	 */
	
}
