package LoginRegisterServletCode;

import DBConnection.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginRegisterFolder/login")
public class signIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public signIn() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String LoginMail = request.getParameter("loginmail");
		String LoginPwd = request.getParameter("loginPWD");
		RequestDispatcher dispatcher=null;
		int a = new DBHandler().checkLogin(LoginMail,LoginPwd);
		if(LoginMail.equals(""))
		{
			request.setAttribute("status","EmptyMail");
			dispatcher=request.getRequestDispatcher("loginRegister.jsp");
		}
		
		if(LoginPwd.equals(""))
		{
			request.setAttribute("status","EmptyPswd");
			dispatcher=request.getRequestDispatcher("loginRegister.jsp");
		}
		if(a==2)
		{
			request.setAttribute("status","succeeed");
			request.setAttribute("username",LoginMail);
			 dispatcher = request.getRequestDispatcher("loginRegister.jsp");
		}
		else
		{
			request.setAttribute("status","failed");
			dispatcher=request.getRequestDispatcher("loginRegister.jsp");			
		}
		dispatcher.forward(request, response);	
	}
}
