<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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



<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.js"></script>
<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/additional-methods.js"></script>
<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/additional-methods.min.js"></script>



<script src="<c:url value="/resources/js/web-library-registration-js.js" />"></script>



<html>

<head>

</head>
<body>
	<div id="header">
		<%@ include file="../other/header.jsp"%>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-7">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-user"></span> <spring:message code="label.sign_up"></spring:message>
					</div>
					<div class="panel-body">
						<div id="errors"></div>
						<c:if test="${not empty error}">

							<div class="alert alert-danger fade in">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								${error}
							</div>

						</c:if>

						<form:form class="form-horizontal" role="form"
							id="registrationForm" name="registrationForm" method="post"
							modelAttribute="user">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class="form-group">
								<form:label path="login" for="login"
									class="col-sm-3 control-label"> <spring:message code="label.login"></spring:message></form:label>
								<div class="col-sm-9">
									<form:input type="text" class="form-control" id="login"
										placeholder="Login" name="login/>" path="login" />
								</div>
							</div>
							<div class="form-group">
								<form:label path="email" for="email"
									class="col-sm-3 control-label"> Email</form:label>
								<div class="col-sm-9">
									<form:input path="email" type="email" class="form-control"
										id="email" placeholder="Yor email" name="email" />
								</div>
							</div>
							<div class="form-group">
								<form:label path="password" for="password"
									class="col-sm-3 control-label">
									<spring:message code="label.password"></spring:message></form:label>
								<div class="col-sm-9">
									<form:input path="password" type="password"
										class="form-control" id="password" placeholder="Password"
										name="password" />
									<span class="help-block"><spring:message code="label.minlen"></spring:message></span>
								</div>
							</div>
							<div class="form-group">
								<label for="reenterPassword" class="col-sm-3 control-label">
									<spring:message code="label.password"></spring:message></label>
								<div class="col-sm-9">
									<input type="password" class="form-control"
										id="reenterPassword" placeholder="Please enter password again"
										name="reenterPassword">
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="form-group last">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" id="submit" name="submit"
										class="btn btn-success btn-sm"><spring:message code="label.sign_up"></spring:message></button>
									<button type="reset" class="btn btn-default btn-sm">
										<spring:message code="label.reset"></spring:message></button>
								</div>
							</div>
							<br>
							

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<%@ include file="../other/footer.jsp"%>
	</div>
</body>
</html>
