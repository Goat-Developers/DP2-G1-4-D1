package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Announcement;
import org.springframework.samples.petclinic.model.Announcements;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.service.AnnouncementService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AnnouncementController {
	
	private final AnnouncementService annService;
	
	private final VetService vetService;
	
	
	
	private static final String URL_ANNOUNCEMENTS ="announcements/announcementList"; 

	@Autowired
	public AnnouncementController(AnnouncementService annService,VetService vetService) {
		this.annService = annService;
		this.vetService = vetService;
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
	public String showAnnouncementListFiltered(Announcement ann, Map<String,Object> model) {
		Announcements announcements = new Announcements();
		if(ann.getTag()=="") {
			announcements.getAnnouncementList().addAll(annService.findAnnouncements());
		}else {
			announcements.getAnnouncementList().addAll(annService.findAnnouncementsByTag(ann.getTag().toLowerCase()));
			if (announcements.getAnnouncementList().isEmpty()) {
				model.put("vacio", true);
			}
		}
		
		model.put("announcements",announcements);
		model.put("announcement",ann);
		return URL_ANNOUNCEMENTS;
	}
	@GetMapping("/announcements/{announcementId}")
	public String showAnnouncementDetail(@PathVariable("announcementId")  int announcementId, Map<String,Object>  model ) {
		Announcement a = annService.findAnnouncementById(announcementId);
		model.put("announcement",a);
		return "announcements/announcementDetails";
	}
	@GetMapping("/announcements/old")
	public String showOldAnnouncementList(Map<String,Object> model,Announcement ann ) {
		Announcements announcements = new Announcements();
		announcements.getAnnouncementList().addAll(annService.findOldAnnouncements());
		model.put("announcements",announcements);
		model.put("announcement",ann);
		return URL_ANNOUNCEMENTS;
	}
	
	@GetMapping("/announcement/new")
	public String initAnnouncementCreationForm(Map<String,Object> model) {
		Announcement announcement = new Announcement();
		model.put("announcement",announcement);
		return "announcements/createOrUpdateAnnouncementForm";
	}
	@PostMapping(value = "/announcement/new")
	public String processCreationForm(@Valid Announcement ann, BindingResult result) {
		if (result.hasErrors()) {
			return "announcements/createOrUpdateAnnouncementForm";
		}
		else {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentUsername = authentication.getName();
			Vet vet = this.annService.findVetByUser(currentUsername);
			ann.setVet(vet);
			ann.setTag(ann.getTag().toLowerCase().replaceAll("[\\s+,,]","").trim());
			this.annService.saveAnnouncement(ann);
			this.vetService.saveVet(vet);
			
			return "redirect:/announcements";
		}
	}
	@PostMapping(value="/announcements/like")
	public String likeIncrement(@ModelAttribute("id") int id) {
		
		Announcement ann = annService.findAnnouncementById(id);
		ann.setLikes(ann.getLikes()+1);
		annService.saveAnnouncement(ann);
		return "redirect:/announcements";
		
	}
	

}
