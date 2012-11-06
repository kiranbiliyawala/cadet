package org.cadet.client.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.model.NAHTMLConvert;
import org.cadet.client.model.NonAdaptiveTest;
import org.json.JSONException;

/**
 * Servlet implementation class StartTest
 */
@WebServlet("/client/Test/NonAdaptive/StartTest")
public class StartTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartTest() {
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
		NonAdaptiveTest test = (NonAdaptiveTest) session.getAttribute("test");
		
		try {
			request.setAttribute("Catselector", NAHTMLConvert.CatSelectorHTML(test));
			request.setAttribute("Queselector", NAHTMLConvert.QuestionSelectorHTML(test));		
			request.setAttribute("TimeDuration", test.test_details[2]);
			request.setAttribute("TimeName", test.test_details[0]);
			request.setAttribute("CatDistribution", test.getQuestionDistribution().toString());
			
		} catch (JSONException e) {
			response.sendError(500);
		}
		
		test.startTest();
		RequestDispatcher dispacher = request.getRequestDispatcher("Test.jsp");
		dispacher.include(request, response);
	}

}
