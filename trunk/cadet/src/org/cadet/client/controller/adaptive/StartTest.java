package org.cadet.client.controller.adaptive;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.model.adaptive.AdaptiveTest;
import org.cadet.client.model.adaptive.AdaptiveTestDBTransactions;

/**
 * Servlet implementation class StartTestA
 * 
 * @author Varun Jamdar
 * 
 */
@WebServlet("/Client/Test/Adaptive/StartTest")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//put this into doPost rather than doGet when Rajan's work is done and when you link the modules.
		
		try {
			HttpSession session = request.getSession();
			String testStatus=session.getAttribute("testStatus").toString();
			if(testStatus!=null){
				request.getRequestDispatcher("").forward(request, response);//send to client dashboard from where start test button was clicked.
			}
			Integer testID = Integer.parseInt(request.getAttribute("testID").toString());

			if (AdaptiveTestDBTransactions.checkTestWithinDuration(testID.intValue())) {
				session.setAttribute("testStarted", true);//check on pop up page whether test has been started so that candidate cannot restart the test by resubmitting the url.
				try {
					AdaptiveTest test=new AdaptiveTest(testID.intValue(),session.getAttribute("username").toString());
					session.setAttribute("test", test);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("error", e.getMessage());
					request.getRequestDispatcher("").forward(request, response);//send to client dashboard to display error message from where start test button was clicked.
				}
				
				//code here for pop up the test page and start test
				
				
				

			} else {
				request.setAttribute("error", "Either Test Period has not started or is over !!");
			}

		} catch (SQLException sqle) {
			request.setAttribute("error", sqle.getMessage());
			request.getRequestDispatcher("/client/index.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/client/index.jsp").forward(request, response);

		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendError(403);
	}

}
