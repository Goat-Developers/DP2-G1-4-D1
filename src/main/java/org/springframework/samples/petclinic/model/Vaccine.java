package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
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
	
	@ManyToOne
    @NotNull
    @JoinColumn(name = "pet_type_id")
    private PetType petType;


	
}
