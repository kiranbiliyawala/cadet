package org.cadet.client.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.model.NonAdaptiveTest;
import org.cadet.util.model.DatabaseConnection;

/**
 * Servlet implementation class CreateTest
 */
@WebServlet("/client/Test/NonAdaptive/CreateTest")
public class CreateTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process_request(request,response);
	}

	private void process_request(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		Connection connection = DatabaseConnection.getInstance().getDbConnection();
		String username = "";
		String testid_str;
		int testid=0;
		try{
			username = (String) session.getAttribute("user");
			username.toString();
			testid_str = request.getParameter("testid");
			testid = Integer.parseInt(testid_str);
			
		}catch(Exception e){
			response.sendError(403);
			return;
		}
		
	
		try{
		if(NonAdaptiveTest.isAllowed(testid, username)){
		NonAdaptiveTest test = new NonAdaptiveTest(connection, testid, username);
		session.setAttribute("test", test);
		
		session.setAttribute("test_name", test.test_details[0]);
		request.setAttribute("TestName", test.test_details[0]);
		request.setAttribute("TestTime", test.test_details[2]);
		
		}else{
			response.sendError(403,"Test Not Allowed");
			return;
		}
		}catch(Exception e){
			response.sendError(500);
			e.printStackTrace();
			return;
		}
	
		
		RequestDispatcher rd = request.getRequestDispatcher("TestStartPageNA.jsp");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process_request(request,response);
	}

}
