package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VaccineValidator implements Validator {

	private static final String REQUIRED = "required";
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Vaccine.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Vaccine vaccine = (Vaccine) target;
		 LocalDate exxp = vaccine.getExpiration();
		if(exxp == null || exxp.isBefore(LocalDate.now())) {
			errors.rejectValue("expiration", VaccineValidator.REQUIRED + " la fecha de expiración debe ser futura", VaccineValidator.REQUIRED + " la fecha de expiración debe ser futura");
		}
		if(vaccine.getName()=="") {
			errors.rejectValue("name", VaccineValidator.REQUIRED + " el campo nombre no puede ser vacio", VaccineValidator.REQUIRED + "  el campo nombre no puede ser vacio");
		}
		if(vaccine.getInformation()=="") {
			errors.rejectValue("information", VaccineValidator.REQUIRED + " el campo informacion no puede ser vacio", VaccineValidator.REQUIRED + " el campo informacion no puede ser vacio");
		}
		if(vaccine.getProvider()=="") {
			errors.rejectValue("provider", VaccineValidator.REQUIRED + " el campo proveedor no puede ser vacio", VaccineValidator.REQUIRED + " el campo proveedor no puede ser vacio");
		}
		if(vaccine.getPrice()==null) {
			errors.rejectValue("price", VaccineValidator.REQUIRED + " el campo precio no puede ser nulo", VaccineValidator.REQUIRED + " el campo precio no puede ser nulo");
		}
		if(vaccine.getStock()==null) {
			errors.rejectValue("stock", VaccineValidator.REQUIRED + " el campo stock no puede ser nulo", VaccineValidator.REQUIRED + " el campo stock no puede ser nulo");
		}
		if(vaccine.getPetType()==null) {
			errors.rejectValue("petType", VaccineValidator.REQUIRED + " debe elegir un tipo de mascota", VaccineValidator.REQUIRED + " debe elegir un tipo de mascota");
		}
		
		
	}

}
