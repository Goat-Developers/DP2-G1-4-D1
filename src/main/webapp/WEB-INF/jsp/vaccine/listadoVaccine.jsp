<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vaccine">
    <h2>Vaccine</h2>

    <table id="vaccineTable" class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Tipo de Mascota</th>
            <th>Information</th>
            <th>Price</th>
            <th>Provider</th>
            <th>Expiration</th>
            <th>Stock</th>
            
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vaccine}" var="vaccine">
            <tr>
                <td>
                 <c:out value="${vaccine.name}"/>
               
                </td>
                 <td>
                    <c:out value="${vaccine.petType}"/>
                </td>
                <td>
                    <c:out value="${vaccine.information}"/>
                </td>
                <td>
                    <c:out value="${vaccine.price}"/>
                </td>
                <td>
                    <c:out value="${vaccine.provider}"/>
                </td>
                <td>
                    <c:out value="${vaccine.expiration}"/>
                </td>
                <td>
                <c:out value="${vaccine.stock}"/>
                   
                </td>
                
      
<!--
                <td> 
                    <c:out value="${owner.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${owner.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
