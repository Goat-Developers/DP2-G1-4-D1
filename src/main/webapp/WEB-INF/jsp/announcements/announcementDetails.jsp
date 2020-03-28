<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:layout pageName="announcements">

 
 <jsp:body>
	<section>
	<span><c:out value="${announcement.date }" ></c:out></span>
	<h1><c:out value="${announcement.header}"/></h1>
	<hr>
	<div class="xd-container2"><c:out value="${announcement.body }" ></c:out></div>
           
            <p>#<c:out value="${announcement.tag }" ></c:out></p>
            <hr>
             <p>Created by <c:out value="${announcement.vet.firstName } ${announcement.vet.lastName }" ></c:out></p>
	</section>
 
 	<div class="contenedor">
          <form:form class="inLine2" modelAttribute="announcement" action="/announcements/like" method="post">
          <input type="hidden" name="id" value="${announcement.id}"/>
          <form:button type="submit"><img class="img-responsive" src="/resources/images/like.png" width="35"/> </form:button>
          
          </form:form> <p class="inLine" ><c:out value="${announcement.likes }" ></c:out></p>
          

     
     </div>
     <div class="contenedor">
    <a href="/announcements" class="btn btn-default">Volver</a>
     </div>
 </jsp:body>
</petclinic:layout>
