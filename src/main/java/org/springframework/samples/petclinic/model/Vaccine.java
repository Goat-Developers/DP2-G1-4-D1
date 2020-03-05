/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 */
@Entity
@Table(name = "vaccine")
public class Vaccine extends BaseEntity {

	/**
	 * Holds value of property date.
	*/
	@NotEmpty
	@Column(name = "name")        
	private String name;

	/**
	 * Holds value of property description.
	 */
	@NotEmpty
	@Column(name = "information")
	private String information;

	/**
	 * Holds value of property pet.
	 */
	
	@NotNull
	@Column(name ="price")
	private Double price;
	
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


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
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
