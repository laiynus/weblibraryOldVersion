<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href="/web-library/resources/css/web-library-styles.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<html>
<head>

</head>
<body>
	<div id="header">
		<%@ include file="../other/header.jsp"%>
	</div>
	<div class="container">
		<div class="row">
			<form:form class="form-horizontal" role="form" name="editForm"
				method="post" modelAttribute="user">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="panel panel-default">
					<div class="panel-heading">
						<p>
							<b>${user.login} </b>
						</p>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<p style="color: red"><spring:message code="label.greeting_admin"></spring:message></p>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_USER')">
							<p><spring:message code="label.greeting_user"></spring:message></p>
						</sec:authorize>
					</div>
					<div class="panel-body">
						<div class="row">
							<!-- left column -->
							<div class="col-md-4 col-sm-6 col-xs-12">
								<div class="text-center">
									<img
										src="${pageContext.request.contextPath}/resources/images/default-user-icon-profile.png"
										class="avatar img-circle img-thumbnail" alt="">
								</div>
							</div>


							<!-- edit form column -->
							<div class="col-md-8 col-sm-6 col-xs-12 personal-info">
								<c:if test="${not empty msg}">
									<div class="alert alert-success">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										${msg}
									</div>
								</c:if>
								<h3><spring:message code="label.personal_information"></spring:message></h3>

								<div class="form-group">
									<form:label class="col-md-3  control-label" path="login"
										for="login"><spring:message code="label.login"></spring:message>:</form:label>
									<div class="col-md-8">
										<form:input id="login" name="login" class="form-control"
											path="login" readonly="true" value="${user.login}"
											type="text" />
									</div>
								</div>
								<div class="form-group">
									<form:label class="col-lg-3 control-label" path="firstName"
										for="firstName"><spring:message code="label.first_name"></spring:message>:</form:label>
									<div class="col-lg-8">
										<form:input id="firstName" name="firstName" path="firstName"
											class="form-control" value="${user.firstName}" type="text" />
									</div>
								</div>
								<div class="form-group">
									<form:label class="col-lg-3 control-label" path="lastName"
										for="lastName"><spring:message code="label.last_name"></spring:message>:</form:label>
									<div class="col-lg-8">
										<form:input id="lastName" name="lastName" path="lastName"
											class="form-control" value="${user.lastName}" type="text" />
									</div>
								</div>
								<div class="form-group">
									<form:label class="col-lg-3 control-label" path="patronymic"
										for="patronymic"><spring:message code="label.patronymic"></spring:message>:</form:label>
									<div class="col-lg-8">
										<form:input id="patronymic" name="patronymic"
											path="patronymic" class="form-control"
											value="${user.patronymic}" type="text" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label" for="email"><spring:message code="label.email"></spring:message>:</label>
									<div class="col-lg-8">
										<input id="email" name="email" class="form-control"
											value="${user.email}" type="email" readonly>
									</div>
								</div>
								<div class="form-group">
									<form:label class="col-lg-3 control-label" path="faculty"
										for="faculty"><spring:message code="label.faculty"></spring:message>:</form:label>
									<div class="col-lg-8">
										<form:input id="faculty" name="faculty" path="faculty"
											class="form-control" value="${user.faculty}" type="text" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="chair" for="chair"
										class="col-lg-3 control-label"><spring:message code="label.chair"></spring:message>:</form:label>
									<div class="col-lg-8">
										<form:input id="chair" name="chair" path="chair"
											class="form-control" value="${user.chair}" type="text" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="specialty" for="specialty"
										class="col-lg-3 control-label"><spring:message code="label.specialty"></spring:message>:</form:label>
									<div class="col-lg-8">
										<form:input id="specialty" name="specialty" path="specialty"
											class="form-control" value="${user.specialty}" type="text" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label"></label>
									<div class="col-md-8">

										<button id="submit" name="submit" class="btn btn-primary"
											type="submit"><spring:message code="label.save"></spring:message></button>
										<button class="btn btn-default" type="reset"><spring:message code="label.cancel"></spring:message></button>
									<!--  	<a class="btn btn-warning" href="#changePassword" data-toggle="modal" type="button"><spring:message code="label.changepassword"></spring:message></a> -->
									</div>
								</div>

							</div>

						</div>

					</div>

				</div>
			</form:form>
		</div>
	</div>
	<div id="footer">
		<%@ include file="../other/footer.jsp"%>
	</div>
</body>
</html>