package quiz;

import java.io.IOException;
import DBConnection.*;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class createQuiz
 */
@WebServlet("/CreateQuiz")
public class createQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public createQuiz() {
        super();}

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String ques[]= new String[11];
    	String optionA[]= new String[11];
    	String optionB[]= new String[11];
    	String optionC[]= new String[11];
    	String optionD[]= new String[11];
    	String right[]= new String[11];
    	String pswd= request.getParameter("pswd");
    	String name= request.getParameter("Username");
    	RequestDispatcher dispatcher=null;
    	for(int i=0;i<10;i++)
    	{
    		ques[i] = request.getParameter("ques"+i);
    		optionA[i] = request.getParameter("optionA"+i);
    		optionB[i] = request.getParameter("optionb"+i);
    		optionC[i] = request.getParameter("optionC"+i);              
    		optionD[i] = request.getParameter("optionD"+i);
    		right[i] = request.getParameter("Right"+i);
    	}
    	System.out.print(name);
    	String quizId="quiz"+Integer.toString(new Random().nextInt(100000));
    	if(new DBHandler().createQuiz(pswd,name,quizId,ques, optionA, optionB, optionC, optionD, right)==1)
    	{
    		request.setAttribute("status", "added");
    		request.setAttribute("quizid", quizId);
    	} 
    	else
    		request.setAttribute("status", "failed");
		dispatcher=request.getRequestDispatcher("createQuiz.jsp");			
		dispatcher.forward(request, response);	
    }
}
