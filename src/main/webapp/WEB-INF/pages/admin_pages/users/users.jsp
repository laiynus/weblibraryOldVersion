<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link href="/web-library/resources/css/web-library-styles.css"
	rel="stylesheet">



<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<script src="https://cdn.datatables.net/1.10.7/js/jquery.dataTables.js"></script>
<script
	src="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<link rel="stylesheet"
	href="//cdn.datatables.net/plug-ins/1.10.7/integration/bootstrap/3/dataTables.bootstrap.css">

<script src="resources/js/jquery/jquery.dataTables.editable.js"></script>
<script src="resources/js/jquery/jquery.jeditable.js"></script>

<script src="resources/js/web-library-users-js.js"></script>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />

<style type="text/css">
table.table-striped tr.even.row_selected td {
	background-color: #87ADF6;
}

table.table-striped tr.odd.row_selected td {
	background-color: #87ADF6;
}

input{
    width: 50px;
}

</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id="header">
		<%@ include file="../../other/header.jsp"%>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-13 col-md-offset-14">
			<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

				<div class="panel panel-default">
					<div class="panel-heading">
						<p>Users</p>
					</div>
					<div class="panel-body">
					
						<button id="btnDeleteRow" class="btn btn-danger btn-cons"
							value="cancel">Delete selected user</button>

						<div style="width: 100%; padding: 20px">

							<table id="userTable" class="table table-striped table-bordered"
								style="cellspacing: 0; width: 100%;">
								
								<thead>
									<tr>

										<th>Login</th>
										<th>Email</th>
										<th>Date Registration</th>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Patronymic</th>
										<th>Faculty</th>
										<th>Chair</th>
										<th>Specialty</th>
										<th>Enabled</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
<div id="footer">
	<%@ include file="../../other/footer.jsp"%>
</div>
</html>