<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="insurances">
	
	<h2>Insurance Information</h2>
    <table class="table table-striped">
    <tr>
        <th>Date</th>
        <td><c:out value="${insurance.insuranceDate}"/></td>
    </tr>
    <tr>
        <th>Vaccines</th>
        <td>
	    	<c:forEach items="${insurance.vaccines}" var="vaccine">
	        	<c:out value="${vaccine.name} (${vaccine.price} Euros)"/><br/>
	        </c:forEach>
	    </td>
    </tr>
    <tr>
        <th>Treatments</th>
        <td>
        	<c:forEach items="${insurance.treatments}" var="treatment">
	        	<c:out value="${treatment.description} (${treatment.price} Euros)"/><br/>
	        </c:forEach>
        </td>
    </tr>
    <tr>
    	<th>Total price</th>
    	<td><c:out value="${insurance.insurancePrice} Euros"/></td>
    </tr>
    </table>
    <br/>
    <h2>Insurance Base Information</h2>
    <table class="table table-striped">
    <tr>
        <th>Pet Type</th>
        <td><c:out value="${insurance.insuranceBase.petType.name}"/></td>
    </tr>
    <tr>
        <th>Vaccines</th>
        <td>
	    	<c:forEach items="${insurance.insuranceBase.vaccines}" var="vaccine">
	        	<c:out value="${vaccine.name} (${vaccine.price} Euros)"/><br/>
	        </c:forEach>
	    </td>
    </tr>
    <tr>
        <th>Treatments</th>
        <td>
        	<c:forEach items="${insurance.insuranceBase.treatments}" var="treatment">
	        	<c:out value="${treatment.description} (${treatment.price} Euros)"/><br/>
	        </c:forEach>
        </td>
    </tr>
    <tr>
        <th>Conditions</th>
        <td><c:out value="${insurance.insuranceBase.conditions}"/></td>
    </tr>
    <tr>
    	<th>Base price</th>
    	<td><c:out value="${insurance.insuranceBase.price} Euros"/></td>
    </tr>
   </table>

	 <button onclick="goBack()">Return</button>

	
    <br/>
</petclinic:layout>

<script>
		function goBack() {
 		 window.history.back();
		}
	</script> 
