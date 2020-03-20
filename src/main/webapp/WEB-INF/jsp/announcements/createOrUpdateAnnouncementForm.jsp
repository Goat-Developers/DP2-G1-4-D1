<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="owners">
    <h2>
        <c:if test="${announcement['new']}">New </c:if> Announcement
    </h2>
   
    <form:form modelAttribute="announcement" class="form-horizontal" id="add-announcement-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Header" name="header"/>
            <petclinic:inputField label="Body" name="body"/>
            <petclinic:inputField label="Tag" name="tag"/>
        </div>
        
             <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">

 				<sec:authorize access="hasAuthority('veterinarian')">
                <button class="btn btn-default" type="submit">Add Announcement</button>
                   <a href="/announcements" class="btn btn-default">Cancel</a>
                 </div>
                 </sec:authorize>
        </div>
          
    </form:form>
    
   
</petclinic:layout>
