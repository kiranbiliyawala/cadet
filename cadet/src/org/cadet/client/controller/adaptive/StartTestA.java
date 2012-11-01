package org.cadet.client.controller.adaptive;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.model.adaptive.AdaptiveTestDBTransactions;
import org.cadet.util.model.DatabaseConnection;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class StartTestA
 * 
 * @author Varun Jamdar
 * 
 */
@WebServlet("/Test/Adaptive/StartTestA")
public class StartTestA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartTestA() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {

			// this code checks if the test is started in within the stipulated
			// test duration.

			HttpSession session = request.getSession();
			Integer testID = Integer.parseInt(request.getAttribute("testID").toString());

			if (AdaptiveTestDBTransactions.checkTestWithinDuration(testID.intValue())) {
				request.setAttribute("testStarted", true);
				
				//pop up the test page and start test
				
				

			} else {
				request.setAttribute("error",
						"Either Test Period has not started or is over !!");
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
