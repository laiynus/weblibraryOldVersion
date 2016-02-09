<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<script src="resources/js/web-library-lastbook-reader-js.js"></script>
<script src="resources/js/web-library-sendemail-js.js"></script>
<script src="resources/js/web-library-reader-js.js"></script>
<script src="resources/js/web-library-resetpassword-js.js"></script>

<script src="resources/js/web-library-changepassword-js.js"></script>
<html>

<head>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />

<title>Web-library</title>
<script type="text/javascript">
	function LogOut() {
		document.getElementById("logoutForm").submit();
	}
</script>
<style type="text/css">
#readerModal .modal-content {
	max-height: 800px;
	overflow: auto;
	height: 100%;
    border-radius: 0;
}

#readerModal .modal-dialog {
  width: 100%;
  height: 100%;
  padding: 0;
}
</style>
</head>
<body>



	<nav class="navbar navbar-default" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">

			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/home"><spring:message
					code="label.brand"></spring:message></a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<li><a href="#lastBookModal" onclick="getInfoLastBook()"
						data-toggle="modal"><spring:message code="label.last_book"></spring:message></a></li>
				</c:if>
				<sec:authorize	access="hasRole('ROLE_ADMIN')">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown"><spring:message code="label.pages"></spring:message>
						<b class="caret"></b></a>
						
						<ul class="dropdown-menu">
							<li><a href="${pageContext.request.contextPath}/adminbooks"><spring:message
										code="label.admin_books"></spring:message></a></li>
							<li><a href="${pageContext.request.contextPath}/users"><spring:message
										code="label.admin_users"></spring:message></a></li>
							<li class="divider"></li>
							<li><a href="${pageContext.request.contextPath}/books"><spring:message code="label.user_books"></spring:message></a></li>
						</ul>
					
				</li>	
				</sec:authorize>
				<sec:authorize	access="hasRole('ROLE_USER')">	
					<li><a href="${pageContext.request.contextPath}/books"><spring:message code="label.user_books"></spring:message></a></li>	
				</sec:authorize>
				<li><a href="#FAQ" data-toggle="modal"><spring:message code="label.faq"></spring:message></a></li>
				<li><a href="#About" data-toggle="modal"><spring:message code="label.about"></spring:message></a></li>
				<li><a href="#contactWithUs" data-toggle="modal"><spring:message
							code="label.contact"></spring:message></a></li>

			</ul>

			<!--<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control"
						placeholder="<spring:message code="label.search"></spring:message>">
				</div>
				<button type="submit" class="btn btn-default">
					<spring:message code="label.submit"></spring:message>
				</button>
			</form>-->



			<c:choose>
				<c:when test="${pageContext.request.userPrincipal.name == null}">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${pageContext.request.contextPath}/sign-up"><spring:message
									code="label.sign_up"></spring:message></a></li>
						<li><a href="${pageContext.request.contextPath}/sign-in"><spring:message
									code="label.sign_in"></spring:message></a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav navbar-right">

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><spring:message code="label.hello"></spring:message>,
								${pageContext.request.userPrincipal.name}<b class="caret"></b> </a>
							<ul class="dropdown-menu">
								<c:url value="/j_spring_security_logout" var="logoutUrl" />
								<li><a href="myprofile"><spring:message
											code="label.myprofile"></spring:message></a></li>
								<li><a href="mybookmarks"><spring:message
											code="label.mybookmarks"></spring:message></a></li>
								<li class="divider"></li>

								<li>
									<form class="form" role="form" accept-charset="UTF-8"
										action="${logoutUrl}" method="post" id="logoutForm">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
									</form> <a href="javascript:LogOut()"><spring:message
											code="label.log_out"></spring:message></a>
								</li>

							</ul></li>


					</ul>
				</c:otherwise>
			</c:choose>



		</div>
	</nav>
	<div class="modal fade" id="lastBookModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog panel-default">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<spring:message code="label.last_book"></spring:message>
					</h4>
				</div>
				<div class="modal-body" id="modal_body">
					<div id="error"></div>

					<div style="float: left;" id="ltitle">
						<b><spring:message code="label.title"></spring:message> </b>
					</div>
					<div id="title"></div>

					<div style="float: left;" id="llast_date_reading">
						<b><spring:message code="label.last_date_reading"></spring:message>
						</b>
					</div>
					<div id="lastDateReading"></div>

					<div style="float: left;" id="lpage">
						<b><spring:message code="label.page"></spring:message> </b>
					</div>
					<div id="page"></div>

					<div style="float: left;" id="lfiletype">
						<b><spring:message code="label.file_type"></spring:message> </b>
					</div>
					<div id="fileType"></div>

					<div style="float: left;" id="ldatepublication">
						<b><spring:message code="label.date_publication"></spring:message>
						</b>
					</div>
					<div id="datePublication"></div>

					<div style="float: left;" id="lannotation">
						<b><spring:message code="label.annotation"></spring:message> </b>
					</div>
					<div id="annotation"></div>
					<div id="idBook" style="display: none;"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#readerModal" data-dismiss="modal"
						onclick="getChapter()" id="readBookButton" name="readBookButton">Read
						book</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="About" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog panel-default">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<spring:message code="label.about"></spring:message>
					</h4>
				</div>
				<div class="modal-body" id="modal_body">
					<p><spring:message code="label.aboutmore"></spring:message></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="FAQ" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog panel-default">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<spring:message code="label.faq"></spring:message>
					</h4>
				</div>
				<div class="modal-body" id="modal_body">
					<p><b><spring:message code="label.question1"></spring:message></b></p>
					<p><spring:message code="label.answer1"></spring:message></p>
					<p><b><spring:message code="label.question2"></spring:message></b></p>
					<p><spring:message code="label.answer2"></spring:message></p>
					<p><b><spring:message code="label.question3"></spring:message></b></p>
					<p><spring:message code="label.answer3"></spring:message></p>
					<p><b><spring:message code="label.question4"></spring:message></b></p>
					<p><spring:message code="label.answer4"></spring:message></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	

	<div class="modal fade" id="readerModal" tabindex="-1" role="dialog"
		aria-labelledby="largeModal" aria-hidden="true">
		<div class="modal-dialog modal-lg panel-default">
			<div class="modal-content">
				<div class="modal-header">

					
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="saveBookMark()">&times;</button>
					<h4 class="modal-title" id="titleBook"></h4><div align="center"><select onchange="getChapterFromList(this.value)" class="form-control" name="chapterList" id="chapterList" style="width: 300px;">
					</select></div>
				</div>
				<div class="modal-body">
					<div id="titleChapter" align="center"></div>
					<div id="textBook"></div>
					<div id="idBookReader" style="display: none;"></div>
					<div id="pageReader" style="display: none;"></div>
					<!-- Controls -->
					<a class="left carousel-control" role="button"
						onclick="getPrevChapter()"> <span
						class="glyphicon glyphicon-chevron-left"></span>
					</a> <a class="right carousel-control" role="button"
						onclick="getNextChapter()"> <span
						class="glyphicon glyphicon-chevron-right"></span>
					</a>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						onclick="saveBookMark()">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div id="contactWithUs" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog container  panel-default">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel"><spring:message code="label.titleMailtoSupport"></spring:message></h3>
				</div>
				<div class="modal-body">
					<div class="alert alert-danger fade in" id="errorMail"
						style="display: none;">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
					</div>
					<div class="alert alert-success fade in" id="successEmail"
						style="display: none;">
						<button type="button" class="close" data-dismiss="alert"
							id="successEmailButton" style="display: none;">&times;</button>
					</div>
					<c:if test="${pageContext.request.userPrincipal.name == null}">
						<div class="form-group">
							<label><spring:message code="label.name"></spring:message></label><input class="form-control required"
								placeholder="<spring:message code="label.name"></spring:message>" data-placement="top" data-trigger="manual"
								data-content="Must be at least 3 characters long, and must only contain letters."
								type="text" name="name" id="name">
						</div>
					</c:if>
					<div class="form-group">
						<label><spring:message code="label.subject"></spring:message></label><input class="form-control required"
							placeholder="<spring:message code="label.subject"></spring:message>" data-placement="top" data-trigger="manual"
							data-content="Must be at least 3 characters long, and must only contain letters."
							type="text" name="subject" id="subject">
					</div>
					<div class="form-group">
						<label><spring:message code="label.message"></spring:message></label>
						<textarea class="form-control" placeholder="<spring:message code="label.messagemore"></spring:message>"
							data-placement="top" data-trigger="manual" name="message"
							id="message"></textarea>
					</div>
					<c:if test="${pageContext.request.userPrincipal.name == null}">
						<div class="form-group">
							<label>E-Mail</label><input class="form-control email"
								placeholder="<spring:message code="label.emailmore"></spring:message>"
								data-placement="top" data-trigger="manual"
								data-content="Must be a valid e-mail address (user@gmail.com)"
								type="text" name="email" id="email">
						</div>
					</c:if>
					<div class="form-group">
						<button type="submit" class="btn btn-success pull-right"
							onclick="sendEmailToSupport()"><spring:message code="label.send"></spring:message></button>
						<p class="help-block pull-left text-danger hide" id="form-error">&nbsp;
							The form is not valid.</p>
					</div>
					<br> <br>
				</div>
				<div class="modal-footer">
					<button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="label.cancel"></spring:message></button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="changePassword"  class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" data-toggle="validator">
		<div class="modal-dialog container  panel-default">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel"><spring:message code="label.changepassword"></spring:message></h3>
				</div>
				<div class="modal-body" id="changePasswordbody">
					<div class="alert alert-danger fade in" id="errorChangePassword"
						style="display: none;">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
					</div>
					<div class="alert alert-success fade in" id="successChangePassword"
						style="display: none;">
						<button type="button" class="close" data-dismiss="alert"
							id="successEmailButton" style="display: none;">&times;</button>
					</div>
					
						<div class="form-group">
							<label><spring:message code="label.oldpassword"></spring:message></label><input class="form-control required" required
								placeholder="<spring:message code="label.oldpassword"></spring:message>" data-placement="top" data-trigger="manual"
								type="password" name="oldpassword" id="oldpassword">
						</div>
						
						<div class="form-group">
							<label><spring:message code="label.newpassword"></spring:message></label><input class="form-control required" required data-minlength="6"
								placeholder="<spring:message code="label.newpassword"></spring:message>" data-placement="top" data-trigger="manual"
								type="password" name="newpassword" id="newpassword">
						</div>
						
						<div class="form-group">
							<label><spring:message code="label.reenterpassword"></spring:message></label><input data-match-error="Whoops, these don't match" class="form-control required" data-match="#newpassword"
								placeholder="<spring:message code="label.reenterpassword"></spring:message>" data-placement="top" data-trigger="manual"
								type="password" name="reenterpassword" id="reenterpassword">
						</div>
					
					
					<div class="form-group">
						<button type="submit" class="btn btn-success pull-right"
							><spring:message code="label.changepassword"></spring:message></button>
					</div>
					<br> <br>
				</div>
				<div class="modal-footer">
					<button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="label.cancel"></spring:message></button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="resetPassword" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog panel-default">
			<div class="modal-content">
				<div class="modal-header">
					
					
					<div class="modal-body" id="modal_body">
							<div class="text-center">
								<h3>
									<i class="fa fa-lock fa-4x"></i>
								</h3>
								<h2 class="text-center"><spring:message code="label.forgotpassword"></spring:message></h2>
								<p><spring:message code="label.resetpassword"></spring:message></p>
								<div class="panel-body">
									<div class="alert alert-danger fade in" id="errorMail"
										style="display: none;">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
									</div>
									<div class="alert alert-success fade in" id="successEmail"
										style="display: none;">
										<button type="button" class="close" data-dismiss="alert"
											id="successEmailButton" style="display: none;">&times;</button>
									</div>
									<form class="form">
										<fieldset>
											<div class="form-group">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-envelope color-blue"></i></span> <input
														id="emailInput" placeholder="email address"
														class="form-control" type="email"
														oninvalid="setCustomValidity('Please enter a valid email address!')"
														onchange="try{setCustomValidity('')}catch(e){}"
														required="true">
												</div>
											</div>
											<div class="form-group">
												<input class="btn btn-warning btn-block"
													value="<spring:message code="label.sendpassword"></spring:message>" type="submit" onclick="resetPasswod()">
											</div>
										</fieldset>
									</form>

								</div>
							</div>
						</div>
						<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="label.close"></spring:message></button>
				</div>
			</div>
		</div>
	</div>
	</div>

</body>
</html>
