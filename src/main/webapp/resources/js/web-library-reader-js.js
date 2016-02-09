function getChapter() {
    if(document.getElementById("idBook")!=null && document.getElementById("page")!=null){
		var idBook = document.getElementById("idBook").innerHTML;
		var page = document.getElementById("page").innerHTML;
		var json = {"idBook" : idBook,"page" : page};
    }else{
    	var idBook = document.getElementById("idBook").innerHTML;
    }
	$.ajax({ 
	    url: "http://localhost:8080/web-library/readerBook/getChapter", 
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
	    	document.getElementById("titleBook").innerHTML = data.title;
	    	document.getElementById("titleChapter").innerHTML = "<b>" + data.listChapters[data.currentChapter.number-1].name + "</b>";
	    	document.getElementById("textBook").innerHTML = data.currentChapter.textChapter;
	    	document.getElementById("idBookReader").innerHTML = data.idBook;
	     	document.getElementById("pageReader").innerHTML =  data.currentChapter.number;
	     	var objSel = document.getElementById("chapterList");
	     	for(var i=0;i<data.listChapters.length;i++){
	     		var opt = document.createElement('option');
	     		opt.innerHTML = data.listChapters[i].name;
	     		opt.value = data.listChapters[i].number;
	     		objSel.appendChild(opt);
	     		objSel.selectedIndex = data.currentChapter.number-1;
	     	}
	    },
	    error:function(data,status,er) { 
	    	
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

function getChapterBook(value) {
		var idBook = value;
		var json = {"idBook" : idBook};
      
	$.ajax({ 
	    url: "http://localhost:8080/web-library/readerBook/getChapter", 
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
	    	document.getElementById("titleBook").innerHTML = data.title;
	    	document.getElementById("titleChapter").innerHTML = "<b>" + data.listChapters[data.currentChapter.number-1].name + "</b>";
	    	document.getElementById("textBook").innerHTML = data.currentChapter.textChapter;
	    	document.getElementById("idBookReader").innerHTML = data.idBook;
	     	document.getElementById("pageReader").innerHTML =  data.currentChapter.number;
	     	var objSel = document.getElementById("chapterList");
	     	for(var i=0;i<data.listChapters.length;i++){
	     		var opt = document.createElement('option');
	     		opt.innerHTML = data.listChapters[i].name;
	     		opt.value = data.listChapters[i].number;
	     		objSel.appendChild(opt);
	     		objSel.selectedIndex = data.currentChapter.number-1;
	     	}
	    },
	    error:function(data,status,er) { 
	    	
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

function getNextChapter() {
	var idBook = document.getElementById("idBookReader").innerHTML;
	var page = document.getElementById("pageReader").innerHTML;
	page = (page*1) + 1;
	var json = {"idBook" : idBook,"page" : page};
	
	$.ajax({ 
	    url: "http://localhost:8080/web-library/readerBook/getNextPrevChapter", 
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
	    	document.getElementById("titleBook").innerHTML = data.title;
	    	document.getElementById("titleChapter").innerHTML = "<b>" + data.listChapters[data.currentChapter.number-1].name + "</b>";
	    	document.getElementById("textBook").innerHTML = data.currentChapter.textChapter;
	    	document.getElementById("idBookReader").innerHTML = data.idBook;
	     	document.getElementById("pageReader").innerHTML =  data.currentChapter.number;
	     	var objSel = document.getElementById("chapterList");
	     	objSel.selectedIndex = data.currentChapter.number-1;
	    },
	    error:function(data,status,er) { 
	    	
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

function getPrevChapter() {
	var idBook = document.getElementById("idBookReader").innerHTML;
	var page = document.getElementById("pageReader").innerHTML;
	page = (page*1) - 1;
	var json = {"idBook" : idBook,"page" : page};
	
	$.ajax({ 
	    url: "http://localhost:8080/web-library/readerBook/getNextPrevChapter", 
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
	    	document.getElementById("titleBook").innerHTML = data.title;
	    	document.getElementById("titleChapter").innerHTML = "<b>" + data.listChapters[data.currentChapter.number-1].name + "</b>";
	    	document.getElementById("textBook").innerHTML = data.currentChapter.textChapter;
	    	document.getElementById("idBookReader").innerHTML = data.idBook;
	     	document.getElementById("pageReader").innerHTML =  data.currentChapter.number;
	     	var objSel = document.getElementById("chapterList");
	     	objSel.selectedIndex = data.currentChapter.number-1;
	    },
	    error:function(data,status,er) { 
	    	
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

function getChapterFromList(value) {
	var idBook = document.getElementById("idBookReader").innerHTML;
	
	var json = {"idBook" : idBook,"page" : value};
	
	$.ajax({ 
	    url: "http://localhost:8080/web-library/readerBook/getNextPrevChapter", 
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
	    	document.getElementById("titleBook").innerHTML = data.title;
	    	document.getElementById("titleChapter").innerHTML = "<b>" + data.listChapters[data.currentChapter.number-1].name + "</b>";
	    	document.getElementById("textBook").innerHTML = data.currentChapter.textChapter;
	    	document.getElementById("idBookReader").innerHTML = data.idBook;
	     	document.getElementById("pageReader").innerHTML =  data.currentChapter.number;
	     	var objSel = document.getElementById("chapterList");
	     	objSel.selectedIndex = data.currentChapter.number-1;
	    },
	    error:function(data,status,er) { 
	    	
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

function saveBookMark() {
	var idBook = document.getElementById("idBookReader").innerHTML;
	var page = document.getElementById("pageReader").innerHTML;
	var json = {"idBook" : idBook,"page" : page};
	
	$.ajax({ 
	    url: "http://localhost:8080/web-library/readerBook/saveBookMark", 
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
