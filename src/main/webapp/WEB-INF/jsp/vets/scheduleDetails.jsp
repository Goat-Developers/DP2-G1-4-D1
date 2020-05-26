<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Schedule Details">


	<h2>Citas del día</h2>
		<table class="table table-striped">
		<thead>
        <tr>
            <th>Hora de la cita</th>
            <th>Motivos</th>
            <th>Atendido</th>
            <th>Propietario</th>
            <th>Otros</th>
      

            
            
        </tr>
        </thead>
			
			<c:forEach items ="${appointments}" var ="appointment">
			<tr>
				 
				<td> <c:out value ="${appointment.appointmentTime}"/></td>
				<td> <c:out value ="${appointment.reason}"/></td>
				<td> <c:out value ="${appointment.attended}"/></td>
				<td> <c:out value ="${appointment.pet.owner.firstName} ${appointment.pet.owner.lastName}"/></td>
				<td>
				<c:if test="${appointment.attended==false}">
				<spring:url value="/appointment/{appointementId}" var="editUrl">
				
        				<spring:param name="appointementId" value="${appointment.id}"/>
   	 			</spring:url>
    			<a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Atender cita</a>
    			</c:if> 
				</td>

			</tr>
			</c:forEach>
   			
		</table>
		
    
</petclinic:layout>
