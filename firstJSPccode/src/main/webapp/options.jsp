<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="showAlert()">
<input type="hidden" id="statuss" value="<%=request.getAttribute("statuss")  %>">

<a href="createQuiz.jsp">create A Quiz</a>
<br><br><br>
	
<form method="post" action="checkQuiz">
	<input type="hidden" id="Username" name="Username" value="" >
	<input type="text" placeholder="Enter Quiz id To Give" name="QuizId" id="QuizId"><br><br>	
	<input type="submit" value="submit">
</form>


<script type="text/javascript">
	
 	function showAlert(){
 		document.getElementById("Username").value =sessionStorage.getItem('username');
 		var statuss=document.getElementById("statuss").value;
		if(statuss=="Wrong")
			alert("Wrong QuizId");
		if(statuss=="error")
			alert("WronizId");
		if(statuss=="succeed")
		{
			sessionStorage.setItem("QuizId","<%=request.getAttribute("quizId")  %>");	
			location.href="quizSubmission.jsp",{name:"quiz13686"};
					
		}
		if(statuss=="locked")
			alert("Lockedd");
		if(statuss=="YourCreated")
		{
			sessionStorage.setItem("QuizId","<%=request.getAttribute("quizId")  %>");	
			location.href="quizSubmission.jsp",{name:"quiz13686"};
					
		}	
 	}console.log(sessionStorage.getItem('QuizId'));
 	
	</script>

</body>
</html>