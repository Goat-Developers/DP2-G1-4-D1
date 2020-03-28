<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="treatment">
<jsp:attribute name="customScript">
    
    </jsp:attribute>
    <jsp:body>
    <h2>
        <c:if test="${treatment['new']}">Nuevo </c:if> Tratamiento
            </h2>
    <form:form modelAttribute="treatment" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Tipo de tratamiento" name="type"/>
            <petclinic:inputField label="Precio" name="price"/>
            <petclinic:inputField label="Información" name="description"/>
           <petclinic:selectField  label="Tipo " name="petType" names="${types}" size="5"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${treatment['new']}">
                        <button class="btn btn-default" type="submit">Crear tratamiento</button>
                    </c:when>
                    
                </c:choose>
                <spring:url value="/treatment" var="addUrl">
 
    </spring:url>
     <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Cancelar</a>
            </div>
        </div>	
    </form:form>
     </jsp:body>
</petclinic:layout>
