package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserDao;

@WebServlet("/userlist")
public class UserList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String uemail=request.getParameter("uemail");
		String upass=request.getParameter("upass");
//		HttpSession h=request.getSession();
//		String uemail=(String) h.getAttribute("uemail");
//		String upass =(String) h.getAttribute("upass");
		
		RequestDispatcher d=request.getRequestDispatcher("adminNav.html");
		d.include(request, response);
		
		out.print("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css'>");
		out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js'></script>");
		out.print("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js'></script>");
		
		
		out.print("<h1> Welcome "+uemail+"</h1>");
		out.print("<center><h1>User List</h1></center>");
		out.print("<br><br>");
		
		try {
			ArrayList<User> al = new UserDao().getAllUser();
			out.print("<table class='table table-bordered'>");
			out.print("<thead>");
			out.print("<tr><th>Uid</th><th>FirstName</th><th>LastName</th><th>Email</th><th>Gender</th><th>Action</th></tr>");
			out.print("</thead>");
			out.print("<tbody>");
			for(User u:al){
				out.print("<tr><td>"+u.getUid()+"</td><td>"+u.getFname()+"</td><td>"+u.getLname()+"</td><td>"+u.getUemail()+"</td><td>"+u.getUgender()+"</td><td>     <a href='deleteserv?id="+u.getUid()+"' class='btn btn-danger'>Detele</a> </td></tr>");
			}
			out.print("</tbody>");                                                                                                                                     //<a href='editserv?id="+u.getUid()+"' class='btn btn-primary'>Edit </a>                                                                                                                   
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
