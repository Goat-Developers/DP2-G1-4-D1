package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.samples.petclinic.model.Announcement;

public interface AnnouncementRepository {
	
	Announcement findAnnouncementById(int id);
	
	void save(Announcement announcement);
	
	Collection<Announcement> findAll();

	Collection<Announcement> findByTag(String tag);

	Announcement findById(int id);

	Collection<Announcement> findOld();

}
