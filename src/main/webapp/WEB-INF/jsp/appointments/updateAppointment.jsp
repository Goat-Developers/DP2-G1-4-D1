<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="appointment">
    <form:form modelAttribute="appointment" class="form-horizontal" id="update-appointment-form">
      	<div class="form-group has-feedback">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Dia</label>
                        <c:out value="${appointment.appointmentDate}"/>
                        </div>
                        <div class="col-sm-10">
                    <label class="col-sm-2 control-label">Hora</label>
                        <c:out value="${appointment.appointmentTime}"/>
                        </div>
                        <div class="col-sm-10">
                    <label class="col-sm-2 control-label">Motivo</label>
                        <c:out value="${appointment.reason}"/>
                      </div>
                        <div class="col-sm-10">
                    <label class="col-sm-2 control-label">Mascota</label>
                        <c:out value="${appointment.pet.name}"/>
                        </div>
                        <div class="col-sm-10">
                    <label class="col-sm-2 control-label">Tratamiento</label>
                        <c:out value="${appointment.treatment.type}"/>
                        </div>
                        <div class="col-sm-10">
                    
                    <label class="col-sm-2 control-label">Vacuna</label>
                   
                        <c:out value="${appointment.vaccine.name}"/>
                        </div>
                        <div class="col-sm-10">
                   
                    <label class="col-sm-2 control-label">Precio total</label>
                  
                        <c:out value="${appointment.billing}"/>
                         </div>
                        <div class="col-sm-10">
                  
                   
                </div>
                 <div class="col-sm-10">
                <petclinic:inputField label="Observaciones" name="observations"/>
                <input type="hidden" name="appointementId" value="${appointment.id}">
                 </div>
        	
        	 
            
        </div>
        <spring:url value="/appointment/{appointementId}/edit" var="appointementId">
                        <spring:param name="appointementId" value="${appointment.id}"/>
                    </spring:url>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
             	<button class="btn btn-default" type="submit">Completar cita</button>
            </div>
            
        </div>
    </form:form>
</petclinic:layout>