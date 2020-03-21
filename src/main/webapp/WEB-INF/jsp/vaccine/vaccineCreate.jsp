
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vaccine">
<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#expiration").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
    <h2>
        <c:if test="${vaccine['new']}">Nueva </c:if> Vacuna
            </h2>
    <form:form modelAttribute="vaccine" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="name"/>
            <petclinic:inputField label="Informacion" name="information"/>
            <petclinic:inputField label="Precio" name="price"/>
            <petclinic:inputField label="Proveedor" name="provider"/>
            <petclinic:inputField label="Fecha de caducidad" name="expiration"/>
            <petclinic:inputField label="Stock" name="stock"/>
            <petclinic:selectField  label="Tipo " name="petType" names="${types}" size="5"/>
            <petclinic:inputField label="Efectos secundarios" name="sideEffects"/>
             
                   
          
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${vaccine['new']}">
                        <button class="btn btn-default" type="submit">Crear vacuna</button>
                    </c:when>
                    
                </c:choose>
                 <spring:url value="/vaccine" var="addUrl">
 
    </spring:url>
     <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Cancelar</a>
            </div>
            
        </div>
        
    </form:form>
     </jsp:body>
     
</petclinic:layout>
