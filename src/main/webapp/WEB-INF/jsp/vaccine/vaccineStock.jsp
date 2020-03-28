<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="vaccine">
    <h2>Vacunas</h2>

    <table id="vaccineTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Stock</th>
         	
            <th></th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vaccine}" var="vaccine">
        
            <tr>
            <td>
                    <spring:url value="/vaccine/{vaccineId}" var="vaccineUrl">
                        <spring:param name="vaccineId" value="${vaccine.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(vaccineUrl)}"><c:out value="${vaccine.name}"/></a>
                </td>
               
                <td>
                    <c:out value="${vaccine.stock}"/>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <spring:url value="/vaccine" var="addUrl">
 
    </spring:url>
     <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Volver</a>
    
</petclinic:layout>
