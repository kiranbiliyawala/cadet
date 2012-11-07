package org.cadet.client.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.bean.BeanTest;
import org.cadet.client.model.CandidateCategory;
import org.cadet.client.model.TestRegister;
import org.cadet.util.model.DatabaseConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class GetViewAllTest
 */
@WebServlet("/client/Test/ViewAll")
public class GetViewAllTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetViewAllTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	DatabaseConnection dbConn = DatabaseConnection.getInstance();
	Connection dbConnection = dbConn.getDbConnection();
	String CCatName=null;
	HttpSession cadetsession = request.getSession();
	String CUserName = (String) cadetsession.getAttribute("user");
	try {
		CCatName = CandidateCategory.fetch(dbConnection, CUserName);
		System.out.println("Gettesttoregister" + CCatName);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	JSONArray objJarray = new JSONArray();
	
	TestRegister objTestRegister = new TestRegister(dbConnection);
	
	BeanTest[] objTests = null;
	try {
		objTests = objTestRegister.getViewAllTestForUser(CUserName, CCatName);
	} catch (SQLException e1) {
		response.sendError(500);
		e1.printStackTrace();
	return;
	}
	System.out.println("GetViewAllTest" + CUserName + CCatName);
	JSONObject jObj;
	
	for (BeanTest oTest : objTests) {
		jObj = new JSONObject();
		
		try {
			jObj.put("testName", oTest.getTestName());
			jObj.put("testDate", oTest.getTestDate());
			jObj.put("testDuration", oTest.getTestDuration());
			jObj.put("testId", oTest.getTestId());
			jObj.put("testDesc", oTest.getTestDesc());
			objJarray.put(jObj);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	
	PrintWriter out = response.getWriter();
	
	JSONObject jResp = new JSONObject();
	
	try {
		jResp.put("tests", objJarray);
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	out.print(jResp);
	
	
	
	
	}

}
