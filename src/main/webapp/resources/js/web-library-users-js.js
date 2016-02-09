$(document)
		.ready(
				function() {
					$
							.ajaxSetup({
								scriptCharset : "utf-8",
								contentType : "application/x-www-form-urlencoded; charset=utf-8",
								cache : false
							});

					var table = $("#userTable")
							.dataTable(
									{
										"bServerSide" : true,
										"sAjaxSource" : "http://localhost:8080/web-library/users/springPaginationDataTables.web",
										"bProcessing" : true,
										"sPaginationType" : "full_numbers",
										"bJQueryUI" : true,
										"aoColumns" : [ {
											"mDataProp" : "0"
										}, {
											"mDataProp" : "1"
										}, {
											"mDataProp" : "2"
										}, {
											"mDataProp" : "3"
										}, {
											"mDataProp" : "4"
										}, {
											"mDataProp" : "5"
										}, {
											"mDataProp" : "6"
										}, {
											"mDataProp" : "7"
										}, {
											"mDataProp" : "8"
										}, {
											"mDataProp" : "9"
										}]
									})
							.makeEditable(
									{
										sDeleteHttpMethod : "GET",
										sDeleteURL : "http://localhost:8080/web-library/users/deleteUser",
										sDeleteRowButtonId : "btnDeleteRow",
										fnEndProcessingMode : function() {
											table.api().ajax.reload();
											document
													.getElementById("btnDeleteRow").disabled = true;
										},
										"sUpdateURL": "http://localhost:8080/web-library/users/activateUser",
										"aoColumns" : [ null, null, null, null,
												null, null, null, null, null,
												true ]
									});

					

					$(function() {
						var token = $("meta[name='_csrf']").attr("content");
						var header = $("meta[name='_csrf_header']").attr(
								"content");
						$(document).ajaxSend(function(e, xhr, options) {
							xhr.setRequestHeader(header, token);
						});
					});

				});