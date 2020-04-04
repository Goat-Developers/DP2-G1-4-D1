<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="vet schedule">
    <h2>Veterinarios</h2>



    <table id="vetsTable" class="table table-striped">
        <thead>
        <tr>
            <th>Horario </th>
        </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${vetSchedule.shifts}" var="shift">
            <tr>
                <td>
              	 <c:out value="${shft.picked}"/>
                </td>
               
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/vets.xml" htmlEscape="true" />">Ver como XML</a>
            </td>            
        </tr>
    </table>
</petclinic:layout>
