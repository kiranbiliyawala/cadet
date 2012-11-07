package org.cadet.client.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.model.CandidateProfileModel;
import org.cadet.util.model.DatabaseConnection;
import org.cadet.util.model.UserControl;

/**
 * Servlet implementation class UpdateCandidatePassword
 */
@WebServlet("/client/profile/UpdateCandidatePassword")
public class UpdateCandidatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCandidatePassword() {
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
		DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean success=false;
        HttpSession cadetsession = request.getSession();
		String CUserName = (String) cadetsession.getAttribute("user");
		CandidateProfileModel cpm = new CandidateProfileModel(dbConnection);
		
		try {
            	String newPass= request.getParameter("changepassword");
            	String password= request.getParameter("confirmpassword");
            
            	if((! password.equals(newPass)) && password.equals(null) && newPass.equals(null)){
            		cadetsession.setAttribute("passwordcheck", false);
            	}else{

            		//Change_Password
            		success = UserControl.UpdateClientPassword(dbConnection, CUserName, password);
            		if(success == true)
            			request.setAttribute("passwordcheck", true);
			  
            		RequestDispatcher rd = request.getRequestDispatcher("/client/profile/ChangePassword");
            		rd.forward(request, response);
            	}
		}  catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/client/DatabaseError.html").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/client/ServerException.html").forward(request, response);
		} finally { 
            out.close();
		}
	}

}
