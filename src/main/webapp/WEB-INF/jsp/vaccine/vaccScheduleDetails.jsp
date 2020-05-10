<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="vaccinationSchedule">

    <h1>Calendario de vacunas de <c:out value="${pet.name}"/> </h1>
    <br>
    <c:if test="${next }">
    <h2>Próxima Vacuna</h2>
    <table class="table table-striped">
    <td> <c:out value="${proxVaccine.name}"/> </td>
    <td> <c:out value="${proxDate}"/> </td>
   <spring:url value="/appointment/new/{petId}" var="appointmentUrl">
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <td><a href="${fn:escapeXml(appointmentUrl)}">Solicitar cita</a></td>
    </table>
    </c:if>
	<table class="table table-striped">
	<td>
		<c:forEach var="date" items="${ vaccinationSchedule.dates}">
		
		<dt> <c:out value="${date}"/> </dt>
		</c:forEach>
		
	</td>
	<td>
		<c:forEach var="vaccine" items="${vaccinationSchedule.vaccines}">
		<dd> <c:out value="${vaccine.name}"/> </dd>
		</c:forEach>
	</td>
	
	</table>


  
    
</petclinic:layout>
