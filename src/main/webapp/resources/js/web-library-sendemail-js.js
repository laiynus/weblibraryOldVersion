function sendEmailToSupport(){
	 if(document.getElementById("name")!=null && document.getElementById("email")!=null){
		 var name = document.getElementById("name").value;
		 var email = document.getElementById("email").value;
		 var subject = document.getElementById("subject").value;
		 var message = document.getElementById("message").value;
		 var json = {"name" : name, "subject" : subject,"message" : message, "email" : email}; 
	 }else{
		 var subject = document.getElementById("subject").value;
		 var message = document.getElementById("message").value;
		 var json = {"subject" : subject,"message" : message};
	 }
	 
 
	
	 

 
 $.ajax({
  type: "POST",
  url: "http://localhost:8080/web-library/mail/sendEmailToSupport",
  dataType: 'text',
  contentType: 'application/json',
  mimeType: 'application/json',
  beforeSend:function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr(
				"content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
  },
  data: JSON.stringify(json),
  success: function() {
	  if((document.getElementById("email") == null)&&(document.getElementById("name") == null)){
		  document.getElementById("successEmail").style.display = 'block';
		  document.getElementById("successEmailButton").style.display = 'block';
		  document.getElementById("successEmail").innerHTML = "";
		  document.getElementById("subject").innerHTML = "";
		  document.getElementById("message").innerHTML = "";
		  document.getElementById("successEmail").innerHTML = "Your email was successfully sent";
	  }else{
		  document.getElementById("successEmail").style.display = 'block';
		  document.getElementById("successEmailButton").style.display = 'block';
		  document.getElementById("successEmail").innerHTML = "";
		  document.getElementById("subject").innerHTML = "";
		  document.getElementById("message").innerHTML = "";
		  document.getElementById("name").innerHTML = "";
		  document.getElementById("email").innerHTML = "";
		  document.getElementById("successEmail").innerHTML = "Your email was successfully sent";
	  }
	 
  },
  error:function(status,er) {      
	  document.getElementById("errorEmail").style.display = 'block';
	  document.getElementById("errorEmail").innerHTML = "";
	  document.getElementById("successEmail").innerHTML = "";
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

 
};