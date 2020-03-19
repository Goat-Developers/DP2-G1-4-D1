package org.springframework.samples.petclinic.model;

import java.beans.Transient;
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
	
	@Column(name = "side_effects")
	private String sideEffects;

	@Transient
	public String getExpirationSoon() {
		String res = "No expira pronto";
		LocalDate ahora= LocalDate.now();
        LocalDate umbral = expiration.minusDays(7);
        if (ahora.equals(umbral) || ahora.isAfter(expiration) || ahora.isAfter(umbral)) {
            res = "Expira pronto";	        
        }
        	return res;
	}
	@Transient
	public Boolean getExpirated() {
		Boolean res = false;
		LocalDate ahora= LocalDate.now();
            if (ahora.isAfter(expiration)) {
            res = true;	        
        }
        	return res;
	}
}
