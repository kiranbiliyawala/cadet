package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExceptionError
 */
@WebServlet("/admin/ServerException")
public class ServerException extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerException() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();

		String resData = "<div class='container' style='max-width: 380px; _width: 380px; margin: 0 auto;'>"+
				"<h1 style='margin: 0 10px; font-size: 44px; text-align: center;'>Server Exception<br><span style='color: #bbb;'>:(</span></h1>"+
				"<p style='margin: 1em 0;'>Sorry, but the page you were trying to view could not respond as expected.</p>"+
				"<p style='margin: 1em 0;'>It looks like this was the result of either:</p>"+
				"<ul style='padding: 0 0 0 40px; margin: 1em 0;'>"+
					"<li>Invalid data entry in the form</li>"+
					"<li>Exception at the server side</li>"+
				"</ul>"+
			"</div>";
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		out.println(resData);
	}

}
