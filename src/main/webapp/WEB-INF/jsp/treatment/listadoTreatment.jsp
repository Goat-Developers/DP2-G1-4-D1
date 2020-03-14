<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="treatment">
    <h2>Treatment</h2>

    <table id="treatmentTable" class="table table-striped">
        <thead>
        <tr>
            <th>Tipo</th>
            <th>Tipo de Mascota</th>
            <th>Price</th>
            <th>Descripcion</th>
            
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${treatment}" var="treatment">
            <tr>
                <td>
                 <c:out value="${treatment.type}"/>
               
                </td>
                 <td>
                    <c:out value="${treatment.petType}"/>
                </td>
                <td>
                    <c:out value="${treatment.price}"/>
                </td>
                <td>
                    <c:out value="${treatment.description}"/>
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
