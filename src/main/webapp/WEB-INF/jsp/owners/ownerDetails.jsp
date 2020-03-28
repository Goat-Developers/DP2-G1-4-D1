<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<petclinic:layout pageName="owners">

    <h2>Información del Propietario</h2>


    <table class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><b><c:out value="${owner.firstName} ${owner.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Dirección</th>
            <td><c:out value="${owner.address}"/></td>
        </tr>
        <tr>
            <th>Ciudad</th>
            <td><c:out value="${owner.city}"/></td>
        </tr>
        <tr>
            <th>Teléfono</th>
            <td><c:out value="${owner.telephone}"/></td>
        </tr>
    </table>
 <sec:authorize access="hasAuthority('owner')">
    <spring:url value="{ownerId}/edit" var="editUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Editar Información</a>

    <spring:url value="{ownerId}/pets/new" var="addUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Añadir nueva mascota</a>
</sec:authorize>
    <br/>
    <br/>
    <br/>
    <h2>Mis Mascotas</h2>

    <table class="table table-striped">
        <c:forEach var="pet" items="${owner.pets}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Nombre</dt>
                        <dd><c:out value="${pet.name}"/></dd>
                        <dt>Fecha de nacimiento</dt>
                        <dd><petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/></dd>
                        <dt>Tipo</dt>
                        <dd><c:out value="${pet.type.name}"/></dd>
                    </dl>
                </td>
                <td valign="top">
                <sec:authorize access="hasAuthority('owner')">
                    <table class="table-condensed">
                        <thead>
                        <tr>
                            <th>Fecha de la visita</th>
                            <th>Descripción</th>
                            <c:if test ="${ pet.insurance ==null}"> <th> Seguro </th></c:if>
                        </tr>
                        </thead>
                        <c:forEach var="visit" items="${pet.visits}">
                            <tr>
                                <td><petclinic:localDate date="${visit.date}" pattern="yyyy-MM-dd"/></td>
                                <td><c:out value="${visit.description}"/></td>
                            </tr>
                             
                        </c:forEach>
                        
                        <tr>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/edit" var="petUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(petUrl)}">Editar Mascota</a>
                            </td>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/visits/new" var="visitUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(visitUrl)}">Añadir Visita</a>
                            </td>
                            <c:if test ="${ pet.insurance ==null}">
                            
                            <td>    
                                 <spring:url value="/insurance/new/{petId}" var="insuranceUrl">
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(insuranceUrl)}">Añadir Seguro</a>
                            </td>
                            
                            </c:if>
                        </tr>
                  
                    </table>
                          </sec:authorize>
                </td>
            </tr>

        </c:forEach>
    </table>
 <c:if test="${numberIns >0 }">
<h2>Mis Seguros</h2>
<table id="insurancesTable" class="table table-striped">
        <tr>
        	<th>Número</th>
            <th>Vacunas</th>
            <th>Tratamientos</th>
            <th>Condiciones</th>
            <th>Precio Total</th>
            <th> Mascota </th>
        </tr>
        <tbody>
        <c:forEach items="${owner.pets}" var="pets">
        	<c:if test="${pets.insurance!=null}"> 
            <tr>
            	<td>
            		<spring:url value="/insurances/{insuranceId}" var="insUrl">
                    <spring:param name="insuranceId" value="${pets.insurance.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(insUrl)}"><c:out value="${pets.insurance.id}"/></a>
            	</td>
	            <td>
	            	<c:forEach items="${pets.insurance.vaccines}" var="vaccine">
	            		<c:out value="${vaccine.name}"/><br/>
	            	</c:forEach>
	            </td>
                <td>
                	<c:forEach items="${pets.insurance.treatments}" var="treatment">
	            		<c:out value="${treatment.description}"/><br/>
	            	</c:forEach>
                </td>
                <td>
                    <c:out value="${pets.insurance.insuranceBase.conditions}"/>
                </td>
                <td>
                	<c:out value="${pets.insurance.insurancePrice} Euros"/>
                </td>
    			<td><c:out value="${pets.name}"/></td>             
            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    </c:if>
</petclinic:layout>
