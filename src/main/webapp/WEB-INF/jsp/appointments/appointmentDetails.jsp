<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="insurances">
	
	<h2>Información de la cita</h2>
    <table class="table table-striped">
    <tr>
        <th>Fecha</th>
        <td><c:out value="${appointment.appointmentDate} ${appointment.appointmentTime}"/></td>
    </tr>
    <tr>
        <th>Motivo de la cita</th>
        <td>
	    	<c:out value="${appointment.reason }" />
	    </td>
    </tr>
    <tr>
        <th>Mascota</th>
        <td>
        	
	        	<c:out value="${appointment.pet.name}"/><br/>
	 
        </td>
    </tr>
     <c:if test="${ appointment.treatment !=null }">
    <tr>
    	<th>Tratamiento</th>
    	<td><c:out value="${appointment.treatment.type}"/></td>
    </tr>
    </c:if>
    <c:if test="${ appointment.vaccine!=null }">
    <tr>
    	<th>Vacuna</th>
    	<td><c:out value="${appointment.vaccine.name}"/></td>
    </tr>
    </c:if>
    <tr>
    	<th>Estado de la cita</th>
    	<c:if test="${appointment.attended==false }">
    	<td>La cita no se ha atendido todavía</td>
    	</c:if>
    	<c:if test="${appointment.attended==true }">
    	<td>La cita fue atendida</td>
    	</c:if>
    </tr>
    <tr> 
    <th>Observaciones del veterinario</th>
    <td><c:out value="${appointment.observations }"></c:out></td>
    </tr>
    <tr>
    <th>Facturación</th>
    
     <c:if test="${appointement.pet.insurance == null }">
     <td>
     <c:if test="${ appointment.vaccine!=null }">
      <c:out value=" Vacuna --> ${appointment.vaccine.price } Euros"></c:out><br>
      </c:if>
       <c:if test="${ appointment.treatment !=null }">
     <c:out value="Tratamiento --> ${appointment.treatment.price } Euros"></c:out><br>
    </c:if>
     <hr>
    <c:out value="Total ${appointment.billing } Euros"></c:out></td>
    </c:if>
    </tr>
    </table>
   <sec:authorize access="!hasAuthority('owner')" >
    	<form:form modelAttribute="appointment" action="/appointment/observe" method="post">
    		 <input type="hidden" name="id" value="${appointment.id}"/>
    		 <petclinic:inputField label="Observaciones" name="observations"/>
         <button class="btn btn-default" type="submit">Actualizar Propietario</button>
          
          </form:form>
   
   </sec:authorize>
 
   <br>
   <hr>

	 <button onclick="goBack()">Volver</button>

	
</petclinic:layout>

<script>
		function goBack() {
 		 window.history.back();
		}
	</script> 
