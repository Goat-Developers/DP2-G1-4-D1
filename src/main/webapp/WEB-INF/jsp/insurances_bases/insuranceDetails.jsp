<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="insurances">

	<section>
	<c:forEach items="${insurance.vaccines}" var="insurance">
	<c:out value="${insurance.name}" ></c:out>
	</c:forEach>
<%-- 	<h1><c:out value="${announcement.header}"/></h1> --%>
<!-- 	<hr> -->
<%-- 	<div class="xd-container2"><c:out value="${announcement.body }" ></c:out></div> --%>
           
<%--             <p>#<c:out value="${announcement.tag }" ></c:out></p> --%>
<!--             <hr> -->
<%--              <p>Created by <c:out value="${announcement.vet.firstName } ${announcement.vet.lastName }" ></c:out></p> --%>
	</section>

</petclinic:layout>
