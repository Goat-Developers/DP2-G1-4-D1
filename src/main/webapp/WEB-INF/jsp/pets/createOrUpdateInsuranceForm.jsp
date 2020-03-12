<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#date").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${insurance['new']}">New </c:if>Insurance</h2>

  
        <form:form modelAttribute="insurance" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="insuranceDate" name="insuranceDate"/>
                <petclinic:menuItem label="insuranceBase" name="insuranceBase"/>
                <petclinic:menuItem label="vaccines" name="vaccines"/>
                <petclinic:menuItem label="treatments" name="treatments"/>
                
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="type" value="${pet.petType}"/>
                    <button class="btn btn-default" type="submit">Add Insurance</button>
                </div>
            </div>
        </form:form>

        <br/>
    </jsp:body>

</petclinic:layout>
