package org.springframework.samples.petclinic.web.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.core.io.ResourceLoader;
import org.springframework.samples.petclinic.model.Insurance;
import org.springframework.samples.petclinic.model.InsuranceBase;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.Vaccine;
import org.springframework.samples.petclinic.service.InsuranceBaseService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.TreatmentService;
import org.springframework.samples.petclinic.service.VaccineService;
import org.springframework.samples.petclinic.util.GmailQuickstart;
import org.springframework.samples.petclinic.util.SendEmail;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import io.cucumber.java.en.When;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
public class GmailAPITest {

	@Autowired
    private static ResourceLoader resourceLoader;
    private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

	
	
	
	@Test
	void testSendingEmail() throws Exception{
//		Insurance seguro = new Insurance();
//		InsuranceBase seguroBase = new InsuranceBase();
//		Vaccine vacc= new Vaccine();
//		vacc.setPrice(10.0);
//		Treatment treat = new Treatment();
//		treat.setPrice(10.0);
//		Set<Treatment> treatments = new HashSet<Treatment>();
//		treatments.add(treat);
//		seguroBase.setTreatments(treatments);
//		Set<Vaccine> vaccines= new HashSet<Vaccine>();
//		vaccines.add(vacc);
//		seguroBase.setVaccines(vaccines);
//		seguroBase.setName("prueba base");
//		seguro.setInsuranceBase(seguroBase);
//		seguro.setTreatments(treatments);
//		seguro.setVaccines(vaccines);
//		Pet pet = new Pet();
//		Owner owner = new Owner();
//		owner.addPet(pet);
//		owner.setFirstName("Carlos");
//		owner.setLastName("Cote");
//		GmailQuickstart.SendingEmail(seguro, pet);
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
   	 Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, GmailQuickstart.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
   	 String user = "me";
		 File file = new File("./target/classes/static/resources/images/pets.png");
		MimeMessage message = SendEmail.createEmailWithAttachment("springtest89@gmail.com", "me", "Hire Insurance", "hola", file );
			 Message sgg = SendEmail.sendMessage(service, user, message);
			 assertEquals(sgg.getLabelIds().toString(), "[SENT]");
		
		
		
	}
	
}
