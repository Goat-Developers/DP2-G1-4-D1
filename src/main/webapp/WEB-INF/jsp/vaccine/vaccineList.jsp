<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vaccine">
    <h2>Vacunas</h2>

    <table id="vaccineTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Fecha de expiracion</th>
         
            
            
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
                    <c:out value="${vaccine.expiration}"/>
                </td>
               
                
  
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
