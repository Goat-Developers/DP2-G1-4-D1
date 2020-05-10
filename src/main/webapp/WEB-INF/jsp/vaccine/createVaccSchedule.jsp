<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="vaccinationSchedule">
    
    <form:form modelAttribute="vaccinationSchedule" class="form-horizontal" id="add-vaccinationSchedule-form">
        <div class="form-group has-feedback">
            <petclinic:selectField label="Vacunas para el calendario" name="vaccines" size="" names="${vaccines}"></petclinic:selectField>
        </div>
        
             <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">

 				<sec:authorize access="hasAuthority('veterinarian')">
                <button class="btn btn-default" type="submit">Añadir Vacunas necesarias</button>
                  
                </sec:authorize>
                 </div>
                
        </div>
          
    </form:form>
    
   
</petclinic:layout>
