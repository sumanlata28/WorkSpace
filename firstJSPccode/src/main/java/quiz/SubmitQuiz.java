package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DBConnection.DBHandler;

/**
 * Servlet implementation class SubmitQuiz
 */
@WebServlet("/checkQuiz")
public class SubmitQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**		
     * @see HttpServlet#HttpServlet()
     */
    public SubmitQuiz() {
        super();}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	String quizId= request.getParameter("QuizId");
    	RequestDispatcher dispatcher=null;
    	String name= request.getParameter("Username");
    	int a=new DBHandler().chechQuiz(quizId,name);
    	System.out.print(a);
		if(a==0)
		{
			dispatcher=request.getRequestDispatcher("options.jsp");
			request.setAttribute("statuss","Wrong");
		}
		else if(a==1)
		{
			dispatcher=request.getRequestDispatcher("options.jsp");
			request.setAttribute("quizId",quizId);
			request.setAttribute("statuss","succeed");
		}
		else if(a==2)
		{
			dispatcher=request.getRequestDispatcher("options.jsp");
			request.setAttribute("statuss","locked");
		
    	}else if(a==4)
		{
			request.setAttribute("quizId",quizId);
    		dispatcher=request.getRequestDispatcher("options.jsp");
			request.setAttribute("statuss","YourCreated");
		}
		else
		{
			dispatcher=request.getRequestDispatcher("options.jsp");
			request.setAttribute("statuss", "error");
		}
					
		dispatcher.forward(request, response);	
    	
    }

}