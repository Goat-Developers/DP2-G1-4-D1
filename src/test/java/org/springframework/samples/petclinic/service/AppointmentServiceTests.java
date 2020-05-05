package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Appointment;
import org.springframework.samples.petclinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class AppointmentServiceTests {
	
	@Autowired
	protected AppointmentService appointmentService;
	
	@Mock
	private AppointmentRepository appointmentRepository;
	
	//Descomentar cuando se meta algun appointment en el data
	/*@Test
	void shouldFindSingleAppointmentById() {
		Appointment app = this.appointmentService.findAppointmentById(1);
		assertThat(app.getBilling()).isNotNull();
	}*/
	
	@ParameterizedTest
	@ValueSource(ints= {0,-6,100})
	void shouldFailFindSingleAppointmentById(int argument) {
		Assertions.assertThrows(NullPointerException.class, () -> {this.appointmentService.findAppointmentById(argument).getBilling();});
	}

	@Test
	@Transactional
	public void shouldInsertAppointmentIntoDatabaseAndGenerateId() {
		Collection<Appointment> appointments = this.appointmentService.findAll();
		int found = appointments.size();
		
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(LocalDate.of(2021, Month.APRIL, 3));
		appointment.setAppointmentTime(LocalTime.of(11, 45, 0));
		appointment.setReason("Deseaba una cita");
		appointment.setAttended(false);
		appointment.setObservations("Observaciones de la cita");
		appointment.setBilling(40.);
        
        this.appointmentService.saveAppointment(appointment);
        
        appointments = this.appointmentService.findAll();
		assertThat(appointments.size()).isEqualTo(found + 1);
        // checks that id has been generated
        assertThat(appointment.getId()).isNotNull();
	}
	
	@Test
	public void addNullAppointment() {
		Appointment dummy = null;
		assertThrows(Exception.class, () -> this.appointmentService.saveAppointment(dummy));
	}
	
	@Test
	public void testInvalidAppointment() throws Exception {
		Appointment appointment = new Appointment();
		appointment.setId(-2);
		AppointmentRepository appointmentRepository = mock(AppointmentRepository.class);
		when(appointmentRepository.findAppById(-2)).thenThrow(new RuntimeException());
		AppointmentService appointmentService = new AppointmentService(appointmentRepository);
		assertThrows(RuntimeException.class, () -> appointmentService.findAppointmentById(appointment.getId()));
	}
}
