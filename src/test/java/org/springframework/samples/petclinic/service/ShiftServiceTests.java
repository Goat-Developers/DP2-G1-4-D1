package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Shift;
import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.repository.ShiftRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ShiftServiceTests {
	
	@Autowired
	protected ShiftService shiftService;
	
	@Mock
	private ShiftRepository shiftRepository;
	

	//Descomentar cuando se meta algun shift en el data

	/*@Test
	void shouldFindSingleShiftById() {
		Shift shift = this.shiftService.findById(1);
		assertThat(shift.getShiftDate()).isNotNull();
	}*/
	
	@ParameterizedTest
	@ValueSource(ints= {0,-6,100})
	void shouldFailFindSingleShiftById(int argument) {
		Assertions.assertThrows(NullPointerException.class, () -> {this.shiftService.findById(argument).getShiftDate();});
	}

	@Test
	@Transactional
	public void shouldInsertShiftIntoDatabaseAndGenerateId() {
		Collection<Shift> shifts = this.shiftService.findAll();
		int found = shifts.size();
		
		Shift shift = new Shift();
		shift.setShiftDate(LocalTime.of(11, 45, 0));
        
        this.shiftService.saveShift(shift);
        
        shifts = this.shiftService.findAll();
		assertThat(shifts.size()).isEqualTo(found + 1);
        // checks that id has been generated
        assertThat(shift.getId()).isNotNull();
	}
	
	@Test
	public void addNullShift() {
		Shift dummy = null;
		assertThrows(Exception.class, () -> this.shiftService.saveShift(dummy));
	}
	
	@Test
	public void testInvalidShift() throws Exception {
		Shift shift = new Shift();
		shift.setId(-2);
		ShiftRepository shiftRepository = mock(ShiftRepository.class);
		when(shiftRepository.findById(-2)).thenThrow(new RuntimeException());
		ShiftService shiftService = new ShiftService(shiftRepository);
		assertThrows(RuntimeException.class, () -> shiftService.findById(shift.getId()));
	}
	

	@Test

	public void shouldOrderShift() {
		Shift shift1 = new Shift(); Shift shift2 = new Shift();
		shift1.setShiftDate(LocalTime.of(10, 45, 0)); shift2.setShiftDate(LocalTime.of(11, 45, 0));
		Set<Shift> shifts = new HashSet<>();
		shifts.add(shift2); shifts.add(shift1);
		VetSchedule vs = new VetSchedule();
		vs.setShifts(shifts);
		
		List<Shift> ls = this.shiftService.orderShifts(vs);
		assertThat(ls.get(0).getShiftDate()).isEqualTo("10:45:00");
	}
}
