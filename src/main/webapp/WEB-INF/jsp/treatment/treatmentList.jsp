<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="treatment">
    <h2>Tratamiento</h2>

    <table id="treatmentTable" class="table table-striped">
        <thead>
        <tr>
            <th>Tipo</th>
                       
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${treatment}" var="treatment">
            <tr>
                
                 <td>
                    <spring:url value="/treatment/{treatmentId}" var="treatmentUrl">
                        <spring:param name="treatmentId" value="${treatment.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(treatmentUrl)}"><c:out value="${treatment.type}"/></a>
                </td>
                
                
     
                
               
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <sec:authorize access="hasAuthority('veterinarian')">
        <a class="btn btn-default" href='<spring:url value="/treatment/new" htmlEscape="true"/>'>Crear nuevo tratamiento</a>
    </sec:authorize>
    
</petclinic:layout>
