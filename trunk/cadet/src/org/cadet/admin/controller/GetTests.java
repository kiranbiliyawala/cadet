package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.admin.model.TestDbTransactions;
import org.cadet.util.model.DatabaseConnection;
import org.cadet.util.model.ErrorLogging;
import org.cadet.util.model.json.JSONObject;

/**
 * Servlet implementation class GetTests
 */
@WebServlet("/GetTests")
public class GetTests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTests() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.sendError(404,"No Post Request Allowed for this Page");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		
		DatabaseConnection dbConn = DatabaseConnection.getInstance();
		Connection dbConnection = dbConn.getDbConnection();

		try {

			JSONObject data = TestDbTransactions.getTests(dbConnection);
			System.out.println(data.toString());

			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");

		    PrintWriter out = response.getWriter();
		    out.println(data);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ErrorLogging.getInstance().log(Level.SEVERE, e);
			response.sendError(500,new Date().toString()+" - Database Error Check error log for futher information");
		}
	}

}
