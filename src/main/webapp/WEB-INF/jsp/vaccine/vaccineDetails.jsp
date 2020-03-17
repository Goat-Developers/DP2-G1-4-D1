	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vaccine">

    <h2>Informacion de las vacunas</h2>


    <table class="table table-striped">
    <tr>
            <th>Nombre</th>
            <td><b><c:out value="${vaccine.name} "/></b></td>
        </tr>
        <tr>
            <th>Tipo de Mascota</th>
            <td><b><c:out value="${vaccine.petType} "/></b></td>
        </tr>
        <tr>
            <th>Informacion</th>
            <td><c:out value="${vaccine.information}"/></td>
        </tr>
        <tr>
            <th>Precio</th>
            <td><c:out value="${vaccine.price}"/></td>
        </tr>
        <tr>
            <th>Proveedor</th>
            <td><c:out value="${vaccine.provider}"/></td>
        </tr>
        <tr>
            <th>Stock</th>
            <td><c:out value="${vaccine.stock}"/></td>
        </tr>
    </table>
    
    <spring:url value="/vaccine" var="addUrl">
 
    </spring:url>
     <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Volver</a>
   
    

    
</petclinic:layout>
