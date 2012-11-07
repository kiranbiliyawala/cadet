package org.cadet.client.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.model.CandidateProfileModel;
import org.cadet.util.model.DatabaseConnection;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class GetCandidateProfileServlet
 */
@WebServlet("/client/profile/GetCandidateProfileServlet")
public class GetCandidateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCandidateProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DatabaseConnection dbConn = DatabaseConnection.getInstance();
		Connection dbConnection = dbConn.getDbConnection();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession cadetsession = request.getSession();
		String CUserName = (String) cadetsession.getAttribute("user"); 
		System.out.println("in servlet: " + CUserName);
		CandidateProfileModel cpm = new CandidateProfileModel(dbConnection);
		try {

			List<String> list_CandidateProfileData = cpm.getCandidateProfileData(CUserName);
			System.out.println("From servlet:" + list_CandidateProfileData);
			request.setAttribute("CandidateProfileData",
					list_CandidateProfileData);
			List<String> list_CandidateCategory = cpm.getCandidateCategory();
			System.out.println("From servlet:" + list_CandidateCategory);
			request.setAttribute("CandidateCategory", list_CandidateCategory);

			request.getRequestDispatcher("changedetails.jsp").forward(request,
					response);

		}  catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/pages/DatabaseError.html").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/pages/ServerException.html").forward(request, response);
		}		finally {
			out.close();
		}
		
	}

}
