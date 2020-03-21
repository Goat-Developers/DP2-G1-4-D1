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
        	<th>Nombre del Seguro</th>
        	<th>Tipo de mascota</th>
            <th>Precio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${insurances_bases.insuranceBaseList}" var="insuranceBase">
            <tr>
            	<td>
            		<spring:url value="/insurancesbases/{insuranceBaseId}" var="insBaseUrl">
                    <spring:param name="insuranceBaseId" value="${insuranceBase.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(insBaseUrl)}"><c:out value="${insuranceBase.name}"/></a>
            	</td>	
            	<td>
                	<c:out value="${insuranceBase.petType}"/>
                </td>
                <td>
                	<c:out value="${insuranceBase.price} Euros"/>
                </td>
            </tr>
           
        </c:forEach>
        </tbody>
    </table>
    <br/> 
</petclinic:layout>
