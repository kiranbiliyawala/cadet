package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.admin.bean.BeanTest;
import org.cadet.admin.bean.BeanTestCategory;
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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub

	response.sendError(404, "No Get Request Allowed for this Page");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub

	String requestType = request.getParameter("requestType");
	PrintWriter out = response.getWriter();
	JSONObject data = null;

	if (requestType.equals("getAllTests")) {

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
	    } catch (Exception e) {

		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
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

	else if (requestType.equals("createTest")) {

	    String testName = request.getParameter("txtTestName");
	    String testType = request.getParameter("optTestType");
	    String testDesc = request.getParameter("taTestDesc");

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();
	    int testId;

	    try {

		TestDbTransactions.createTest(dbConnection, testName, testType, testDesc);

		testId = TestDbTransactions.getLastInsertID(dbConnection);

	    } catch (SQLException e) {
		// TODO Auto-generated catch block

		e.printStackTrace();
		response.sendRedirect("../DatabaseError.html");
		return;
	    } catch (Exception e) {

		e.printStackTrace();
		response.sendRedirect("../ServerException.html");
		return;
	    }

	    /* AFTER TEST CREATION -- Redirection to Test Page */

	    try {
		if (testId != -1) {

		    ArrayList<BeanTestCategory> categoryDetails = TestDbTransactions.getTestCategoryDetails(dbConnection, testId);

		    request.setAttribute("testId", testId);
		    request.setAttribute("testName", testName);
		    request.setAttribute("testType", testType);
		    request.setAttribute("categoryDetails", categoryDetails);

		    RequestDispatcher rd = request.getRequestDispatcher("testPage.jsp");
		    rd.forward(request, response);
		} else {
		    response.sendRedirect("../ServerException.html");
		    return;
		}
	    } catch (SQLException e) {

		e.printStackTrace();
		response.sendRedirect("../DatabaseError.html");
		return;
	    } catch (Exception e) {

		e.printStackTrace();
		response.sendRedirect("../ServerException.html");
		return;
	    }
	}

	else if (requestType.equals("editTest")) {

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();
	    int testId;

	    try {

		testId = Integer.parseInt(request.getParameter("testId"));

		ArrayList<BeanTestCategory> categoryDetails = TestDbTransactions.getTestCategoryDetails(dbConnection, testId);
		BeanTest test = TestDbTransactions.getTestDetails(dbConnection, testId);

		request.setAttribute("testId", testId);
		request.setAttribute("testName", test.getTestName());
		request.setAttribute("testType", test.getTestType());
		request.setAttribute("categoryDetails", categoryDetails);

		RequestDispatcher rd = request.getRequestDispatcher("testPage.jsp");
		rd.forward(request, response);
	    } catch (SQLException e) {

		e.printStackTrace();
		response.sendRedirect("../DatabaseError.html");
		return;
	    } catch (Exception e) {

		e.printStackTrace();
		response.sendRedirect("../ServerException.html");
		return;
	    }
	}

	else if(requestType.equals("saveTest")) {

	    int testId = Integer.parseInt(request.getParameter("testId"));
	    String testType = request.getParameter("testType");

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    if(testType.equals("Adaptive")) {

		try{

		    ArrayList<BeanTestCategory> temp = TestDbTransactions.getTestCategoryDetails(dbConnection, testId);
		    BeanTestCategory categoryDetails[] = temp.toArray(new BeanTestCategory[temp.size()]);
        
		    for(int i=0;i<categoryDetails.length;i++) {

    		    	int categoryId = categoryDetails[i].getCategoryId();
    		    	int questionsPerCategory = Integer.parseInt(request.getParameter("txtNoQueCat"+categoryId));
    		    	int timePerCategory = Integer.parseInt(request.getParameter("txtTimeCat"+categoryId));

    		    	TestDbTransactions.setTestCategoryDetails(dbConnection,testId,categoryId,questionsPerCategory,timePerCategory);
    		    	categoryDetails[i].setQuestionsPerCategory(questionsPerCategory);
    		    	categoryDetails[i].setTimePerCategory(timePerCategory);
		    }

		    request.setAttribute("testId", testId);
		    request.setAttribute("testName", request.getParameter("testName"));
		    request.setAttribute("testType", testType);
		    request.setAttribute("categoryDetails",categoryDetails);

		    RequestDispatcher rd = request.getRequestDispatcher("testPage.jsp");
		    rd.forward(request, response);
		} catch (SQLException e) {

		    e.printStackTrace();
		    response.sendRedirect("../DatabaseError.html");
		    return;
		} catch (Exception e) {

		    e.printStackTrace();
		    response.sendRedirect("../ServerException.html");
		    return;
    	    	}
	    } else if(testType.equals("Non-Adaptive")) {
		out.println("Non-Adaptive Deletion");
	    }
	}

	else if (requestType.equals("deleteTest")) {

	    int testId = Integer.parseInt(request.getParameter("testId").split("delete")[1]);

	    DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();

	    try {

		TestDbTransactions.deleteTest(dbConnection, testId);

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
	    } catch (Exception e) {

		e.printStackTrace();

		data = new JSONObject();
		try {
		    data.put("result", "ServerException");
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
