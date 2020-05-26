package org.springframework.samples.petclinic.util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.samples.petclinic.model.Insurance;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Treatment;
import org.springframework.samples.petclinic.model.Vaccine;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;


public class GmailQuickstart {
	@Autowired
    private static ResourceLoader resourceLoader;
    private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_LABELS, GmailScopes.GMAIL_INSERT, GmailScopes.MAIL_GOOGLE_COM);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = GmailQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Print the labels in the user's account.
        String user = "me";
        ListLabelsResponse listResponse = service.users().labels().list(user).execute();
        List<Label> labels = listResponse.getLabels();
        if (labels.isEmpty()) {
            System.out.println("No labels found.");
        } else {
            System.out.println("Labels:");
            for (Label label : labels) {
                System.out.printf("- %s\n", label.getName());
            }
        }
       
    }
    public static void SendingEmail(@Valid Insurance insurance, Pet pet) throws GeneralSecurityException, IOException, MessagingException {
    	final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    	 Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                 .setApplicationName(APPLICATION_NAME)
                 .build();
    	 	String user = "me";
    	 	String body = "Tanks for choosing PetClinic"+"\n"+"\n" + " Hi "+pet.getOwner().getFirstName()+"\n"+"\n"+" You have taken out the insurance base : "+" "+ insurance.getInsuranceBase().getName()+"\n"
    	 			+" with a list of vaccines"+""+":"
    	 			;
    	 	if(insurance.getVaccines()!=null) {
    	 		for(Vaccine v: insurance.getVaccines()) {
        	 		
        	 		body += v.getName();
        	 		if(insurance.getVaccines().size()>1) {
        	 			body+=",";
        	 		}
        	 	}
    	 	}
    	 	if(insurance.getTreatments()!=null) {
    	 		body +="\n"+""+ " and a list of treatments:";
        	 	for(Treatment t: insurance.getTreatments()) {
        	 		body+=t.getDescription();
        	 		if(insurance.getTreatments().size()>1) {
        	 			body+=",";
        	 		}
        	 	}
    	 	}
    	 	
    	 	body+="\n"+""+"all with a cost of"+" "+ insurance.getInsurancePrice()+" euros";
     
    	 	
    	 	//String s=ResourceUtils.class.getResource("static/resources/images/pets.png").toString();
    	 	
    	 	//Resource resource = resourceLoader.getResource(".\\resources\\images\\pets.png");

    	 	
    		 File file = new File("./target/classes/static/resources/images/pets.png");
 			
			MimeMessage message = SendEmail.createEmailWithAttachment("springtest89@gmail.com", "me", "Hire Insurance", body, file );
 			SendEmail.sendMessage(service, user, message);
 			System.out.println("email sended");
 			
 		
    }
}