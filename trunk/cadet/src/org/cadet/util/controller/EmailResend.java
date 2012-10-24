package org.cadet.util.controller;

import java.io.IOException;
import java.util.logging.Level;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.util.model.EmailSend;
import org.cadet.util.model.ErrorLogging;

/**
 * Servlet implementation class EmailResend
 */
@WebServlet("/EmailResend")
public class EmailResend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailResend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		try{
			EmailSend.SendMail(username.toString());
			response.sendRedirect("EmailVerification");
			return;
		}catch(NullPointerException e){
			response.sendError(403, "Invalid parameters");
			return;
		}catch(MessagingException e){
			ErrorLogging.getInstance().log(Level.SEVERE, e);
			response.sendError(500, "Email error");
			return;
		}
	}

}
