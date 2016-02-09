
function getInfoLastBook() {
	 	
	$.ajax({ 
	    url: "http://localhost:8080/web-library/lastBook/getLastBook", 
	    type: 'GET', 
	    dataType: 'json', 
	    contentType: 'application/json',
	    mimeType: 'application/json',
	    success: function(data) {
	    		document.getElementById("idBook").innerHTML = '';
	    	   	document.getElementById("title").innerHTML = '';
		    	document.getElementById("lastDateReading").innerHTML = '';
		    	document.getElementById("page").innerHTML = '';
		    	document.getElementById("fileType").innerHTML = '';
		    	document.getElementById("datePublication").innerHTML = '';
		    	document.getElementById("annotation").innerHTML = '';
		    	document.getElementById("title").innerHTML =  data.title;
		    	document.getElementById("lastDateReading").innerHTML =  data.lastDateReading;
		       	document.getElementById("page").innerHTML =  data.currentChapter.number;
		    	document.getElementById("fileType").innerHTML =  data.format;
		    	document.getElementById("datePublication").innerHTML =  data.publisher.dateOfPublish;
		    	document.getElementById("annotation").innerHTML =  data.annotation;
		    	document.getElementById("idBook").innerHTML = data.idBook;
		    	
	    },
	    error:function(data,status,er) { 
	    	if(er=="SyntaxError: Unexpected end of input"){
	    		document.getElementById("idBook").innerHTML = '';
	    		document.getElementById("title").innerHTML = '';
		    	document.getElementById("lastDateReading").innerHTML = '';
		    	document.getElementById("page").innerHTML = '';
		    	document.getElementById("fileType").innerHTML = '';
		    	document.getElementById("datePublication").innerHTML = '';
		    	document.getElementById("annotation").innerHTML = '';
		    	document.getElementById("ltitle").innerHTML = '';
		    	document.getElementById("llast_date_reading").innerHTML = '';
		    	document.getElementById("lpage").innerHTML = '';
		    	document.getElementById("lfiletype").innerHTML = '';
		    	document.getElementById("ldatepublication").innerHTML = '';
		    	document.getElementById("lannotation").innerHTML = '';
		    	document.getElementById("error").innerHTML = "You haven't bookmarks!";
		    	document.getElementById("readBookButton").style.display = 'none';
	    	}else{
		    	document.getElementById("ltitle").innerHTML = '';
		    	document.getElementById("llast_date_reading").innerHTML = '';
		    	document.getElementById("lpage").innerHTML = '';
		    	document.getElementById("lfiletype").innerHTML = '';
		    	document.getElementById("ldatepublication").innerHTML = '';
		    	document.getElementById("lannotation").innerHTML = '';
		    	document.getElementById("error").innerHTML = er;
	    	}
	    }
	});
	
	$(function() {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr(
				"content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	})
	
	
};




