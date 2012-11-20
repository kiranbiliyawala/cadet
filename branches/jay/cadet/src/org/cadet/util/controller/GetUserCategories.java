package org.cadet.util.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.util.model.DatabaseConnection;
import org.cadet.util.model.UserControl;
import org.json.JSONException;
import org.json.JSONObject;

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
		
	
		JSONObject Ar = null;
		try {
			Ar = UserControl.getUserCategories(connection);
		} catch (SQLException e) {
			response.sendError(500);
			return;
		} catch (JSONException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		

/*		String out = "";
		String categories_options_start ="<option value=\"";
		String tag_end = "\">";
		String option_end = "</option>";*/
		
/*		for(int i=0;i<Ar.size();i++){
			out = out+categories_options_start+Ar.get(i)+tag_end+Ar.get(i)+option_end;
		}*/

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(Ar);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process_request(request, response);
	}

}
