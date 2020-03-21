package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Announcement;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.AnnouncementRepository;

public interface SpringDataAnnouncementRepository extends AnnouncementRepository, Repository<Announcement, Integer>{

	
	@Override
	@Query("SELECT announcement FROM Announcement announcement WHERE announcement.tag =?1 ")
	Collection<Announcement> findByTag(@Param("tag") String tag);
	
	@Override
	@Query("SELECT announcement FROM Announcement announcement WHERE announcement.id =?1")
	Announcement findById(@Param("id") int id);
	
	@Override
	@Query("SELECT announcement FROM Announcement announcement WHERE announcement.date >= current_date - 7 ORDER BY announcement.likes DESC")
	Collection<Announcement> findAll();
	
	@Override
	@Query("SELECT announcement FROM Announcement announcement WHERE announcement.date < current_date - 7 ORDER BY announcement.likes DESC")
	Collection<Announcement> findOld();
	
	@Query("SELECT vet FROM Vet vet WHERE vet.user.username=?1")
	Vet findVetByUserId(@Param("user") String user);
	
	
}
