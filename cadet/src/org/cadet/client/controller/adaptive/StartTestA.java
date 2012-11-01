package org.cadet.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.util.model.DatabaseConnection;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class StartTestA
 */
@WebServlet("/Test/Adaptive/StartTestA")
public class StartTestA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartTestA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendError(403);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			
			//this code checks if the test is started in within the stipulated test duration. 
			
			HttpSession session =request.getSession();
			Integer testID=Integer.parseInt(request.getAttribute("testID").toString());
			DatabaseConnection dbConn=DatabaseConnection.getInstance();
			
		}catch(Exception e){
			
		}
	}

}
