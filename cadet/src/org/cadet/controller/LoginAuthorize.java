package org.cadet.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.model.UserControl;
import org.cadet.model.DatabaseConnection;
import org.cadet.model.ErrorLogging;

/**
 * Servlet implementation class LoginAuthorize
 */
@WebServlet("/LoginAuthorize")
public class LoginAuthorize extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAuthorize() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404,"No Get Request Allowed for this Page");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseConnection dbconn = DatabaseConnection.getInstance();
		Connection dbconnection = dbconn.getDbConnection();
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try{
		if(UserControl.checkAdminTable(dbconnection, username, password)){
			if(!UserControl.CheckAdminVerification(dbconnection, username)){
				response.sendRedirect("EmailVerification");
			}
			session.setAttribute("user", username);
			session.setAttribute("AdminAuth", true);
			session.setAttribute("ClientAuth", false);
			response.sendRedirect("admin/dashboard");
		}else if(UserControl.checkClientTable(dbconnection, username, password)){
			if(!UserControl.CheckClientVerification(dbconnection, username)){
				response.sendRedirect("EmailVerification");
			}
			session.setAttribute("user", username);
			session.setAttribute("AdminAuth", false);
			session.setAttribute("ClientAuth", true);
			response.sendRedirect("client/dashboard");
		}else{
			session.setAttribute("AdminAuth", false);
			session.setAttribute("ClientAuth", false);
			response.sendRedirect("Login.jsp");
		}
		}catch(SQLException e){
			ErrorLogging.getInstance().log(Level.SEVERE, e);
			response.sendError(500,new Date().toString()+" - Database Error Check error log for futher information");
		}
	}

}
