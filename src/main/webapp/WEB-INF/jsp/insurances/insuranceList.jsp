<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="insurances">
    <h2>Insurances</h2>

    <table id="insurancesTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Num</th>
        	<th>Conditions</th>
            <th>Vaccines</th>
            <th>Treatment</th>
            <th>Total price</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${insurances.insuranceList}" var="insurance">
            <tr>
            	<td>
            		<spring:url value="/insurances/{insuranceId}" var="insUrl">
                    <spring:param name="insuranceId" value="${insurance.id}"/>
                    </spring:url>
                    <c:out value="${insurance.id}"/>
            	</td>
            	<td>
                    <c:out value="${insurance.insuranceBase.conditions}"/>
                </td>
	            <td>
	            	<c:forEach items="${insurance.vaccines}" var="vaccine">
	            		<c:out value="${vaccine.name} (${vaccine.price} Euros)"/>
	            		<br/>
	            	</c:forEach>
	            </td>
                <td>
                	<c:forEach items="${insurance.treatments}" var="treatment">
	            		<c:out value="${treatment.description} (${treatment.price} Euros)"/><br/>
	            	</c:forEach>
                </td>
                <td>
                	<c:out value="${insurance.insurancePrice} Euros"/>
                </td>
            </tr>
           
        </c:forEach>
        </tbody>
    </table>
    	
    	<a class="btn btn-default" href='<spring:url value="/insurance/new" htmlEscape="true"/>'>Create insurance</a>
    	
    <br/> 
</petclinic:layout>

<!-- Descomentar cuando este acabado (HU9) -->
<!-- <sec:authorize access="hasAuthority('veterinarian')"> -->

<!-- </sec:authorize> -->
