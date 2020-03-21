package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.stereotype.Component;

@Component
public class VaccineFormatter implements Formatter<Vaccine>{

	
	@Autowired
	private InsuranceService insuranceService;
	
	@Override
	public String print(Vaccine object, Locale locale) {
		
		return object.getName();
	}

	@Override
	public Vaccine parse(String text, Locale locale) throws ParseException {
		Collection<Vaccine> vaccines = this.insuranceService.findVaccines();
		for (Vaccine type : vaccines) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
