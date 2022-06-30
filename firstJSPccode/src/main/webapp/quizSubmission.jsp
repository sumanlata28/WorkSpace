<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.sql.*,DBConnection.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quiz</title>
    <link rel="stylesheet" href="style.css">
</head>
<body >
<form name="form">
<input type="hidden" name="idd" id="idd" value="quiz13686">
    <input type="hidden" name="qui" id="qui" value="quiz">
    </form>
    <div id="container">
        <div id="start">Start Quiz!</div>
        <div id="quiz" style="display: none">
            <div id="question"></div>
            <div id="choices">
                <div class="choice" id="A" onclick="checkAnswer('A')"></div>
                <div class="choice" id="B" onclick="checkAnswer('B')"></div>
                <div class="choice" id="C" onclick="checkAnswer('C')"></div>
                <div class="choice" id="D" onclick="checkAnswer('D')"></div>
            </div>
            <div id="timer">
                <div id="counter"></div>
                <div id="btimeGauge"></div>
                <div id="timeGauge"></div>
            </div>
            <div id="progress"></div>
        </div>
        <div id="scoreContainer" style="display: none"></div>
    </div>
    <script type="text/javascript">
    const start = document.getElementById("start");
    const quiz = document.getElementById("quiz");
    const question = document.getElementById("question");
    const choiceA = document.getElementById("A");
    const choiceB = document.getElementById("B");
    const choiceC = document.getElementById("C");
    const choiceD = document.getElementById("D");
    const timeGauge = document.getElementById("timeGauge");
    const counter = document.getElementById("counter");
    const progress = document.getElementById("progress");
    const scoreDiv = document.getElementById("scoreContainer");
    document.getElementById("idd").value =sessionStorage.getItem('quizId');
	const iddd=sessionStorage.getItem('quizId');
	const quizz="quiz13686";	
	let questions = [];

	
    <% 
    try{
    	
    	String aa="quiz56058";
    	ResultSet rs=new DBHandler().quizQues(aa);
    	rs.next();
    	rs.getString(5);
    	for(int i=0;i<10;i++)
    	{%>
    		ques={question: "<%=rs.getString(i*6+2)%>",
    		        choiceA :" <%= rs.getString(i*6+3)%>",
    		        choiceB :" <%= rs.getString(i*6+4)%>",
    		        choiceC :" <%= rs.getString(i*6+5)%>",
    		        choiceD :" <%= rs.getString(i*6+6)%>",
    		        correct :"<%=rs.getString(i*6+7)%>"};
    				questions.push(ques);
    		        <%    		
    	}
    }
    catch(Exception e)
    {
    	System.out.print(e);
    }
    %>
      const lastQuestion = questions.length - 1;
    let runningQuestion = 0;
    let count = 0;
    const questionTime = 30; // 5min
    const gaugeWidth = 150; // 150px
    const gaugeUnit = gaugeWidth / questionTime;
    let TIMER;
    let score = 0;

    // render a question
    function renderQuestion(){
        let q = questions[runningQuestion];

        question.innerHTML = "<p>"+ q.question +"</p>";
        choiceA.innerHTML = q.choiceA;
        choiceB.innerHTML = q.choiceB;
        choiceC.innerHTML = q.choiceC;
        choiceD.innerHTML = q.choiceD;
    }

    start.addEventListener("click",startQuiz);

    // start quiz
    function startQuiz(){
        start.style.display = "none";
        renderQuestion();
        quiz.style.display = "block";
        renderProgress();
        renderCounter();
        TIMER = setInterval(renderCounter,1000); // 1000ms = 1s
    }

    // render progress
    function renderProgress(){
        for(let qIndex = 0; qIndex <= lastQuestion; qIndex++){
            progress.innerHTML += "<div class='prog' id="+ qIndex +"></div>";
        }
    }

    // counter render

    function renderCounter(){
        if(count <= questionTime){
            counter.innerHTML = count;
            timeGauge.style.width = count * gaugeUnit + "px";
            count++
        }else{
            count = 0;
            // change progress color to red
            answerIsWrong();
            if(runningQuestion < lastQuestion){
                runningQuestion++;
                renderQuestion();
            }else{
                // end the quiz and show the score
                clearInterval(TIMER);
                scoreRender();
            }
        }
    }

    // checkAnwer

    function checkAnswer(answer){
        if( answer == questions[runningQuestion].correct){
            // answer is correct
            score++;
            // change progress color to green
            answerIsCorrect();
        }else{
            // answer is wrong
            // change progress color to red
            answerIsWrong();
        }
        count = 0;
        if(runningQuestion < lastQuestion){
            runningQuestion++;
            renderQuestion();
        }else{
            // end the quiz and show the score
            clearInterval(TIMER);
            scoreRender();
        }
    }

    // answer is correct
    function answerIsCorrect(){
        document.getElementById(runningQuestion).style.backgroundColor = "#0f0";
    }

    // answer is Wrong
    function answerIsWrong(){
        document.getElementById(runningQuestion).style.backgroundColor = "#f00";
    }


    function scoreRender(){
        scoreDiv.style.display = "block";

        // calculate the amount of question percent answered by the user
        const scorePerCent = Math.round(100 * score/questions.length);

        // choose the image based on the scorePerCent
        let img = (scorePerCent >= 80) ? "img/5.png" :
                  (scorePerCent >= 60) ? "img/4.png" :
                  (scorePerCent >= 40) ? "img/3.png" :
                  (scorePerCent >= 20) ? "img/2.png" :
                  "img/1.png";

        scoreDiv.innerHTML = "<img src="+ img +">";
        scoreDiv.innerHTML += "<p>"+ scorePerCent +"%</p>";
    }
    </script>
</body>
</html>