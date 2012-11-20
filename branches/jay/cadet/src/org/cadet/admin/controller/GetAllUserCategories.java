package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.admin.model.UserCategoryModel;
import org.cadet.util.model.DatabaseConnection;
import org.json.JSONObject;

/**
 * Servlet implementation class GetAllUserCategories
 */
@WebServlet("/admin/usercategory/GetAllUserCategories")
public class GetAllUserCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllUserCategories() {
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
		
		PrintWriter out = response.getWriter();
		JSONObject data = null;
		
		DatabaseConnection dbConn = DatabaseConnection.getInstance();
		Connection dbConnection = dbConn.getDbConnection();
		
		try{
			
		data = UserCategoryModel.getAllUserCategory(dbConnection);
		data.put("result", true);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.println(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
//		JSONArray objJarray = new JSONArray();
//		PrintWriter out = response.getWriter();
//		UserCategoryModel ucm = new UserCategoryModel(dbConnection);
//		
//		BeanUserCategory[] objTests = ucm.getAllUserCategories();
//		try {
//		JSONObject jObj;
//		jObj = new JSONObject();
////		for (BeanUserCategory oTest : objTests) {			
//		for(int i=0;i<objTests.length;i++){
//				System.out.println(objTests[i].getCandidateCategory());
//				jObj.put("CandidateCategory" + i, objTests[i].getCandidateCategory());	
//				
//		}
//		//objJarray.put(jObj);
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		out.println(jObj);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
