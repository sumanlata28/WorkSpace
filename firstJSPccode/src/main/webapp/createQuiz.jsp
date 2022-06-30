<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="showAlert()">
	<input type="hidden" id="status" value="<%=request.getAttribute("status")  %>">
	<input type="hidden" id="quizid" value="<%=request.getAttribute("quizid")  %>">

<form method="post"  action="CreateQuiz">
<input type="hidden" id="Username" name="Username" value="jklklklj" >

<input type="text" name="pswd" id="pswd" placeholder="password if want to set">
<%for(int i=0;i<10;i++)
	{  out.print(i+1);%>
	<input type="text" name="ques<%=i%>" id="ques<%=i%>" placeholder='Question'><br>
	<input type="text" name="optionA<%=i%>" id="optionA<%=i%>" placeholder='option a' required><br>
	<input type="text" name="optionb<%=i%>" id="optionb<%=i%>" placeholder='option b' required><br>
	<input type="text" name="optionC<%=i%>" id="optionC<%=i%>" placeholder='option c' required><br>
	<input type="text" name="optionD<%=i%>" id="optionD<%=i%>" placeholder='option D' required><br>
	<input type="text" name="Right<%=i%>" id="Right<%=i%>" placeholder='right option' required><br><br>
	
	<%
	}%>
	
<input type="submit" value="submit">	
</form>
<script type="text/javascript" >
	function showAlert(){
		document.getElementById("Username").value =sessionStorage.getItem('username');
		var status=document.getElementById("status").value;
		if(status=="added")
		{
			alert("Quiz Created with id = "+document.getElementById("quizid").value);
			location.href="options.jsp";
		}
		if(status=="failed")
			alert("Not creaed");
		console.log(sessionStorage.getItem('username'));
	}
	
</script>

</body>
</html>
