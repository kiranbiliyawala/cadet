package org.cadet.util.controller;

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

import org.cadet.util.model.DatabaseConnection;
import org.cadet.util.model.ErrorLogging;
import org.cadet.util.model.UserControl;

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
		response.sendError(403,"No Get Request Allowed for this Page");
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
		session.setAttribute("user", username);
		try{
		if(UserControl.checkAdminTable(dbconnection, username, password)){
			if(!UserControl.CheckAdminVerification(dbconnection, username)){
				response.sendRedirect("EmailVerification");
				return;
			}
			session.setAttribute("user", username);
			session.setAttribute("AdminAuth", true);
			session.setAttribute("ClientAuth", false);
			session.setAttribute("homeurl","admin/dashboard");
			session.setAttribute("categoryId", "0");
			session.setAttribute("categoryName", "All Categories");
			response.sendRedirect("admin/dashboard");
			return;
		}else if(UserControl.checkClientTable(dbconnection, username, password)){
			if(!UserControl.CheckClientVerification(dbconnection, username)){
				response.sendRedirect("EmailVerification");
				return;
			}
			session.setAttribute("user", username);
			session.setAttribute("AdminAuth", false);
			session.setAttribute("ClientAuth", true);
			session.setAttribute("homeurl","client/dashboard");
			response.sendRedirect("client/dashboard");
			return;
		}else{
			session.setAttribute("AdminAuth", false);
			session.setAttribute("ClientAuth", false);
			request.setAttribute("AuthCheck", false);
			request.getRequestDispatcher("Login").forward(request, response);
			return;
		}
		}catch(SQLException e){
			ErrorLogging.getInstance().log(Level.SEVERE, e);
			response.sendError(500,new Date().toString()+" - Database Error Check error log for futher information");
		}
	}

}
