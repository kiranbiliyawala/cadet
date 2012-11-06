package org.cadet.util.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.util.model.UserControl;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
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
		String username = request.getParameter("user");
		String key = request.getParameter("key");
		HttpSession session = request.getSession();
		String timekey = new Long((new Date().getTime())/600000).toString();
		
		String datakey = UserControl.getHashEmail(username+timekey);
		if(datakey.equals(key)){
			session.setAttribute("user", username);
			session.setAttribute("AdminAuth", false);
			session.setAttribute("ClientAuth", true);
			session.setAttribute("homeurl","client/dashboard");
			response.sendRedirect("client/ChangePassword");
			return;
		}else{
			response.sendError(403,"Invalid Access Parameters");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process_request(request, response);
	}

}
