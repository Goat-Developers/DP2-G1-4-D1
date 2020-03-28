<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="insurance_base">

	<h2>Seguro Base <c:out value="${insurance_base.name}"/> Información</h2>

    <table class="table table-striped">
    <tr>
        <th>Tipo de mascota</th>
        <td><c:out value="${insurance_base.petType.name}"/></td>
    </tr>
    <tr>
        <th>Vacunas</th>
        <td>
	    	<c:forEach items="${insurance_base.vaccines}" var="vaccine">
	        	<c:out value="${vaccine.name} (${vaccine.price} Euros)"/><br/>
	        </c:forEach>
	    </td>
    </tr>
    <tr>
        <th>Tratamientos</th>
        <td>
        	<c:forEach items="${insurance_base.treatments}" var="treatment">
	        	<c:out value="${treatment.description} (${treatment.price} Euros)"/><br/>
	        </c:forEach>
        </td>
    </tr>
    <tr>
        <th>Condiciones</th>
        <td><c:out value="${insurance_base.conditions}"/></td>
    </tr>
    <tr>
    	<th>Precio total</th>
    	<td><c:out value="${insurance_base.price} Euros"/></td>
    </tr>
    </table>
	<a class="btn btn-default" href='<spring:url value="/insurancesbases" htmlEscape="true"/>'>Volver</a>
    <br/>
</petclinic:layout>
