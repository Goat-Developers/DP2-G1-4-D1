<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">
	
				<sec:authorize access="!isAuthenticated()">
				<petclinic:menuItem active="${name eq 'home'}" url="/"
					title="Página de inicio">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Inicio</span>
				</petclinic:menuItem>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
				<petclinic:menuItem active="${name eq 'announcements'}" url="/announcements"
					title="Anuncios">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Anuncios</span>
				</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize  access="hasAuthority('owner')">
				<petclinic:menuItem active="${name eq 'owners'}" url="/owners/myInfo"
					title="Buscar mis datos">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Mi Información</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize  access="hasAuthority('worker')">
				<petclinic:menuItem active="${name eq 'owners'}" url="/owners/find"
					title="Buscar Propietarios">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Buscar Propietarios</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize  access="hasAuthority('veterinarian')">
				<petclinic:menuItem active="${name eq 'owners'}" url="/owners/find"
					title="Buscar Propietarios">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Buscar Propietarios</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize  access="hasAuthority('admin')">
				<petclinic:menuItem active="${name eq 'owners'}" url="/owners/find"
					title="Buscar Propietarios">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Buscar Propietarios</span>
				</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize access="hasAuthority('veterinarian')">
				<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
					title="Veterinarios">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Veterinarios</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('veterinarian')">
				<petclinic:menuItem active="${name eq 'vets'}" url="/vetSchedule"
					title="Horario">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Horario</span>
				</petclinic:menuItem>
				</sec:authorize>
				<sec:authorize access="hasAuthority('worker')">
				<petclinic:menuItem active="${name eq 'vets'}" url="/vetSchedule"
					title="Horario">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Horario</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<petclinic:menuItem active="${name eq 'insurances_bases'}" url="/insurancesbases"
					title="Seguros base">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Seguros base</span>
				</petclinic:menuItem>
					
				<sec:authorize access="!isAuthenticated()">
				<petclinic:menuItem active="${name eq 'error'}" url="/oups"
					title="trigger a RuntimeException to see how it is handled">
					<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
					<span>Error</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">
				<petclinic:menuItem active="${name eq 'vacunas'}" url="/vaccine"
					title="Vacunas">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Vacunas</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">
				<petclinic:menuItem active="${name eq 'tratamientos'}" url="/treatment"
					title="Tratamientos">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Tratamientos</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				

			</ul>




			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Iniciar Sesión</a></li>
					<li><a href="<c:url value="/users/new" />">Registrarse</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>Â 
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Cerrar Sesión</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
<!-- 							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="#" class="btn btn-primary btn-block">My Profile</a>
												<a href="#" class="btn btn-danger btn-block">Change
													Password</a>
											</p>
										</div>
									</div>
								</div>
							</li>
-->
						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>
