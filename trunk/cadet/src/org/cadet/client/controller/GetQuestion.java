package org.cadet.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.model.NonAdaptiveTest;

/**
 * Servlet implementation class GetQuestion
 */
@WebServlet("/Test/NonAdaptive/GetQuestion")
public class GetQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendError(403);
	}

	/**
     * Default constructor. 
     */
    public GetQuestion() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String QNO = request.getParameter("QNO");
		String category = request.getParameter("CAT");
		HttpSession session = request.getSession();
		NonAdaptiveTest test;
		int qno = 1;
		try{
			qno = Integer.parseInt(QNO);	
			category = category.toString();
		}catch(NullPointerException e){
			response.sendError(403);
			return;
		}catch (NumberFormatException e) {
			response.sendError(403);
		}
		
		try{
				test = (NonAdaptiveTest) session.getAttribute("test");
		}catch(Exception e){
			response.sendError(403);
			return;
		}
		
		try{
			test.getQuestions(category, qno);
		}catch(Exception e){
			response.sendError(500);
			return;
		}
		
	}

}
