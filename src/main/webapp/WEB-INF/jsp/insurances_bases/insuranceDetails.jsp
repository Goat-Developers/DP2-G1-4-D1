<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="insurance_base">

	<h2>Insurance Base Information</h2>

    <table class="table table-striped">
    <tr>
        <th>Pet Type</th>
        <td><c:out value="${insurance_base.petType.name}"/></td>
    </tr>
    <tr>
        <th>Vaccines</th>
        <td>
	    	<c:forEach items="${insurance_base.vaccines}" var="vaccine">
	        	<c:out value="${vaccine.name} (${vaccine.price} Euros)"/><br/>
	        </c:forEach>
	    </td>
    </tr>
    <tr>
        <th>Treatments</th>
        <td>
        	<c:forEach items="${insurance_base.treatments}" var="treatment">
	        	<c:out value="${treatment.description} (${treatment.price} Euros)"/><br/>
	        </c:forEach>
        </td>
    </tr>
    <tr>
        <th>Conditions</th>
        <td><c:out value="${insurance_base.conditions}"/></td>
    </tr>
    <tr>
    	<th>Total price</th>
    	<td><c:out value="${insurance_base.price} Euros"/></td>
    </tr>
    </table>
	<a class="btn btn-default" href='<spring:url value="/insurances_bases" htmlEscape="true"/>'>Return</a>
    <br/>
</petclinic:layout>
