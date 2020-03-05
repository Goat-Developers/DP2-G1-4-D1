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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 */
@Entity
@Table(name = "vaccination_schedule")
public class VaccinationSchedule extends BaseEntity {

	/**
	 * Holds value of property date.
	*/

	@JoinColumn(name = "vaccine_id")
	@ManyToMany
	private List<Vaccine> vaccines;

	/**
	 * Holds value of property pet.
	 */
	
	
	@Column(name = "dates")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private List<LocalDate> dates;

	public List<Vaccine> getVaccines() {
		return vaccines;
	}

	public void setVaccines(List<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}

	public List<LocalDate> getDates() {
		return dates;
	}

	public void setDates(List<LocalDate> dates) {
		this.dates = dates;
	}
	
	


	
	/**
	 * Creates a new instance of Visit for the current date
	 */
	
}
