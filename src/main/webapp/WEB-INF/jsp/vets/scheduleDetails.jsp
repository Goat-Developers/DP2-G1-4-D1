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
		
			
			<c:forEach items ="${appointments}" var ="appointment">
			<tr>
				 
				<td> <c:out value ="${appointment.appointmentTime}"/></td>
				<td> <c:out value ="${appointment.reason}"/></td>
<!-- <sec:authorize access="hasAuthority('worker')">				 -->
<!-- 				 <td> -->
<%--                      <spring:url value="/vetSchedule/{day}/appointment/{appointmentId}" var="app"> --%>
<%--                      <spring:param name="appointmentId" value="${appointment.id}"/> --%>
<%--                      <spring:param name="day" value="${appointment.appointmentDate}"/> --%>
<%--                      </spring:url> --%>
<%--                      <a href="${fn:escapeXml(app)}"> Atender cita</a> --%>
<!--                </td>     -->
<!-- </sec:authorize> -->
			</tr>
			</c:forEach>
   			
		</table>
    
</petclinic:layout>
