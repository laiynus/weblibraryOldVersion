$(document).ready(function(){
$("#registrationForm").validate({
	 rules: {
		    login:{
		    	 required: true,
		    	 minlength: 4,
		    },
		    email: {
		      required: true,
		      email: true
		    },
		    password:{ 
		    	 required: true,
		    	 minlength: 6
		    },
		    reenterPassword: {
		        equalTo: "#password"
		      }
		  },
		  messages: {
		    login: { 
		    	 required: "Please specify your login",
		    	 minlength: "Minimum length of your login 4 symbols "
		    },
		    email: {
		      required: "We need your email address to contact you",
		      email: "Your email address must be in the format of name@domain.com"
		    }, 
		    password:{ 
		    	 required:  "Please enter password",
		    	 minlength: "Minimum length of your password 6 symbols"
		    },
		    reenterPassword: {
		        equalTo: "Passwords must match"
		    }
		  },
		  errorLabelContainer:  $("#errorChangePassword")
		
		 
});
});

