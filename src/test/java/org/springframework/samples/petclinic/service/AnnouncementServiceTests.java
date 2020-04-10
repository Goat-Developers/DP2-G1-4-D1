package org.springframework.samples.petclinic.service;



import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.model.Announcement;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.AnnouncementRepository;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AnnouncementServiceTests {
	
	@Autowired
	protected  AnnouncementService annService;
	
	@Mock
	private AnnouncementRepository annRepository;
	
	
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean(); 
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean; 
		} 
	@Test
	void shouldFindCurrentAnnouncements() {
		Collection<Announcement> announcements = this.annService.findAnnouncements();
		
		Announcement a = EntityUtils.getById(announcements, Announcement.class, 4);
		assertThat(a.getBody()).isEqualTo("esto body CON MAYUS");
		assertThat(a.getHeader()).isEqualTo("esto header en tiempo");
		assertThat(a.getDate()).isEqualTo("2020-03-22");
		assertThat(a.getTag()).isEqualTo("gatos");
		
	}
	@Test
	void shouldFindOldAnnouncements() {
		Collection<Announcement> announcements = (Collection<Announcement>) this.annService.findOldAnnouncements();
		Announcement a = EntityUtils.getById(announcements, Announcement.class, 1);
		assertThat(a.getBody()).isEqualTo("esto body");
		assertThat(a.getHeader()).isEqualTo("esto header");
		assertThat(a.getDate()).isEqualTo("2010-09-07");
		assertThat(a.getTag()).isEqualTo("tag1");
	}
	
	@Test
	void shouldFindAnnouncementsByTag() {
		Collection<Announcement> announcements = this.annService.findAnnouncementsByTag("gatos");
		assertThat(announcements.size()).isEqualTo(2);

		announcements = this.annService.findAnnouncementsByTag("gatod");
		assertThat(announcements.isEmpty()).isTrue();
	}
	@ParameterizedTest
	@ValueSource(ints= {5,6,7,8})
	void failureFindAnnouncementsById(int argument) {
		Assertions.assertThrows(NullPointerException.class, () ->{this.annService.findAnnouncementById(argument).getBody();});
	}

	@Test
	@Transactional
	public void shouldInsertAnnouncement() {
		Collection<Announcement> announcements =  this.annService.findAnnouncementsByTag("Testing tag");
		Vet vetAdin = this.annService.findVetByUser("vet1");
		int found = announcements.size();

		Announcement a = new Announcement();
		a.setHeader("Testing header");
		a.setBody("Testing body");
		a.setDate(LocalDate.now());
		a.setLikes(0);
		a.setTag("Testing tag");
		a.setVet(vetAdin);
                             
        this.annService.saveAnnouncement(a);
		assertThat(a.getId().longValue()).isNotEqualTo(0);

		announcements = this.annService.findAnnouncementsByTag("Testing tag");
		assertThat(announcements.size()).isEqualTo(found + 1);
	}
	@Test
	public void failureInsertAnnouncement() {
		Vet vetAdin = this.annService.findVetByUser("vet1");
		
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Announcement a = new Announcement();
		a.setHeader("Testing header");
		a.setBody("");
		a.setDate(LocalDate.now());
		a.setLikes(0);
		a.setTag("Testing tag");
		a.setVet(vetAdin);
		
		Validator v = createValidator();
		Set<ConstraintViolation<Announcement>> constraintViolations =  v.validate(a); 
		assertThat(constraintViolations.size()).isEqualTo(1); 
		ConstraintViolation<Announcement> violation =   constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()) .isEqualTo("body"); 
		assertThat(violation.getMessage()).isEqualTo("must not be empty"); 
		Assertions.assertThrows(Exception.class, () -> {this.annService.saveAnnouncement(a);});
		
	}


	@ParameterizedTest
	@ValueSource(ints= {1,2,3,4})
	public void shouldFindOnlyOneVetForAnnouncements(int argument) {
		Announcement a = this.annService.findAnnouncementById(argument);
		assertThat(a.getVet().getFirstName()).startsWith("James");
	}
	@Test
	public void shouldFindAnnouncements() {
		AnnouncementService annServices = new AnnouncementService(annRepository);
		Vet sampleVet = new Vet();
		sampleVet.setFirstName("James");
		Announcement a = new Announcement();
		a.setBody("test body");
		a.setHeader("test header");
		a.setTag("test tag");
		a.setDate(LocalDate.of(2020, 3, 27));
		a.setId(1);
		a.setLikes(0);
		a.setVet(sampleVet);
		Collection<Announcement> announcements = new ArrayList<Announcement>();
		announcements.add(a);
		when(annRepository.findAll()).thenReturn(announcements);
		Collection<Announcement> anns = annServices.findAnnouncements();
		assertThat(anns).hasSize(1);
		Announcement aN= anns.iterator().next();
		assertThat(aN.getBody()).isEqualTo("test body");
	}
	
}
