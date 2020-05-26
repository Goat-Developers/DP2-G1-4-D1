package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Announcement;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.service.AnnouncementService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AnnouncementController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class)
,excludeAutoConfiguration= SecurityConfiguration.class)
public class AnnouncementControllerTests {
	
	private static final String VET_USERNAME = "vet1";
	private static final int VET_TEST_ID = 1;
	private static final int ANNOUNCEMENT_TEST_ID = 1;
	
	@Autowired
	private AnnouncementController annController;
	
	@MockBean
	private AnnouncementService annService;
	
	@MockBean
	private VetService VetService;
	
	@MockBean
	private AuthoritiesService authService;
	
	@Autowired
	private MockMvc mockMvc;

	private Announcement announcement;
	
	private Vet vet;
	
	@BeforeEach
	void setUp() {
		vet = new Vet();
		vet.setFirstName("James");
		vet.setLastName("Carter");
		vet.setId(AnnouncementControllerTests.VET_TEST_ID);
		given(this.annService.findVetByUser(VET_USERNAME)).willReturn(vet);
		
		announcement = new Announcement();
		announcement.setId(AnnouncementControllerTests.ANNOUNCEMENT_TEST_ID);
		announcement.setBody("esto body");
		announcement.setHeader("esto header");
		announcement.setLikes(0);
		announcement.setTag("tag1");
		announcement.setDate(LocalDate.of(2020,3,23));
		announcement.setVet(vet);
		given(this.annService.findAnnouncementById(AnnouncementControllerTests.ANNOUNCEMENT_TEST_ID)).willReturn(announcement);
	}
	
	@WithMockUser(value = "vet1")
    @Test
    void testShowAnnouncements() throws Exception {
	given(this.annService.findAnnouncements()).willReturn(Lists.newArrayList(announcement));
		
	mockMvc.perform(get("/announcements")).andExpect(status().isOk()).andExpect(model().attributeExists("announcements"))
	.andExpect(view().name("announcements/announcementList"));
	}

	@WithMockUser(value = "vet1")
    @Test
	void testShowAnnouncementListFiltered() throws Exception{
		
		mockMvc.perform(get("/announcements/tag").param("tag", "tag1")
				.with(csrf()))
		.andExpect(model().attributeExists("announcement"))
		.andExpect(model().attributeExists("announcements"))
	    .andExpect(view().name("announcements/announcementList"));
		
	}
	
	@WithMockUser(value =  "vet1")
	@Test
	void testShowAnnouncementDetail() throws Exception{
		mockMvc.perform(get("/announcements/{announcementId}",ANNOUNCEMENT_TEST_ID).with(csrf()))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("announcement"))
		.andExpect(model().attribute("announcement", hasProperty("header", is("esto header"))))
		.andExpect(model().attribute("announcement", hasProperty("body", is("esto body"))))
		.andExpect(model().attribute("announcement", hasProperty("tag", is("tag1"))))
		.andExpect(model().attribute("announcement", hasProperty("likes", is(0))))
		.andExpect(model().attribute("announcement", hasProperty("date", is(LocalDate.of(2020,3,23)))))
		.andExpect(model().attribute("announcement", hasProperty("vet", is(vet))))
		.andExpect(view().name("announcements/announcementDetails"))
		;
	}

	@WithMockUser(value = "vet1")
	@Test
	void testInitAnnouncementCreationForm() throws Exception{
		mockMvc.perform(get("/announcement/new")).andExpect(status().isOk())
		.andExpect(model().attributeExists("announcement"))
		.andExpect(view().name("announcements/createOrUpdateAnnouncementForm"));
	}
	@WithMockUser(value = "vet1")
	@Test
	void testProcessCreationFormSuccess() throws Exception{
		mockMvc.perform(post("/announcement/new").param("header","another header")
				.param("body","other body")
				.param("tag", "othertag")
				.with(csrf()))
				.andExpect(status().is3xxRedirection());
		
	}
	@WithMockUser(value = "vet1")
	@Test
	void testProcessCreationFormFailure() throws Exception{
		mockMvc.perform(post("/announcement/new").param("header","another header")
				.param("body","other body")
				.with(csrf()))
				.andExpect(model().attributeHasErrors("announcement"))
				.andExpect(model().attributeHasFieldErrors("announcement", "tag"))
				.andExpect(view().name("announcements/createOrUpdateAnnouncementForm"));
	}
	@WithMockUser(value = "vet1")
	@Test
	void tesLikeIncrement() throws Exception{
		mockMvc.perform(post("/announcements/like").with(csrf()))
		.andExpect(status().isOk());

	}
}
