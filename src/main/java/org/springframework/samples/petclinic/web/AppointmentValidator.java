package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.samples.petclinic.model.Appointment;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Shift;
import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.service.VetScheduleService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AppointmentValidator implements Validator{
	
	private static final String  REQUIRED = "required";
	
	
	private final VetScheduleService vtSchService;
	
	public AppointmentValidator(VetScheduleService vtSchService) {
		this.vtSchService = vtSchService;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Appointment.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Appointment appointment = (Appointment) target;
		Pet pet  = appointment.getPet();
		Set<Appointment> appointments = pet.getAppointments();
		LocalDate dateApp = appointment.getAppointmentDate();
		LocalTime timeApp = appointment.getAppointmentTime();
		
		for(Appointment a: appointments) {
			if(a.getAppointmentDate().equals(dateApp) && a.getAppointmentTime().equals(timeApp) ) {
				errors.rejectValue("appointmentDate", AppointmentValidator.REQUIRED + " su mascota ya dispone de una cita para ese día y hora", AppointmentValidator.REQUIRED + " su mascota ya dispone de una cita para ese día y hora");
			}
		}
		
		if(appointment.getReason().isEmpty()) {
			errors.rejectValue("reason", AppointmentValidator.REQUIRED + " debe facilitar un motivo para la cita", AppointmentValidator.REQUIRED + " debe facilitar un motivo para la cita");
		}
		
		
		boolean res = false;
		List<VetSchedule> allVetSchedule = (List<VetSchedule>) this.vtSchService.findAll();
		for(int i=0; i<allVetSchedule.size(); i++) {
			List<LocalTime> shifts = allVetSchedule.get(i).getShifts().stream().map(Shift::getShiftDate).collect(Collectors.toList());
			if(shifts.contains(appointment.getAppointmentTime())) {
				List<Appointment> appVetSchedule = allVetSchedule.get(i).getAppointments().stream().filter(x->x.getAppointmentDate().equals(appointment.getAppointmentDate())&& x.getAppointmentTime().equals(appointment.getAppointmentTime())).collect(Collectors.toList());
				if(appVetSchedule.size()==0) {
					res= true;
					break;
				}
			}
			
		}
		if(!res) {
			errors.rejectValue("appointmentTime", AppointmentValidator.REQUIRED + " no hay cita disponible a esa hora y ese día", AppointmentValidator.REQUIRED + " no hay cita disponible a esa hora y ese día");
		}
		
		if (appointment.getAppointmentDate() == null ||appointment.getAppointmentDate().isBefore(LocalDate.now())) {
			errors.rejectValue("appointmentDate", AppointmentValidator.REQUIRED + " la fecha de la cita no puede ser anterior a hoy", AppointmentValidator.REQUIRED + " la fecha de la cita no puede ser anterior a hoy");
		}
		
		if (appointment.getAppointmentTime().isBefore(LocalTime.now()) && appointment.getAppointmentDate().equals(LocalDate.now())) {
			errors.rejectValue("appointmentTime", AppointmentValidator.REQUIRED + " la hora de la cita no puede ser anterior a la actual "+ LocalTime.now().getHour()+ " : "+ LocalTime.now().getMinute(), AppointmentValidator.REQUIRED + " la hora de la cita no puede ser anterior a la actual "+  LocalTime.now().getHour()+ " : "+ LocalTime.now().getMinute());
		}
	}
	

}
