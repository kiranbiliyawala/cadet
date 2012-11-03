package org.cadet.client.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.client.model.TestRegister;
import org.cadet.util.model.DatabaseConnection;

/**
 * Servlet implementation class SetRegisterTest
 */
@WebServlet("/Test/Register")
public class SetRegisterTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetRegisterTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DatabaseConnection dbConn = DatabaseConnection.getInstance();
		Connection dbConnection = dbConn.getDbConnection();
		TestRegister objTest = new TestRegister(dbConnection);
		int testid = Integer.parseInt(request.getParameter("testid"));
		
		String CUserName = "user8";
		
		
		
		objTest.setRegisterTestForUser(CUserName, testid);
		
		
		
		
		
		
	}

}
