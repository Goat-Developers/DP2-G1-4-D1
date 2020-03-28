package org.springframework.samples.petclinic.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Announcement;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnnouncementService {

	private AnnouncementRepository annrepository;

	@Autowired
	public AnnouncementService(AnnouncementRepository annrepository) {
		this.annrepository = annrepository;
	}
	@Transactional(readOnly = true)	
	public Collection<Announcement> findAnnouncements() throws DataAccessException {
		return annrepository.findAll();
	}
	public Collection<Announcement> findAnnouncementsByTag(String tag)  throws DataAccessException{
		return annrepository.findByTag(tag);
	}
	public Announcement findAnnouncementById(int id) {
		
		return annrepository.findById(id);
	}
	public Collection<? extends Announcement> findOldAnnouncements()  throws DataAccessException{
		
		return annrepository.findOld();
	}
	public void saveAnnouncement(@Valid Announcement announcement)  throws DataAccessException{
		
		 annrepository.save(announcement);
		
	}
	public Vet findVetByUser(String user){
		
		return annrepository.findVetByUserId(user);
	}	

	
}
