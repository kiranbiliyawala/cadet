package org.cadet.util.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.util.model.DatabaseConnection;
import org.cadet.util.model.EmailSend;
import org.cadet.util.model.ErrorLogging;
import org.cadet.util.model.UserControl;

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
		if(username==null){
			response.sendError(403,"Inaprpriate Entry");
			return;
		}
		try{
			Connection connection = DatabaseConnection.getInstance().getDbConnection();
			try {
				if(UserControl.isClientAvailable(connection, username)){
					response.sendError(403, "No Such Username");
					return;	
				}
			} catch (SQLException e) {
				response.sendError(403, "No Such Username");
				return;	
			}
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
