package org.cadet.client.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.model.NonAdaptiveTest;
import org.cadet.util.model.DatabaseConnection;

/**
 * Servlet implementation class SubmitAnswerNA
 */
@WebServlet("/Test/NonAdaptive/SubmitTest")
public class SubmitTestNA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitTestNA() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(403);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		NonAdaptiveTest test;
		String username;
		Connection connection = DatabaseConnection.getInstance().getDbConnection();
		try{
			test = (NonAdaptiveTest) session.getAttribute("test");
			username = session.getAttribute("user").toString();
		}catch(Exception e){
			response.sendError(403);
			return;
		}
		
			try {
				test.process_Answers();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		session.setAttribute("test", null);
		session.setAttribute("testCategory", null);
		
			response.sendRedirect("TestComplete");
		
	}

}
