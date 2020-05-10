<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="announcements">
    <h2>Anuncios</h2>

	<form:form modelAttribute="announcement" action="/announcements/tag" method="get" class="form-horizontal" id="search-owner-form">
    	<div class="form-group">
        	<div class="control-group" id="tag">
            	<label class="col-sm-2 control-label">Buscar por Tag</label>
                <div class="col-sm-10">
					<form:input path="tag" />
					<form:button class="btn-default">Buscar</form:button>
				</div>
			</div>
		</div>
		
	</form:form>
	
    <table id="announcementsTable" class="table table-striped">
        <thead>
        
        <tr>
        	<th>Cabecera</th>
            <th>Fecha</th>
            <th>Tag</th>
            <th>Likes</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
        <c:when test="${vacio}"><td> No se han encontrado resultados</td>
        	<td> </td>
        	<td> </td>
        	<td> </td>
        </c:when>
        <c:otherwise>
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
                <td>
                	<c:out value="${announcement.likes}"/>
                </td>
            </tr>
           
        </c:forEach>
        </c:otherwise>
        </c:choose>
        </tbody>
    </table>
    <br/> 
    <a class="btn btn-default" href='<spring:url value="/announcements/old" htmlEscape="true"/>'>Anuncios Antiguos</a>
     <a href="/announcements" class="btn btn-default">Anuncios Actuales</a>
    <sec:authorize access="hasAuthority('veterinarian')">
		<a class="btn btn-default" href='<spring:url value="/announcement/new" htmlEscape="true"/>'>Crear Anuncio</a>
	</sec:authorize>
</petclinic:layout>
