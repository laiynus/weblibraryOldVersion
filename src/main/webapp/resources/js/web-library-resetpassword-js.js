function resetPasswod(){
	
	var email = document.getElementById("emailInput").value;
	var json = {"email" : email};
	
	$.ajax({ 
	    url: "http://localhost:8080/web-library/resetpassword/sendToEmailNewPassword", 
	    type: 'POST', 
	    dataType: 'json', 
	    contentType: 'application/json; charset=utf-8',
	    mimeType: 'application/json; charset=utf-8',
	    data: JSON.stringify(json),
	    beforeSend:function() {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr(
					"content");
			$(document).ajaxSend(function(e, xhr, options) {
				xhr.setRequestHeader(header, token);
			});
	    },
	    success: function(data) {
	    	 document.getElementById("successEmail").innerHTML = data;
	    },
	    error:function(data,status,er) { 
	    	 document.getElementById("errorEmail").innerHTML = er;
	    }
	})
	
	$(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr(
				"content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	})
}