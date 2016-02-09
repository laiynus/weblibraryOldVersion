$(document).ready(
	function() {
		$.ajaxSetup({
				scriptCharset : "utf-8",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				cache : false
		});
		
		var i = 0;
		var j = 0;
		var table = $("#userBookmarkTable").dataTable(
						{
								"bServerSide" : true,
								"sAjaxSource" : "http://localhost:8080/web-library/mybookmarks/springPaginationDataTables.web",
								"bProcessing" : true,
								"sPaginationType" : "full_numbers",
								"bJQueryUI" : true,
								"aoColumns" : [
										{
											"sName" : "idbook",
											"bSearchable" : false,
											"bSortable" : false,
										    "bVisible" : false
										},
								        {		"sName" : "id",
												"bSearchable" : false,
												"bSortable" : false,
											    "bVisible" : false
										}, {
											"sName" : "title"
										}, {
											"sName" : "lastName"
										},{
											"sName" : "name"
										},{
											"sName" : "datePublication"
										}, {
											"sName" : "lastDateReading"
										}, {
											"sName" : "page"
										}, {
										      "mData": null,
										      "bSortable": false,
										      "bSearchable" : false,
											  "bSortable" : false,
										      "mRender": function() {
										    	var temp =  table.api().row(i).data()  
										    	if(temp === undefined)
										    		i=0;
										    	var id = table.api().row(i).data()+"";
										    	var str = id.split(",");
										    	i++;
										        return '<a class="btn btn-primary btn-sm"  href="http://localhost:8080/web-library/adminbooks/downloadBook?id=' + str[0] +'">' + 'Download' + '</a>';
										      }
										},
										{
										      "mData": null,
										      "bSortable": false,
										      "bSearchable" : false,
											  "bSortable" : false,
										      "mRender": function() {
										    	var temp =  table.api().row(j).data()  
										    	if(temp === undefined)
										    		j=0;
										    	var id = table.api().row(j).data() + "";
										    	var str = id.split(",");
										    	j++;
										        return '<button class="btn btn-success btn-sm type="button" data-toggle="modal" data-target="#readerModal" data-dismiss="modal"" id="'+ str[0] + '" value"' + str[0] +'" onclick="getChapterBook(this.id)">' + 'Read book' + '</button>';
										      }
										}]
									})
							.makeEditable(
									{
										"aoColumns" : [ null, null, null, null ],
										sDeleteHttpMethod : "GET",
										sDeleteURL : "http://localhost:8080/web-library/mybookmarks/deleteBookMark",
										sDeleteRowButtonId : "btnDeleteRow",
										fnEndProcessingMode: function(){
											table.api().ajax.reload();
											document.getElementById("btnDeleteRow").disabled = true; 
										}
									});
					
					

					

	$(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});

});
