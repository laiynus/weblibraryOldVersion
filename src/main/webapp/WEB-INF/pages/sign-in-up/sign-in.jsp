<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
<body onload='document.loginForm.login.focus();'>
	<div id="header">
		<%@ include file="../other/header.jsp"%>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-7">
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="glyphicon glyphicon-lock"></span> <spring:message code="label.sign_in"></spring:message>
					</div>
					<div class="panel-body">
						<c:if test="${not empty error}">
							<div class="alert alert-danger fade in">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								${error}
							</div>
						</c:if>
						<c:if test="${not empty msg}">
							<div class="alert alert-success fade in">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								${msg}
							</div>
						</c:if>
						<c:url value="/j_spring_security_check" var="loginUrl" />
						<form class="form-horizontal" role="form" name="loginForm"
							action="${loginUrl}" method='POST'>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class="form-group">
								<label for="login" class="col-sm-3 control-label"> <spring:message code="label.login"></spring:message></label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="login"
										placeholder="<spring:message code="label.login"></spring:message>" required name="login">
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-sm-3 control-label">
									<spring:message code="label.password"></spring:message></label>
								<div class="col-sm-9">
									<input type="password" class="form-control" id="password"
										placeholder="<spring:message code="label.password"></spring:message>" required name="password">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									<div class="checkbox">
										<label> <input type="checkbox" name="remember-me" /> <spring:message code="label.remember_me"></spring:message>
										</label>
									</div>
								</div>
							</div>
							<div class="form-group last">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-success btn-sm">
										<spring:message code="label.sign_in"></spring:message></button>
									<a href="#resetPassword" data-toggle="modal" type="button" class="btn btn-danger btn-sm">
										<spring:message code="label.forgotpassword"></spring:message></a>	
								</div>
							</div>

						</form>
					</div>
					<div class="panel-footer">
						<spring:message code="label.notregistred"></spring:message> <a href="${pageContext.request.contextPath}/sign-up"><spring:message code="label.registredhere"></spring:message></a>
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