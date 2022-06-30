<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
		pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title> Online Quiz</title>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css'><link rel="stylesheet" href="./style.css">

</head>
<body onload="showAlert()">
<!-- partial:index.partial.html -->
<input type="hidden" id="status" value="<%=request.getAttribute("status")  %>">
<input type="hidden" id="namee" value="<%=request.getAttribute("namee")  %>">

<h2>Online Quiz System: Login/Sign-up</h2>
<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form action="Register" method="post">
			<h1>Create Account</h1>
			<input type="text" id="userName" name="userName" placeholder="Name" required/>
			<input type="email" id="userMail" name="userMail" placeholder="Email" required/>
			<input type="password" id="userPwd" name="userPwd" placeholder="Password" required/>
			<button>Sign Up</button>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form action="login" method="post">
			<h1>Sign in</h1>
			<input type="email" id="loginmail" name="loginmail" placeholder="Email" required/>
			<input type="password" id="loginPWD" name="loginPWD" placeholder="Password" required/>
			
			<input type="submit" id="submit" value="Login">
		</form>
	</div> 
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Welcome Back!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signIn">Sign In</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Hello, Friend!</h1>
				<p>Enter your personal details and start journey with us</p>
				<button class="ghost" id="signUp">Sign Up</button>
			</div>
		</div>
	</div>
</div>
<!-- partial -->
  <script  src="./script.js"></script>
<script type="text/javascript">
 	function showAlert(){
 		var status=document.getElementById("status").value;
 		var username=document.getElementById("namee").value;
		if(status=="failed"){
			alert("UserName And Password does not match");
		}
		if(status=="EmptyMail"){
			alert("Enter a Username");
		} 	
		if(status=="EmptyPswd"){
			alert("Enter Password");
		}
		if(status=="EmptyPswd"){
			alert("Enter Password");
		} 	
		if(status=="Allready"){
			alert("Mail is Allready Registered");
		} 	
		if(status=="EmptyPswd"){
			alert("Enter Password");
		} 	
		
		if(status=="succeeed")
		{	
			sessionStorage.setItem("username","<%=request.getAttribute("username")  %>");
			location.href="../options.jsp";
		}
		if(status=="succeeedRegister")
		{	
			sessionStorage.setItem("username","<%=request.getAttribute("username")  %>");
			location.href="../options.jsp";
		}
 	}
 	
	</script>
</body>
</html>
