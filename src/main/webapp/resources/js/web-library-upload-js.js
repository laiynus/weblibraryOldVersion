
$(document)
		.ready(
				
				function() {
					$
							.ajaxSetup({
								scriptCharset : "utf-8",
								contentType : "application/x-www-form-urlencoded; charset=utf-8",
								cache : false
							});
					
					var i = 0;
					var j = 0;
					var table = $("#bookTable")
							.dataTable(
									
									{
										"bServerSide" : true,
										"sAjaxSource" : "http://localhost:8080/web-library/adminbooks/springPaginationDataTables.web",
										"bProcessing" : true,
										"sPaginationType" : "full_numbers",
										"bJQueryUI" : true,
										"aoColumns" : [ {
											"sName" : "id",
											"bSearchable" : false,
											"bSortable" : false,
											"bVisible" : false
										}, {
											"sName" : "title"
										}, {
											"sName" : "datePublication"
										}, {
											"sName" : "filePath"
										}, {
											"sName" : "fileType"
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
										"aoColumns" : [ null, null, null, null,null,null ],
										sDeleteHttpMethod : "GET",
										sDeleteURL : "http://localhost:8080/web-library/adminbooks/deleteBook",
										sDeleteRowButtonId : "btnDeleteRow",
										fnEndProcessingMode: function(){
											table.api().ajax.reload();
											document.getElementById("btnDeleteRow").disabled = true; 
										}
									});
					
					

					$(function() {
						'use strict';
						// Change this to the location of your server-side
						// upload handler:
						var url = 'http://localhost:8080/web-library/adminbooks/upload', uploadButton = $(
								'<button/>').addClass('btn btn-primary').prop(
								'disabled', true).text('Processing...').on(
								'click',
								function() {
									var $this = $(this), data = $this.data();
									$this.off('click').text('Abort').on(
											'click', function() {
												$this.remove();
												data.abort();
											});
									data.submit().always(function() {
										$this.remove();
									});
								});
						$('#fileupload')
								.fileupload({
									url : url,
									dataType : 'json',
									autoUpload : false,
									acceptFileTypes : /(\.|\/)(fb2|fb2.zip)$/i,
									maxFileSize : 10000000,
									previewCrop : true
								})
								.on(
										'fileuploadadd',
										function(e, data) {
											data.context = $('<div/>')
													.appendTo('#files');
											$.each(data.files, function(index,
													file) {
												var node = $('<p/>').append(
														$('<span/>').text(
																file.name));
												if (!index) {
													node.append('<br>').append(
															uploadButton.clone(
																	true).data(
																	data));
												}
												node.appendTo(data.context);
											});
										})
								.on(
										'fileuploadprocessalways',
										function(e, data) {
											var index = data.index, file = data.files[index], node = $(data.context
													.children()[index]);
											if (file.preview) {
												node.prepend('<br>').prepend(
														file.preview);
											}
											if (file.error) {
												node
														.append('<br>')
														.append(
																$(
																		'<span class="text-danger"/>')
																		.text(
																				file.error));
											}
											if (index + 1 === data.files.length) {
												data.context
														.find('button')
														.text('Upload')
														.prop(
																'disabled',
																!!data.files.error);
											}
										})
								.on(
										'fileuploadprogressall',
										function(e, data) {
											var progress = parseInt(data.loaded
													/ data.total * 100, 10);
											$('#progress .progress-bar').css(
													'width', progress + '%');
										})
								.on('fileuploaddone', function(e, data) {
									table.api().ajax.reload();
								})
								.on(
										'fileuploadfail',
										function(e, data) {
											$
													.each(
															data.files,
															function(index) {
																var error = $(
																		'<span class="text-danger"/>')
																		.text(
																				'File upload failed.');
																$(
																		data.context
																				.children()[index])
																		.append(
																				'<br>')
																		.append(
																				error);
															});
										}).prop('disabled',
										!$.support.fileInput).parent()
								.addClass(
										$.support.fileInput ? undefined
												: 'disabled');
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


