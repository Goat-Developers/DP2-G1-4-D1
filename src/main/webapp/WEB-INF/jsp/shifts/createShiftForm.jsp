<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap-2.2.2.min.js"></script>
        <script type="text/javascript" src="js/bootstrap-timepicker.min.js"></script>
<petclinic:layout pageName="vets">
    <h2>
        <c:if test="${shift['new']}">Nuevo </c:if> Turno
            </h2>
    <form:form modelAttribute="shift" class="form-horizontal" id="add-shift-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Hora" name="shiftDate"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${shift['new']}">
                        <button class="btn btn-default" type="submit">Crear vacuna</button>
                    </c:when>
                    
                </c:choose>
                 <spring:url value="/vaccine" var="addUrl">
 
    </spring:url>
     <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Cancelar</a>
            </div>
            
        </div>
        
    </form:form>
</petclinic:layout>
