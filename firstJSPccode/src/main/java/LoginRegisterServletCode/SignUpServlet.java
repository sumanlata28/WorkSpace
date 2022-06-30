package LoginRegisterServletCode;
import DBConnection.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/loginRegisterFolder/Register")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignUpServlet() {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String name = request.getParameter("userName");
    	String mail = request.getParameter("userMail");
    	String pwd = request.getParameter("userPwd");
    	RequestDispatcher dispatcher=null;
    	int a= new DBHandler().checkLogin(mail,""); 
    	if(a==0)
    	{
	    	a=new DBHandler().CreateUser(name, mail, pwd);
	    	if(a==1)
	    	{
	    		request.setAttribute("username",mail);
	    		request.setAttribute("status","succeeedRegister");
	    	}
	    	else
	    		request.setAttribute("status","serverError");

    	}
    	else
			request.setAttribute("status","Allready");
		dispatcher = request.getRequestDispatcher("loginRegister.jsp");
		dispatcher.forward(request, response);
	}	

}
