<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<petclinic:layout pageName="vets">
    <h2>Turnos</h2>



    <table class="table table-striped">
		<tr>    
    		<c:forEach items ="${shifts}" var = "shift">
    			<td> 
    				<c:out value ="${shift.shiftDate}"/>
    			</td>	
    		</c:forEach>
    	</tr>
    </table>
     
     <h2>Turnos disponibles</h2>
     
     
    
     <table class="table table-striped">
		<tr>    
    		<c:forEach items ="${availableShifts}" var = "shift">
    			<td> 
    					<c:out value ="${shift.shiftDate}"/>
    					<spring:url value="/shifts/{shiftId}/new/{vetId}" var="vetUrl">
                        <spring:param name="vetId" value="${vet.id}"/>
                        <spring:param name="shiftId" value="${shift.id}"/>
                </spring:url>
                <br>
                <c:if test="${max > actual }">
                    <a href="${fn:escapeXml(vetUrl)}"><c:out value=" Añadir este turno"/></a>
                </c:if>
    			</td>	
    		</c:forEach>
    		
    	</tr>
    </table>
   
     
     
   </petclinic:layout>
