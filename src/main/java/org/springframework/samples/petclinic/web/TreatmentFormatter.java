package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.service.InsuranceService;
import org.springframework.stereotype.Component;

@Component
public class TreatmentFormatter implements Formatter<Treatment>{

	@Autowired
	private InsuranceService insuranceService;
	
	@Override
	public String print(Treatment object, Locale locale) {
		
		return object.getDescription();
	}

	@Override
	public Treatment parse(String text, Locale locale) throws ParseException {
		Collection<Treatment> treatments = this.insuranceService.findTreatments();
		for (Treatment type : treatments) {
			if (type.getDescription().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
