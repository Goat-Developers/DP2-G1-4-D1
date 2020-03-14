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
        	<th>Conditions</th>
            <th>Vaccines</th>
            <th>Treatment</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${insurances.insuranceList}" var="insurance">
            <tr>
            	<td>
            		<spring:url value="/insurances/{insuranceId}" var="insUrl">
                        <spring:param name="insuranceId" value="${insurance.id}"/>
                    </spring:url>
                   <a href="${fn:escapeXml(insUrl)}"><c:out value="${insurance.conditions}"/></a>
                </td>
<!--                 <td> -->
<%--                     <c:out value="${insurance.date}"/> --%>
<!--                 </td> -->
<!--                 <td> -->
<%--                 	<c:out value="${insurance.tag}"/> --%>
<!--                 </td> -->
            </tr>
           
        </c:forEach>
        </tbody>
    </table>
    <br/> 
</petclinic:layout>
