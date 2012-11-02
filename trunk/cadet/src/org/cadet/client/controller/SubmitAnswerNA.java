package org.cadet.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.model.NonAdaptiveTest;
import org.json.JSONException;

/**
 * Servlet implementation class SubmitAnswerNA
 */
@WebServlet("/Test/NonAdaptive/SubmitAnswer")
public class SubmitAnswerNA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitAnswerNA() {
        super();
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
	
		HttpSession session = request.getSession();
		NonAdaptiveTest test;
		String Category;
		String QNO;
		String QID;
		String ANS;
		try{
			test = (NonAdaptiveTest) session.getAttribute("test");
			Category = request.getParameter("CATEGORY").toString();
			QNO = request.getParameter("QNO").toString();
			QID = request.getParameter("QID").toString();
			ANS = request.getParameter("ANS").toString();
		}catch(Exception e){
			response.sendError(403);
			return;
		}
		
		try {
			test.Store_Answer(Category, QNO, QID, ANS);
		} catch (JSONException e) {
			response.sendError(500);
			return;
		}
		
	}

}
