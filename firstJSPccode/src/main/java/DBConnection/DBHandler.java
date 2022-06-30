package DBConnection;

import java.sql.*;
import oracle.jdbc.driver.OracleDriver;


public class DBHandler{
	/*public static void main(String args[]) {
	try {
			Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-LQENBF4:1521:XE", "SYSTEM", "Robin_16626");
			Statement stmt = connection.createStatement();
			stmt.execute("drop table quizQues" );

			stmt.execute("create table quizQues(\r\n"
					+ "quizId varchar(50) Primary key,question0 varchar(100),optionA0 varchar(100),optionB0 varchar(100),optionC0 varchar(100),optionD0 varchar(100),right0 varchar(100),\r\n"
					+ "question1 varchar(100),optionA1 varchar(100),optionB1 varchar(100),optionC1 varchar(100),optionD1 varchar(100),right1 varchar(100),\r\n"
					+ "question2 varchar(100),optionA2 varchar(100),optionB2 varchar(100),optionC2 varchar(100),optionD2 varchar(100),right2 varchar(100),\r\n"
					+ "question3 varchar(100),optionA3 varchar(100),optionB3 varchar(100),optionC3 varchar(100),optionD3 varchar(100),right3 varchar(100),\r\n"
					+ "question4 varchar(100),optionA4 varchar(100),optionB4 varchar(100),optionC4 varchar(100),optionD4 varchar(100),right4 varchar(100),\r\n"
					+ "question5 varchar(100),optionA5 varchar(100),optionB5 varchar(100),optionC5 varchar(100),optionD5 varchar(100),right5 varchar(100),\r\n"
					+ "question6 varchar(100),optionA6 varchar(100),optionB6 varchar(100),optionC6 varchar(100),optionD6 varchar(100),right6 varchar(100),\r\n"
					+ "question7 varchar(100),optionA7 varchar(100),optionB7 varchar(100),optionC7 varchar(100),optionD7 varchar(100),right7 varchar(100),\r\n"
					+ "question8 varchar(100),optionA8 varchar(100),optionB8 varchar(100),optionC8 varchar(100),optionD8 varchar(100),right8 varchar(100),\r\n"
					+ "question9 varchar(100),optionA9 varchar(100),optionB9 varchar(100),optionC9 varchar(100),optionD9 varchar(100),right9 varchar(100)\r\n"
					+ ")\r\n"
					+ "");
		//	stmt.execute("create table QuizInfo(quizId varchar(30),password varchar(20),name varchar(200))");
			//stmt.execute("insert into QuizInfo values('TrialQuiz','kkjjmmnn','hhii')");
	//		stmt.execute("insert into QuizInfo values('TrialQu','','hh')");

//			stmt.execute("create table loginQuiz(mailId varchar(30),password varchar(20),name varchar(20))");
	//		stmt.execute("insert into loginQuiz values('robinrk626@gmail.com','Robin_16626','Robin Kumar')");
			System.out.print("Table create....");
			connection.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	*/
	
	public ResultSet quizQues(String quizid)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-LQENBF4:1521:XE", "SYSTEM", "Robin_16626");
			Statement stmt = connection.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("select * from quizQues where quizId = '"+quizid+"'");
			return rs;
		}
		catch(Exception e)
		{
			ResultSet rss=null;
			System.out.print(e);
			return rss;
		}			
	}
	
	public int chechQuiz(String QuizId,String name)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-LQENBF4:1521:XE", "SYSTEM", "Robin_16626");
			Statement stmt = connection.createStatement();
			ResultSet rs;
			rs= stmt.executeQuery("select count(*) from QuizInfo where quizId='"+QuizId+"'");
			rs.next();
			if(rs.getInt(1)==0)
				return 0;
			rs = stmt.executeQuery("select * from QuizInfo where quizId = '"+QuizId+"'");
			rs.next();
			if(rs.getString(3).equals(name))
				return 4;
			if(rs.getString(2)==null)
				return 1;
			else 
				return 2;
		}
		catch(Exception e)
		{
			System.out.print(e);
			return 3;
		}
	}
	
	public int checkLogin(String mailId, String pwd)
	{	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-LQENBF4:1521:XE", "SYSTEM", "Robin_16626");
			Statement stmt = connection.createStatement();
			ResultSet rs;
			rs= stmt.executeQuery("select count(*) from loginQuiz where mailId='"+mailId+"'");
			rs.next();
			if(rs.getInt(1)==0)
				return 0;
			rs = stmt.executeQuery("select * from loginQuiz where mailId = '"+mailId+"'");
			rs.next();
			if(pwd.equals(rs.getString(2)))
				return 2;
			else
				return 1;
		}
		catch(Exception e)
		{
			System.out.print(e);
			return 3;
		}
	}
	
	public int CreateUser(String name, String mail, String pwd)
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-LQENBF4:1521:XE", "SYSTEM", "Robin_16626");
			Statement stmt = connection.createStatement();
			stmt.execute("insert into loginQuiz values('"+mail+"','"+pwd+"','"+name+"')");
			return 1;
		}
		catch(Exception e)
		{
			System.out.print(e);
			return 0;
		}
	}
	
	public int createQuiz(String password,String name,String Id,String ques[],String optionA[],String optionB[], String optionC[], String optionD[], String right[])
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-LQENBF4:1521:XE", "SYSTEM", "Robin_16626");
			Statement stmt = connection.createStatement();
			String query="";
			for(int i=0;i<10;i++)
			{
				query=query+"'"+ques[i]+"','"+optionA[i]+"','"+optionB[i]+"','"+optionC[i]+"','"+optionD[i]+"','"+right[i]+"'";
				if(i!=9)
					query=query+",";
			}
			stmt.execute("insert into QuizInfo values('"+Id+"','"+password+"','"+name+"')");
			stmt.execute("insert into quizQues values('"+Id+"',"+query+")");
			return 1;
		}
		catch(Exception e)
		{
			System.out.print(e);
			return 0;
		}
	}
}