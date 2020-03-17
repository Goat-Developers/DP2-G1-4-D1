<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="insurances">
    <h2>
        <c:if test="${insurance['new']}">New </c:if> Insurance
    </h2>
	<form:form modelAttribute="insurance" class="form-horizontal" id="add-insurance-form">
        <div class="form-group has-feedback">
           	<petclinic:selectField name ="insuranceBase" label ="Seguro base" names ="${insurancebase}" size = "5"/>
            <petclinic:selectField name="vaccines" label="Vacunas" names="${insurance.vaccines}" size="5"/> 
     		<petclinic:selectField name="treatments" label="Tratamientos" names="${treatments}" size="5"/> 
        </div>
    	<div class="form=group">
    		<div class="col-sm-offset-2 col-sm-10">
    			<button class="btn btn-default" type="submit">Create Insurance</button>
    		</div>
    	</div>
    </form:form>
</petclinic:layout>
