package org.cadet.util.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.util.model.Constants;
import org.cadet.util.model.DatabaseConnection;
import org.cadet.util.model.EmailSend;
import org.cadet.util.model.ErrorLogging;
import org.cadet.util.model.UserControl;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404,"No Get Request Allowed for this Page");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Connection dbconnection = DatabaseConnection.getInstance().getDbConnection();
		HttpSession session = request.getSession();
		String username = request.getParameter("Username");
		String password = request.getParameter("Password");
		String password2 = request.getParameter("Password2");
		String name = request.getParameter("Name");
		String Contact = request.getParameter("Contact");
		String Category = request.getParameter("Category");
		
		boolean usernameok = false,passwordok= false,nameok = false,contactok = false,categoryok = false;
		
		Pattern p = Pattern.compile(Constants.email.EmailRegex);
		
		if(p.matcher(username).matches()){
			usernameok=true;
		}
		
		if(password.equals(password2)){
			passwordok = true;
		}
		
		if(name!=""&&name!=null){
			nameok = true;
		}
		
		if(Contact!=""&&Contact!=null){
			contactok = true;
		}
		
		try {
			if(UserControl.hasCategory(dbconnection,Category)){
				categoryok = true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			response.sendError(500);
			e1.printStackTrace();
			return;

			
		}
		
		if(!(usernameok && passwordok && nameok && contactok && categoryok)){
			response.sendError(403,"Inappropriate enrty");
			System.out.println("Everything ok : " + usernameok + passwordok + nameok + contactok + categoryok);
			return;
		}
		
		try{
			if ((boolean) session.getAttribute("Captcha")){
			}else{
				response.sendError(403,"Invalid captcha");
				return;
			}
		}catch(Exception e){
			response.sendError(403,"Invalid captcha");
			return;
		}
		
		try {
			
			if(!UserControl.isClientAvailable(dbconnection, username)){
				response.sendError(403,"Username Not Available");
				return;
			}
			
			UserControl.AddClient(dbconnection, username, password, name, Contact,Category);
			EmailSend.SendMail(username);
			response.sendRedirect("EmailVerification");
		} catch (SQLException e) {
			ErrorLogging.getInstance().log(Level.SEVERE, e);
			response.sendError(500,new Date().toString()+"- Database Connection Error");
			return;
		} catch (MessagingException e) {
			ErrorLogging.getInstance().log(Level.SEVERE, e);
			response.sendError(500,new Date().toString()+"- Email fail Error");
			return;
		}
		
		response.sendRedirect("EmailVerification");
		
	}

}
