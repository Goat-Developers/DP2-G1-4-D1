package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Component;

@Component
public class PetFormatter implements Formatter<Pet>{

	@Autowired
	private PetService petService;
	
	@Override
	public String print(Pet object, Locale locale) {
		
		return object.getName();
	}

	@Override
	public Pet parse(String text, Locale locale) throws ParseException {
		Collection<Pet> pets = this.petService.findPets();
		for (Pet type : pets) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
