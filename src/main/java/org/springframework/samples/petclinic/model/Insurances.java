package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Insurances {
	
	private List<Insurance> insurances;

	@XmlElement
	public List<Insurance> getInsuranceList() {
		if (insurances == null) {
			insurances = new ArrayList<>();
		}
		return insurances;
	}

}
