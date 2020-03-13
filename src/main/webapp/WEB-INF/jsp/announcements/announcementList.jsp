<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<petclinic:layout pageName="announcements">
    <h2>Announcements</h2>


	<form:form modelAttribute="announcement" action="/announcements/tag" method="get" class="form-horizontal"
               id="search-owner-form">
               <div class="form-group">
               <div class="control-group" id="tag">
                <label class="col-sm-2 control-label">Searching for Tag</label>
                <div class="col-sm-10">
	<form:input path="tag" />
	</div>
	</div>
	</div>
	</form:form>

	

	
    <table id="announcementsTable" class="table table-striped">
        <thead>
        <tr>
        	<th>Header</th>
            <th>Date</th>
            <th>Tag</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${announcements.announcementList}" var="announcement">
            <tr>
            	<td>
            		<spring:url value="/announcements/{announcementId}" var="annUrl">
                        <spring:param name="announcementId" value="${announcement.id}"/>
                    </spring:url>
                   <a href="${fn:escapeXml(annUrl)}"><c:out value="${announcement.header}"/></a>
                </td>
                <td>
                    <c:out value="${announcement.date}"/>
                </td>
                <td>
                	<c:out value="${announcement.tag}"/>
                </td>
            </tr>
           
        </c:forEach>
        </tbody>
    </table>
    <br/> 
    <a class="btn btn-default" href='<spring:url value="/announcements/old" htmlEscape="true"/>'>Show old announcements</a>
    <sec:authorize access="hasAuthority('veterinarian')">
		<a class="btn btn-default" href='<spring:url value="/announcement/new" htmlEscape="true"/>'>Create announcement</a>
	</sec:authorize>
</petclinic:layout>
