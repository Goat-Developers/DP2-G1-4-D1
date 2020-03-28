<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="insurances">
	
	<h2>Información del Seguro</h2>
    <table class="table table-striped">
    <tr>
        <th>Fecha</th>
        <td><c:out value="${insurance.insuranceDate}"/></td>
    </tr>
    <tr>
        <th>Vacunas</th>
        <td>
	    	<c:forEach items="${insurance.vaccines}" var="vaccine">
	        	<c:out value="${vaccine.name} (${vaccine.price} Euros)"/><br/>
	        </c:forEach>
	    </td>
    </tr>
    <tr>
        <th>Tratamientos</th>
        <td>
        	<c:forEach items="${insurance.treatments}" var="treatment">
	        	<c:out value="${treatment.description} (${treatment.price} Euros)"/><br/>
	        </c:forEach>
        </td>
    </tr>
    <tr>
    	<th>Precio Total</th>
    	<td><c:out value="${insurance.insurancePrice} Euros"/></td>
    </tr>
    </table>
    <br/>
    <h2>Información del Seguro Base</h2>
    <table class="table table-striped">
    <tr>
        <th>Tipo de Mascota</th>
        <td><c:out value="${insurance.insuranceBase.petType.name}"/></td>
    </tr>
    <tr>
        <th>Vacunas</th>
        <td>
	    	<c:forEach items="${insurance.insuranceBase.vaccines}" var="vaccine">
	        	<c:out value="${vaccine.name} (${vaccine.price} Euros)"/><br/>
	        </c:forEach>
	    </td>
    </tr>
    <tr>
        <th>Tratamientos</th>
        <td>
        	<c:forEach items="${insurance.insuranceBase.treatments}" var="treatment">
	        	<c:out value="${treatment.description} (${treatment.price} Euros)"/><br/>
	        </c:forEach>
        </td>
    </tr>
    <tr>
        <th>Condiciones</th>
        <td><c:out value="${insurance.insuranceBase.conditions}"/></td>
    </tr>
    <tr>
    	<th>Precio Base</th>
    	<td><c:out value="${insurance.insuranceBase.price} Euros"/></td>
    </tr>
   </table>

	 <button onclick="goBack()">Volver</button>

	
    <br/>
</petclinic:layout>

<script>
		function goBack() {
 		 window.history.back();
		}
	</script> 
