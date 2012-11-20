package org.cadet.client.controller.adaptive;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.model.adaptive.AdaptiveTest;
import org.cadet.util.Exceptions.TestFinishException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class SubmitTest
 */
@WebServlet("/client/Test/Adaptive/SubmitTest")
public class SubmitTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject data = null;
		PrintWriter out;
		AdaptiveTest test;
		
		try {
			data = new JSONObject();
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			out = response.getWriter();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return;
		}
		
		HttpSession session=request.getSession(false);
		if(session==null){
			try {
				data.put("result", "invalidSession");
				out.println(data);
				return;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		
		test= (AdaptiveTest)session.getAttribute("test");
		
		
			try {
				test.finishTest(session.getAttribute("user").toString());
				response.sendRedirect("TestEndPageA.jsp");
				return;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					data.put("result", "databaseError");
					out.println(data);
					return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			} catch (TestFinishException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					response.sendRedirect("TestEndPageA.jsp");
					return;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return;
				}
			}
			
		
	}
}
