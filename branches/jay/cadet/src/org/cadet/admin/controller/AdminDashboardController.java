package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.cadet.admin.model.AdminDashboard;
import org.cadet.admin.model.TestDbTransactions;
import org.cadet.util.model.DatabaseConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class AdminDashboardController
 */
@WebServlet("/admin/AdminDashboardController")
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AdminDashboardController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(403);
		}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//String requestType = request.getParameter("requestType");	
			PrintWriter out = response.getWriter();
			JSONObject data = null;

			
				DatabaseConnection dbConn = DatabaseConnection.getInstance();
				Connection dbConnection = dbConn.getDbConnection();
				try {
					try {
						data = AdminDashboard.gettestdetail(dbConnection);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					data.put("result", true);

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
				    out.println(data);
				   } 
				catch (SQLException e) 
				{
					response.sendError(500);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (JSONException e) 
				{
					response.sendError(500);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
	}
	
	
	