package org.cadet.util.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.util.model.DatabaseConnection;
import org.cadet.util.model.UserControl;

/**
 * Servlet implementation class GetUserCategories
 */
@WebServlet("/GetUserCategories")
public class GetUserCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserCategories() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process_request(request,response);
	}

	private void process_request(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Connection connection = DatabaseConnection.getInstance().getDbConnection();
		
	
		ArrayList<String> Ar;
		try {
			Ar = UserControl.getUserCategories(connection);
		} catch (SQLException e) {
			response.sendError(500);
			return;
		}
		
		String out = "";
		
		for(int i=0;i<Ar.size();i++){
			out = out+Ar.get(i);
		}
		
		response.getWriter().print(out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process_request(request, response);
	}

}
