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
 * Servlet implementation class Gettesttoregister
 */
@WebServlet("/Test/newRegisterList")
public class Gettesttoregister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gettesttoregister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DatabaseConnection dbConn = DatabaseConnection.getInstance();
		Connection dbConnection = dbConn.getDbConnection();
		
		TestRegister objTestRegister = new TestRegister(dbConnection);
		
		HttpSession cadetsession = request.getSession();
		String CUserName = (String) cadetsession.getAttribute("user");
		String CCatName=null;
		try {
			CCatName = CandidateCategory.fetch(dbConnection, CUserName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		JSONArray objJarray= new JSONArray();
		
		
		
		BeanTest[] objTests= objTestRegister.getNotRegisterTestForUser(CUserName, CCatName);
		
		JSONObject jObj;
		
		for(BeanTest oTest : objTests ){
			jObj= new JSONObject();
			try {
				jObj.put("testName", oTest.getTestName());
				jObj.put("testDate", oTest.getTestDate());
				jObj.put("testDuration", oTest.getTestDuration());
				jObj.put("testId", oTest.getTestId());
				
				objJarray.put(jObj);
		
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out= response.getWriter();
		
		JSONObject jResp = new JSONObject();
		try {
			jResp.put("tests",objJarray );
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		out.print(jResp);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
