package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Appointment;
import org.springframework.samples.petclinic.model.Shift;
import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.repository.VetScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class VetScheduleServiceTests {
	
	@Autowired
	protected VetScheduleService vetScheduleService;
	
	@Mock
	private VetScheduleRepository vetScheduleRepository;
	

	@Test
	void shouldFindAppointmentsByDay() {
		List<Appointment> appointments = this.vetScheduleService.findAppointmentsByDay(LocalDate.of(2021, Month.APRIL, 16));
		assertThat(appointments.isEmpty()).isTrue();
		//assertThat(appointments.get(0).getAppointmentDate()).isEqualTo("2021/04/16");
	}

	
	@Test
	@Transactional
	public void shouldInsertVetScheduleIntoDatabaseAndGenerateId() {
		Collection<VetSchedule> vetSchedules = this.vetScheduleService.findAll();
		int found = vetSchedules.size();
		
		VetSchedule vs = new VetSchedule();
			Shift shift1 = new Shift(); Shift shift2 = new Shift();
			shift1.setShiftDate(LocalTime.of(10, 45, 0)); shift2.setShiftDate(LocalTime.of(11, 45, 0));
			Set<Shift> shifts = new HashSet<>();
			shifts.add(shift2); shifts.add(shift1);
			
			Appointment app1 = new Appointment(); Appointment app2 = new Appointment();
			app1.setAppointmentDate(LocalDate.of(2021, Month.APRIL, 3));
			app1.setAppointmentTime(LocalTime.of(11, 45, 0));
			app1.setReason("Deseaba una cita");
			app1.setAttended(false);
			app1.setObservations("Observaciones de la cita");
			app1.setBilling(40.);
			app2.setAppointmentDate(LocalDate.of(2021, Month.APRIL, 3));
			app2.setAppointmentTime(LocalTime.of(11, 45, 0));
			app2.setReason("Deseabamos una cita");
			app2.setAttended(true);
			app2.setObservations("Observaciones de la citacion");
			app2.setBilling(25.);
			Set<Appointment> appointments = new HashSet<>();
			appointments.add(app1); appointments.add(app2);


		vs.setShifts(shifts);
		vs.setAppointments(appointments);
		

        this.vetScheduleService.saveVtSchedule(vs);
        
        vetSchedules = this.vetScheduleService.findAll();
		assertThat(vetSchedules.size()).isEqualTo(found + 1);
        // checks that id has been generated
        assertThat(vs.getId()).isNotNull();
	}
	
	@Test
	public void addNullVetSchedule() {
		VetSchedule dummy = null;
		assertThrows(Exception.class, () -> this.vetScheduleService.saveVtSchedule(dummy));
	}

}
