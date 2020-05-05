package org.springframework.samples.petclinic.web;



import org.springframework.samples.petclinic.model.Vet;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VetValidator implements Validator {

	private static final String REQUIRED = "required";
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Vet.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Vet vet = (Vet) target;
		 int maxShifts = vet.getMaxShifts();
		if(maxShifts >8) {
			errors.rejectValue("maxShifts", VetValidator.REQUIRED + " Los turnos exceden el máximo disponible", VetValidator.REQUIRED + " Los turnos exceden el máximo disponible");
		}
		if(maxShifts <0) {
			errors.rejectValue("maxShifts", VetValidator.REQUIRED + " Los turnos deben ser positivos", VetValidator.REQUIRED + " Los turnos deben ser positivos");
		}
	}

}
