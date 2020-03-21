package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Announcements {
	
	private List<Announcement> announcements;

	@XmlElement
	public List<Announcement> getAnnouncementList() {
		if (announcements == null) {
			announcements = new ArrayList<>();
		}
		return announcements;
	}

}
