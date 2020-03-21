package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InsurancesBases {
	
	private List<InsuranceBase> insurances;

	@XmlElement
	public List<InsuranceBase> getInsuranceBaseList() {
		if (insurances == null) {
			insurances = new ArrayList<>();
		}
		return insurances;
	}

}
