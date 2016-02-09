<!DOCTYPE HTML>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:directive.page contentType="text/html; charset=UTF-8" />


<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />


<!-- Generic page styles -->
<link rel="stylesheet" href="resources/css/web-library-upload.css">
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="resources/js/jquery/jquery.ui.widget.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="resources/js/jquery/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="resources/js/jquery/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="resources/js/jquery/jquery.fileupload-process.js"></script>
<!-- The File Upload validation plugin -->
<script src="resources/js/jquery/jquery.fileupload-validate.js"></script>
<script src="resources/js/web-library-upload-js.js"></script>
<script
	src="https://cdn.datatables.net/1.10.7/js/jquery.dataTables.js"></script>
<script
	src="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<link rel="stylesheet"
	href="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css">

<link href="/web-library/resources/css/web-library-styles.css"
	rel="stylesheet">
<link href="/web-library/resources/css/web-library-upload-styles.css"
	rel="stylesheet">
<script src="resources/js/jquery/jquery.dataTables.editable.js"></script>
<script src="resources/js/jquery/jquery.jeditable.js"></script>
<style type="text/css">
table.table-striped tr.even.row_selected td {
	background-color: #87ADF6;
}

table.table-striped tr.odd.row_selected td {
	background-color: #87ADF6;
}
</style>

</head>
<body>

	<div id="header">
		<%@ include file="../../other/header.jsp"%>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-13 col-md-offset-14">


				<div class="panel panel-default">
					<div class="panel-heading">
						<p>Books</p>
					</div>
					<div class="panel-body">
					<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
						<!-- The fileinput-button span is used to style the file input field as button -->
						<span class="btn btn-success fileinput-button"> <i
							class="glyphicon glyphicon-plus"></i> <span>Add files...</span> <!-- The file input field used as target for the file upload widget -->
							<input id="fileupload" type="file" name="files[]" multiple>
						</span>
						<button id="btnDeleteRow" class="btn btn-danger btn-cons" value="cancel">Delete selected
								book</button>
						 <br> <br>
						<!-- The global progress bar -->
						<div id="progress" class="progress">
							<div class="progress-bar progress-bar-success"></div>
						</div>
						<!-- The container for the uploaded files -->
						<div id="files" class="files"></div>
						<br>

						<div style="width: 100%; padding: 20px">
							<table id="bookTable" class="table table-striped table-bordered"
								style="cellspacing: 0; width: 100%;">
								<thead>
									<tr>
										<th>ID</th>
										<th>Title</th>
										<th>Date publication</th>
										<th>File path</th>
										<th>File type</th>
										<th></th>
										<th></th>
									</tr>
								</thead>
							</table>

						</div>
					</div>
				</div>

			</div>
		</div>

	</div>



	<div id="footer">
		<%@ include file="../../other/footer.jsp"%>
	</div>

</body>
</html>
