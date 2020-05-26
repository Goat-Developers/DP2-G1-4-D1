package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;


import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.web.VaccineValidator;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

/**
 * @author Michael Isvy Simple test to make sure that Bean Validation is working (useful
 * when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
class VaccineValidatorTests {

	private VaccineValidator createValidator() {
		VaccineValidator vaccineValidator = new VaccineValidator();
		return vaccineValidator;
	}

	@Test
	void shouldNotValidateWhenNameEmpty() {
		PetType human = new PetType();
		human.setId(100);
		human.setName("humano");
		Vaccine vaccine = new Vaccine();
		vaccine.setName("");
		vaccine.setExpiration(LocalDate.of(2021, 7, 3));
		vaccine.setInformation("información");
		vaccine.setPrice(25.0);
		vaccine.setProvider("China");
		vaccine.setSideEffects("Dolorosa");
		vaccine.setStock(20);
		vaccine.setPetType(human);
		VaccineValidator validator = createValidator();
		Errors error = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine,error);
		assertThat(error.getErrorCount()).isEqualTo(1);
		assertThat(error.hasFieldErrors("name"));
		assertThat(error.getFieldError("name").getDefaultMessage()).isEqualTo("required " +" el campo nombre no puede ser vacio");
	}
	
	@Test
	void shouldNotValidateWhenExpirationNotFutureOrEmpty() {
		PetType human = new PetType();
		human.setId(100);
		human.setName("humano");
		Vaccine vaccine = new Vaccine();
		vaccine.setName("Coronavirus");
		vaccine.setExpiration(LocalDate.of(2019, 7, 3));
		vaccine.setInformation("información");
		vaccine.setPrice(25.0);
		vaccine.setProvider("China");
		vaccine.setSideEffects("Dolorosa");
		vaccine.setStock(20);
		vaccine.setPetType(human);
		VaccineValidator validator = createValidator();
		Errors error = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine,error);
		assertThat(error.getErrorCount()).isEqualTo(1);
		assertThat(error.hasFieldErrors("expiration"));
		assertThat(error.getFieldError("expiration").getDefaultMessage()).isEqualTo("required" +" la fecha de expiración debe ser futura");
		vaccine.setExpiration(null);
		assertThat(error.getFieldError("expiration").getDefaultMessage()).isEqualTo("required" +" la fecha de expiración debe ser futura");
	}
	
	@Test
	void shouldNotValidateWhenInformationEmpty() {
		PetType human = new PetType();
		human.setId(100);
		human.setName("humano");
		Vaccine vaccine = new Vaccine();
		vaccine.setName("Coronavirus");
		vaccine.setExpiration(LocalDate.of(2021, 7, 3));
		vaccine.setInformation("");
		vaccine.setPrice(25.0);
		vaccine.setProvider("China");
		vaccine.setSideEffects("Dolorosa");
		vaccine.setStock(20);
		vaccine.setPetType(human);
		VaccineValidator validator = createValidator();
		Errors error = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine,error);
		assertThat(error.getErrorCount()).isEqualTo(1);
		assertThat(error.hasFieldErrors("information"));
		assertThat(error.getFieldError("information").getDefaultMessage()).isEqualTo("required" +" el campo informacion no puede ser vacio");
	}
	
	@Test
	void shouldNotValidateWhenPriceEmpty() {
		PetType human = new PetType();
		human.setId(100);
		human.setName("humano");
		Vaccine vaccine = new Vaccine();
		vaccine.setName("Coronavirus");
		vaccine.setExpiration(LocalDate.of(2021, 7, 3));
		vaccine.setInformation("información");
		vaccine.setPrice(null);
		vaccine.setProvider("China");
		vaccine.setSideEffects("Dolorosa");
		vaccine.setStock(20);
		vaccine.setPetType(human);
		VaccineValidator validator = createValidator();
		Errors error = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine,error);
		assertThat(error.getErrorCount()).isEqualTo(1);
		assertThat(error.hasFieldErrors("price"));
		assertThat(error.getFieldError("price").getDefaultMessage()).isEqualTo("required" +" el campo precio no puede ser nulo");
	}
	
	@Test
	void shouldNotValidateWhenProviderEmpty() {
		PetType human = new PetType();
		human.setId(100);
		human.setName("humano");
		Vaccine vaccine = new Vaccine();
		vaccine.setName("Coronavirus");
		vaccine.setExpiration(LocalDate.of(2021, 7, 3));
		vaccine.setInformation("información");
		vaccine.setPrice(25.0);
		vaccine.setProvider("");
		vaccine.setSideEffects("Dolorosa");
		vaccine.setStock(20);
		vaccine.setPetType(human);
		VaccineValidator validator = createValidator();
		Errors error = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine,error);
		assertThat(error.getErrorCount()).isEqualTo(1);
		assertThat(error.hasFieldErrors("provider"));
		assertThat(error.getFieldError("provider").getDefaultMessage()).isEqualTo("required" +" el campo proveedor no puede ser vacio");
	}
	
	@Test
	void shouldNotValidateWhenSideEffectsEmpty() {
		PetType human = new PetType();
		human.setId(100);
		human.setName("humano");
		Vaccine vaccine = new Vaccine();
		vaccine.setName("Coronavirus");
		vaccine.setExpiration(LocalDate.of(2021, 7, 3));
		vaccine.setInformation("información");
		vaccine.setPrice(25.0);
		vaccine.setProvider("China");
		vaccine.setSideEffects("");
		vaccine.setStock(20);
		vaccine.setPetType(human);
		VaccineValidator validator = createValidator();
		Errors error = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine,error);
		assertThat(error.getErrorCount()).isEqualTo(1);
		assertThat(error.hasFieldErrors("sideEffects"));
		assertThat(error.getFieldError("sideEffects").getDefaultMessage()).isEqualTo("required " +"el campo los efectos secundarios no puede ser vacio");
	}
	
	@Test
	void shouldNotValidateWhenStockEmpty() {
		PetType human = new PetType();
		human.setId(100);
		human.setName("humano");
		Vaccine vaccine = new Vaccine();
		vaccine.setName("Coronavirus");
		vaccine.setExpiration(LocalDate.of(2021, 7, 3));
		vaccine.setInformation("información");
		vaccine.setPrice(25.0);
		vaccine.setProvider("China");
		vaccine.setSideEffects("Dolorosa");
		vaccine.setStock(null);
		vaccine.setPetType(human);
		VaccineValidator validator = createValidator();
		Errors error = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine,error);
		assertThat(error.getErrorCount()).isEqualTo(1);
		assertThat(error.hasFieldErrors("stock"));
		assertThat(error.getFieldError("stock").getDefaultMessage()).isEqualTo("required" +" el campo stock no puede ser nulo");
	}
	
	@Test
	void shouldNotValidateWhenPetTypeEmpty() {
		Vaccine vaccine = new Vaccine();
		vaccine.setName("Coronavirus");
		vaccine.setExpiration(LocalDate.of(2021, 7, 3));
		vaccine.setInformation("información");
		vaccine.setPrice(25.0);
		vaccine.setProvider("China");
		vaccine.setSideEffects("Dolorosa");
		vaccine.setStock(20);
		vaccine.setPetType(null);
		VaccineValidator validator = createValidator();
		Errors error = new BeanPropertyBindingResult(vaccine, "vaccine");
		validator.validate(vaccine,error);
		assertThat(error.getErrorCount()).isEqualTo(1);
		assertThat(error.hasFieldErrors("petType"));
		assertThat(error.getFieldError("petType").getDefaultMessage()).isEqualTo("required" +" debe elegir un tipo de mascota");
	}

}
