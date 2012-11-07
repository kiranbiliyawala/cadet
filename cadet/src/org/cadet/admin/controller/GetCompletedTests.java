package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.cadet.admin.model.Result;
import org.cadet.util.model.Constants;
import org.cadet.util.model.DatabaseConnection;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class GetCompletedTests
 */
@WebServlet("/admin/test/Result/GetCompletedTests")
public class GetCompletedTests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCompletedTests() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(403);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Connection connection = DatabaseConnection.getInstance().getDbConnection();
		try {
			JSONObject jsobject = new JSONObject();
			jsobject.put("testList", Result.getTests(connection).toString());
			out.print(jsobject.toString());
			response.setContentType("application/json");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
