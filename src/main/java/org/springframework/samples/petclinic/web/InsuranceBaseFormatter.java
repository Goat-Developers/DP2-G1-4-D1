package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.stereotype.Component;

@Component
public class InsuranceBaseFormatter implements Formatter<InsuranceBase>{

	@Autowired
	private InsuranceBaseService insuranceBaseService;
	
	@Override
	public String print(InsuranceBase object, Locale locale) {
		
		return object.getName();
	}

	@Override
	public InsuranceBase parse(String text, Locale locale) throws ParseException {
		List<InsuranceBase> insuranceBase = this.insuranceBaseService.findInsurancesBases();
		for (InsuranceBase type : insuranceBase) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
