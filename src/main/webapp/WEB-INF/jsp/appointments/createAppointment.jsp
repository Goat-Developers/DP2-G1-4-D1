<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="insurances">
<jsp:attribute name="customScript">

        <script>
        $(function () {
            $("#appointmentDate").datepicker({dateFormat: 'yy/mm/dd' ,
            	beforeShowDay: $.datepicker.noWeekends, 
            	monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
            	dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
            	firstDay: 1
            	});
        });
        </script>
    </jsp:attribute>
    <jsp:body>
    <h2>
        <c:if test="${appointment['new']}">Nueva </c:if> Cita
    </h2>
	<form:form modelAttribute="appointment" class="form-horizontal" id="add-appointment-form">
		<petclinic:inputField label="Fecha de la cita" name="appointmentDate"/>
		<petclinic:selectField label="Hora de la cita" name="appointmentTime" size="1" names="${times}"></petclinic:selectField>
		<petclinic:inputField label="Motivo" name="reason"/>
		
		<petclinic:selectField size="3" label="Vacuna" name="vaccine"  names="${vaccines}"/>
		<petclinic:selectField size="3" label="Tratamiento" name="treatment" names="${treatments}"/>
		<input type="hidden" name="pet" value="${pet}"/>
		
       
    	<div class="form=group">
    		<div class="col-sm-offset-2 col-sm-10">
    			<button class="btn btn-default" type="submit">Pedir Cita</button>
    			
    		</div>
    	</div>
    </form:form>
     </jsp:body>
</petclinic:layout>
