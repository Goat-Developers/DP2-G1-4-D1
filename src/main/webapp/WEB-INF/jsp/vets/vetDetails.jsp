<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<petclinic:layout pageName="vets">

    <h2>Información del Veterinario</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${vet.firstName} ${vet.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Turnos</th>
            <td><c:out value="${vet.maxShifts}"/></td>
        </tr>
    </table>
 <sec:authorize access="hasAuthority('veterinarian')">
    <spring:url value="{vetId}/edit" var="editUrl">
        <spring:param name="vetId" value="${vet.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Información</a>
     <spring:url value="/vets" var="addUrl">
 
    </spring:url>
     <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Volver</a>
 </sec:authorize>
</petclinic:layout>
