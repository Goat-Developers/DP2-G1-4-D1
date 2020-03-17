<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="insurances_bases">
    <h2>Insurances Bases</h2>

    <table id="insurances_basesTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Conditions</th>
            <th>Vaccines</th>
            <th>Treatment</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${insurances_bases.insuranceList}" var="insuranceBase">
            <tr>
            	<td>
            		<spring:url value="/insurances_bases/{insuranceBaseId}" var="insBaseUrl">
                    <spring:param name="insuranceBaseId" value="${insuranceBase.id}"/>
                    </spring:url>
                    <c:out value="${insuranceBase.conditions}"/>
                </td>
	            <td>
	            	<c:forEach items="${insuranceBase.vaccines}" var="vaccine">
	            		<c:out value="${vaccine.name}"/><br/>
	            	</c:forEach>
	            </td>
                <td>
                	<c:forEach items="${insuranceBase.treatments}" var="treatment">
	            		<c:out value="${treatment.description}"/><br/>
	            	</c:forEach>
                </td>
            </tr>
           
        </c:forEach>
        </tbody>
    </table>
    <br/> 
</petclinic:layout>
