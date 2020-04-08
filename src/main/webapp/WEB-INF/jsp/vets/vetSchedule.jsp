<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


    <script>
function hasAppointment() {
	var td = document.getElementsByTagName("td");
	td[td.length-1].style.backgroundColor ="#0cfe00";
	
}

function hasNotAppointment(){
	
	var td = document.getElementsByTagName("td");	
	td[td.length-1].style.backgroundColor ="#f6948d";
		
}

function changeStyleTd(){
	var td = document.getElementsByTagName("td");
	for(var i=0; i<td.length; i++){
		td[i].style.border ="2px solid";	
	}
	var table = document.getElementsByTagName("table");
	table[0].style.textAlign="center";
	var head = document.getElementsByTagName("th");
	for(var i=0; i<head.length; i++){
		head[i].style.textAlign="center";
	}
	
	
}
</script>


    
    
<petclinic:layout pageName="vet schedule">

	<jsp:body>
    	<h2>Horario del veterinario</h2>
		<table class="table table-striped">
		
			<thead>
			<c:forEach items ="${dias}" var ="dia">
				<th> <c:out value ="${dia}"/></th>
			</c:forEach>
   			</thead>
   			<tr>
   			<c:if test ="${z==1}">
   				<td> </td>
   				<td> </td>
   				<td> </td>
   				<td> </td>
   			</c:if>
   			<c:if test ="${z==2}">
   				<td> </td>
   				<td> </td>
   				<td> </td>
   			</c:if>
   			<c:if test ="${z==3}">
   				<td> </td>
   				<td> </td>
   			</c:if>
   			<c:if test ="${z==4}">
   				<td> </td>
   			
   			</c:if>
   			<c:if test ="${z==5}">
   				
   			
   			</c:if>
   			
   			<c:forEach items="${firstWeek}" var ="primer">
   				
   				<td>
   				<spring:url value="/vetSchedule/{day}" var="dayUrl">
                        <spring:param name="day" value="${primer}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(dayUrl)}"> <c:out value ="${primer.getDayOfMonth()}"/></a>
   				</td>
   				
   			<c:if test="${coincidencias.contains(primer)}">
   				<script lang="javascript">
   					hasAppointment();
   				</script>
   			</c:if>
   			
   			<c:if test ="${!coincidencias.contains(primer) }">
   			
   				<script lang="javascript">
   					hasNotAppointment();
   				</script>
   			
   			</c:if>
   			
   			</c:forEach>
 			
   			</tr>
   			
   			
   			<tr>
   				<c:forEach items="${semana1}" var ="fecha1">
   				<td>
   				<spring:url value="/vetSchedule/{day}" var="dayUrl">
                        <spring:param name="day" value="${fecha1}"/>
                </spring:url>
   					 <a href="${fn:escapeXml(dayUrl)}"><c:out value ="${fecha1.getDayOfMonth()}"/></a>
   			
   				</td>
   				
   			<c:if test="${coincidencias.contains(fecha1)}">
   				<script lang="javascript">
   					hasAppointment();
   				</script>
   			</c:if>
   			<c:if test ="${!coincidencias.contains(fecha1) }">
   			
   				<script lang="javascript">
   					hasNotAppointment();
   				</script>
   			
   			</c:if>
   			
   			
   			
   				</c:forEach>
   			</tr>
   			<tr>
   				<c:forEach items="${semana2}" var ="fecha2">
   				<td>
   				<spring:url value="/vetSchedule/{day}" var="dayUrl">
                        <spring:param name="day" value="${fecha2}"/>
                </spring:url>
   					 <a href="${fn:escapeXml(dayUrl)}"><c:out value ="${fecha2.getDayOfMonth()}"/></a>
   			
   				</td>
   				
   				<c:if test="${coincidencias.contains(fecha2)}">
   				<script lang="javascript">
   					hasAppointment();
   				</script>
   			</c:if>
   			
   			<c:if test ="${!coincidencias.contains(fecha2) }">
   			
   				<script lang="javascript">
   					hasNotAppointment();
   				</script>
   			
   			</c:if>
   			
   			
   				</c:forEach>
   			</tr>
   			<tr>
   				<c:forEach items="${semana3}" var ="fecha3">
   				<td>
   				<spring:url value="/vetSchedule/{day}" var="dayUrl">
                        <spring:param name="day" value="${fecha3}"/>
                </spring:url>
   					 <a href="${fn:escapeXml(dayUrl)}"><c:out value ="${fecha3.getDayOfMonth()}"/></a>
   			
   				</td>
   				
   				<c:if test="${coincidencias.contains(fecha3)}">
   				<script lang="javascript">
   					hasAppointment();
   				</script>
   			</c:if>
   			
   			<c:if test ="${!coincidencias.contains(fecha3) }">
   			
   				<script lang="javascript">
   					hasNotAppointment();
   				</script>
   			
   			</c:if>
   			
   			
   				</c:forEach>
   			</tr>	
   			<tr>
   				<c:forEach items="${semana4}" var ="fecha4">
   				<td>
   				
   				<spring:url value="/vetSchedule/{day}" var="dayUrl">
                        <spring:param name="day" value="${fecha4}"/>
                </spring:url>
                   
   					 <a href="${fn:escapeXml(dayUrl)}"><c:out value ="${fecha4.getDayOfMonth()}"/></a>
   			
   				</td>
   				
   				<c:if test="${coincidencias.contains(fecha4)}">
   				<script lang="javascript">
   					hasAppointment();
   				</script>
   			</c:if>
   			
   			<c:if test ="${!coincidencias.contains(fecha4) }">
   			
   				<script lang="javascript">
   					hasNotAppointment();
   				</script>
   			
   			</c:if>
   			
   			
   				</c:forEach>
   			</tr>	
   			<tr>
   				<c:forEach items="${semana5}" var ="fecha5">
   				<td>
   				
   				<spring:url value="/vetSchedule/{day}" var="dayUrl">
                        <spring:param name="day" value="${fecha5}"/>
                </spring:url>
                    
   					 <a href="${fn:escapeXml(dayUrl)}"><c:out value ="${fecha5.getDayOfMonth()}"/></a>
   			
   				</td>
   				
   				<c:if test="${coincidencias.contains(fecha5)}">
   				<script lang="javascript">
   					hasAppointment();
   				</script>
   			</c:if>
   			
   			<c:if test ="${!coincidencias.contains(fecha5) }">
   			
   				<script lang="javascript">
   					hasNotAppointment();
   				</script>
   			
   			</c:if>
   			
   			
   			
   				</c:forEach>
   			</tr>	
   			
   			
   			
   		</table>
			<script lang ="javascript">
   				changeStyleTd();
   			</script>
	</jsp:body>
</petclinic:layout>






