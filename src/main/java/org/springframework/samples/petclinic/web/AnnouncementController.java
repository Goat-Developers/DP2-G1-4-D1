package org.springframework.samples.petclinic.web;

import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Announcement;
import org.springframework.samples.petclinic.model.Announcements;
import org.springframework.samples.petclinic.service.AnnouncementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
public class AnnouncementController {
	
	private final AnnouncementService annService;
	
	private static final String URL_ANNOUNCEMENTS ="announcements/announcementList"; 

	@Autowired
	public AnnouncementController(AnnouncementService annService) {
		this.annService = annService;
	}
	
	

	@GetMapping("/announcements")
	public String showAnnouncementList(Map<String,Object> model) {
		
		Announcements announcements = new Announcements();
		announcements.getAnnouncementList().addAll(annService.findAnnouncements());
		Announcement announcement = new Announcement();
		model.put("announcements",announcements);
		model.put("announcement",announcement);
		return URL_ANNOUNCEMENTS;
		
	}
	@GetMapping("/announcements/tag")
	public String showAnnouncementListFiltered(Announcement announcement, Map<String,Object> model) {
		Announcements announcements = new Announcements();
		if(announcement.getTag()=="") {
			announcements.getAnnouncementList().addAll(annService.findAnnouncements());
		}else {
			announcements.getAnnouncementList().addAll(annService.findAnnouncementsByTag(announcement.getTag()));
		}
		model.put("announcements",announcements);
		model.put("announcement",announcement);
		return URL_ANNOUNCEMENTS;
	}
	@GetMapping("/announcements/{announcementId}")
	public String ShowAnnouncementDetail(@PathVariable("announcementId")  int announcementId, Map<String,Object>  model ) {
		Announcement a = annService.findAnnouncementById(announcementId);
		model.put("announcement",a);
		return "announcements/announcementDetails";
	}
	@GetMapping("/announcements/old")
	public String ShowOldAnnouncementList(Map<String,Object> model,Announcement announcement ) {
		Announcements announcements = new Announcements();
		announcements.getAnnouncementList().addAll(annService.findOldAnnouncements());
		model.put("announcements",announcements);
		model.put("announcement",announcement);
		return URL_ANNOUNCEMENTS;
	}

}
