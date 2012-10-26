package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
import org.json.JSONObject;

/**
 * Servlet implementation class GetTests
 */
@WebServlet("/admin/test/TestManagement")
public class TestManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.sendError(404,"No Get Request Allowed for this Page");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		

		String requestType = request.getParameter("requestType");
		PrintWriter out = response.getWriter();

		if(requestType.equals("getAllTests")) {

			DatabaseConnection dbConn = DatabaseConnection.getInstance();
			Connection dbConnection = dbConn.getDbConnection();

			try {

				JSONObject data = TestDbTransactions.getAllTests(dbConnection);

				response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");

			    out = response.getWriter();
			    out.println(data);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				ErrorLogging.getInstance().log(Level.SEVERE, e);
//				response.sendError(500,new Date().toString()+" - Database Error Check error log for futher information");
			}
		}
		else if(requestType.equals("createTest")) {

			String testName = request.getParameter("txtTestName");
			String testType = request.getParameter("optTestType");
			String testDesc = request.getParameter("taTestDesc");


			DatabaseConnection dbConn = DatabaseConnection.getInstance();
			Connection dbConnection = dbConn.getDbConnection();

			try {
				TestDbTransactions.createTest(dbConnection,testName,testType,testDesc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				ErrorLogging.getInstance().log(Level.SEVERE, e);
				response.sendError(500,new Date().toString()+"- Database Connection Error");
				return;
			}

			response.sendRedirect("testPage.jsp");
		}
	}
}
