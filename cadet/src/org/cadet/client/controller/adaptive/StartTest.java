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
@WebServlet("/client/Test/Adaptive/CreateTest")
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
			/*String testStatus=session.getAttribute("testStatus").toString();
			if(testStatus!=null){
				request.getRequestDispatcher("").forward(request, response);//send to client dashboard from where start test button was clicked.
			}*/
			Integer testID = Integer.parseInt(request.getParameter("testid").toString());

			if (AdaptiveTestDBTransactions.checkTestWithinDuration(testID)) {
				//session.setAttribute("testStarted", true);//check on pop up page whether test has been started so that candidate cannot restart the test by resubmitting the url.
				try {
					AdaptiveTest test=new AdaptiveTest(testID,session.getAttribute("user").toString());
					session.setAttribute("test", test);
					System.out.print("hio");
					request.getRequestDispatcher("TestStartPageA.jsp").include(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("error", e.getMessage());
					response.sendError(500);
					e.printStackTrace();
					return;
				}
				
				//code here for pop up the test page and start test
				
				
				

			} else {
				
				response.sendError(403,"No Test Allowed");
				
				return;
			}

		} catch (SQLException sqle) {
			request.setAttribute("error", sqle.getMessage());
			sqle.printStackTrace();
			response.sendError(500);
			return;
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
			response.sendError(500);
			return;

		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
