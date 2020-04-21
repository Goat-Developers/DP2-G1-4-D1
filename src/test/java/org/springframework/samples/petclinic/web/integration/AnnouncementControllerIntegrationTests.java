package org.springframework.samples.petclinic.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.Announcement;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.service.AnnouncementService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.samples.petclinic.web.AnnouncementController;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnnouncementControllerIntegrationTests {

	private static final int VET_TEST_ID = 1;
	private static final int ANNOUNCEMENT_TEST_ID = 1;
	
	@Autowired
	private AnnouncementController annController;
	
	@Autowired
	private AnnouncementService annService;
	
	@Autowired
	private VetService vetService;
	
	@Test
    void testListAnnouncements() throws Exception {
		ModelMap model=new ModelMap();

		String view = annController.showAnnouncementList(model);
		
		assertEquals(view,"announcements/announcementList");
		assertNotNull(model.get("announcement"));
	}
	
	@Test
	void testShowAnnouncementListFiltered() throws Exception {
		ModelMap model=new ModelMap();
		Announcement announcement = new Announcement();
		announcement = annService.findAnnouncementById(ANNOUNCEMENT_TEST_ID);

		String view = annController.showAnnouncementListFiltered(announcement, model);
		
		assertEquals(view,"announcements/announcementList");
		assertNotNull(model.get("announcement"));
	}
	
	@Test
    void testListOldAnnouncements() throws Exception {
		ModelMap model=new ModelMap();
		Announcement announcement = new Announcement();
		announcement = annService.findAnnouncementById(ANNOUNCEMENT_TEST_ID);

		String view = annController.ShowOldAnnouncementList(model, announcement);
		
		assertEquals(view,"announcements/announcementList");
		assertNotNull(model.get("announcement"));
	}
	
	@Test
	void testShowAnnouncement() throws Exception{
		ModelMap model=new ModelMap();

		String view = annController.ShowAnnouncementDetail(ANNOUNCEMENT_TEST_ID, model);
		
		assertEquals(view,"announcements/announcementDetails");
		assertNotNull(model.get("announcement"));
	}
	
	@Test
	void testInitAnnouncementCreationForm() throws Exception {
		ModelMap model=new ModelMap();
		
		String view= annController.initAnnouncementCreationForm(model);
		
		assertEquals(view,"announcements/createOrUpdateAnnouncementForm");
		assertNotNull(model.get("announcement"));
	}
	
	/*@Test
	void testProcessCreationFormSuccess() throws Exception {
		Announcement ann = new Announcement();
		ann.setBody("Este es el cuerpo");
		ann.setDate(LocalDate.now());
		ann.setHeader("Esta es la cabecera");
		ann.setLikes(15);
		ann.setTag("Este es el tag");
			Vet vet = new Vet();
			vet = vetService.findVetById(VET_TEST_ID);
		ann.setVet(vet);
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		
		String view = annController.processCreationForm(ann, bindingResult);
		
		assertEquals(view,"redirect:/announcements");
	}*/
	
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		Announcement ann = new Announcement();
		ann.setBody("Este es el cuerpo");
		ann.setDate(LocalDate.now());
		ann.setHeader("Esta es la cabecera");
		ann.setLikes(15);
		ann.setTag("Este es el tag");
		BindingResult bindingResult=new MapBindingResult(Collections.emptyMap(),"");
		bindingResult.reject("vet", "Required!");
		
		String view = annController.processCreationForm(ann, bindingResult);
		
		assertEquals(view,"announcements/createOrUpdateAnnouncementForm");
	}
}
