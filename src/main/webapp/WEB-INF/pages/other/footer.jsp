<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<style>
/* Sticky footer styles
-------------------------------------------------- */
html {
	position: relative;
	min-height: 100%;
}

body {
	/* Margin bottom by footer height */
	margin-bottom: 60px;
}

.footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	/* Set the fixed height of the footer here */
	height: 60px;
	background-color: #f5f5f5;
}
</style>
<footer class="footer">
	<div class="container">

		<div class="dropup" style="float: right; margin-top: 15px; margin-right: 30px">
			<button class="btn btn-default dropdown-toggle" type="button"
				id="menu1" data-toggle="dropdown">
				<spring:message code="label.language"></spring:message> <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="?lang=en"><spring:message code="label.english"></spring:message></a></li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="?lang=ru"><spring:message code="label.russian"></spring:message></a></li>
			</ul>
		</div>

		<p class="text-muted" style="margin-top: 15px">
			<spring:message code="label.brand"></spring:message>
			© <a href="mailto:laiynus@gmail.com"><spring:message
					code="label.author"></spring:message></a>
		</p>
		
	</div>
</footer>