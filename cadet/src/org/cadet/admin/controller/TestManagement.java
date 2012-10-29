package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.admin.model.TestDbTransactions;
import org.cadet.util.model.DatabaseConnection;
import org.json.JSONException;
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
		JSONObject data = null;

		if(requestType.equals("getAllTests")) {

			DatabaseConnection dbConn = DatabaseConnection.getInstance();
			Connection dbConnection = dbConn.getDbConnection();

			try {

				data = TestDbTransactions.getAllTests(dbConnection);
				data.put("result", true);

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
			    out.println(data);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				data = new JSONObject();
				try {
					data.put("result", "DatabaseError");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.println(data);
				return;
			} catch(Exception e) {

				e.printStackTrace();

				data = new JSONObject();
				try {
					data.put("result","ServerException");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.println(data);
				return;
			}
		}

		else if(requestType.equals("createTest")) {

			String testName = request.getParameter("txtTestName");
			String testType = request.getParameter("optTestType");
			String testDesc = request.getParameter("taTestDesc");


			DatabaseConnection dbConn = DatabaseConnection.getInstance();
			Connection dbConnection = dbConn.getDbConnection();
			int testID;

			try {

				TestDbTransactions.createTest(dbConnection,testName,testType,testDesc);

				testID = TestDbTransactions.getLastInsertID(dbConnection);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				data = new JSONObject();
				try {
					data.put("result", "DatabaseError");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.println(data);
				return;
			} catch(Exception e) {

				e.printStackTrace();

				data = new JSONObject();
				try {
					data.put("result","ServerException");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.println(data);
				return;
			}

			/* AFTER TEST CREATION */

			System.out.println(testID);

			request.setAttribute("testID",testID);
			request.setAttribute("testName", testName);
			request.setAttribute("testType",testType);
			response.sendRedirect("testPage.jsp");
		}

		else if(requestType.equals("deleteTest")) {

			int testID = Integer.parseInt(request.getParameter("testID").split("delete")[1]);

			DatabaseConnection dbConn = DatabaseConnection.getInstance();
			Connection dbConnection = dbConn.getDbConnection();

			try {

				TestDbTransactions.deleteTest(dbConnection, testID);

				data = new JSONObject();
				data.put("result", true);

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.println(data);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				data = new JSONObject();
				try {
					data.put("result", "DatabaseError");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.println(data);
				return;
			} catch(Exception e) {

				e.printStackTrace();

				data = new JSONObject();
				try {
					data.put("result","ServerException");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.println(data);
				return;
			}
		}
	}
}
