<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">

    <h2>Owner Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${owner.firstName} ${owner.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Address</th>
            <td><c:out value="${owner.address}"/></td>
        </tr>
        <tr>
            <th>City</th>
            <td><c:out value="${owner.city}"/></td>
        </tr>
        <tr>
            <th>Telephone</th>
            <td><c:out value="${owner.telephone}"/></td>
        </tr>
    </table>

    <spring:url value="{ownerId}/edit" var="editUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Owner</a>

    <spring:url value="{ownerId}/pets/new" var="addUrl">
        <spring:param name="ownerId" value="${owner.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Add New Pet</a>

    <br/>
    <br/>
    <br/>
    <h2>Pets</h2>

    <table class="table table-striped">
        <c:forEach var="pet" items="${owner.pets}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Name</dt>
                        <dd><c:out value="${pet.name}"/></dd>
                        <dt>Birth Date</dt>
                        <dd><petclinic:localDate date="${pet.birthDate}" pattern="yyyy-MM-dd"/></dd>
                        <dt>Type</dt>
                        <dd><c:out value="${pet.type.name}"/></dd>
                    </dl>
                </td>
                <td valign="top">
                    <table class="table-condensed">
                        <thead>
                        <tr>
                            <th>Visit Date</th>
                            <th>Description</th>
                            <c:if test ="${ pet.insurance ==null}"> <th> Insurance </th></c:if>
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
                                <a href="${fn:escapeXml(petUrl)}">Edit Pet</a>
                            </td>
                            <td>
                                <spring:url value="/owners/{ownerId}/pets/{petId}/visits/new" var="visitUrl">
                                    <spring:param name="ownerId" value="${owner.id}"/>
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(visitUrl)}">Add Visit</a>
                            </td>
                            <c:if test ="${ pet.insurance ==null}">
                            <td>    
                                 <spring:url value="/insurance/new/{petId}" var="insuranceUrl">
                                    <spring:param name="petId" value="${pet.id}"/>
                                </spring:url>
                                <a href="${fn:escapeXml(insuranceUrl)}">Add Insurance</a>
                            </td>
                            </c:if>
                        </tr>
                    </table>
                </td>
            </tr>

        </c:forEach>
    </table>
 <c:if test="${numberIns >0 }">
<h2>Pets and Insurances</h2>
<table id="insurancesTable" class="table table-striped">
        <tr>
        	<th>Num</th>
            <th>Vaccines</th>
            <th>Treatment</th>
            <th>Conditions</th>
            <th>Total price</th>
            <th> Pet </th>
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
