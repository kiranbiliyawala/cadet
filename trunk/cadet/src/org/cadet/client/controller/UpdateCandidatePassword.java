package org.cadet.client.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.model.CandidateProfileModel;
import org.cadet.util.model.DatabaseConnection;

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
        
        HttpSession cadetsession = request.getSession();
		String CUserName = (String) cadetsession.getAttribute("user");
		CandidateProfileModel cpm = new CandidateProfileModel(dbConnection);
		
		try {
            String pwd=request.getParameter("currentpassword");
            String newPass= request.getParameter("changepassword");
            String newcPass= request.getParameter("confirmpassword");
            String hashpass = CandidateProfileModel.Hashify(pwd);
            System.out.println(hashpass);
            if(! newcPass.equals(newPass)){
            	System.out.println("Password confirmed");
            	cadetsession.setAttribute("passwordconfirmcheck", false);
                //out.println("New pass & Confirm pass is not matched");
            }else{

            //Change_Password
            
            boolean success = cpm.updateCandidatePassword(hashpass, newPass, CUserName);

            if(success == true){
            	cadetsession.setAttribute("passwordcheck", true);

            }else{
            	cadetsession.setAttribute("passwordcheck", false);
            }
            }
            request.getRequestDispatcher("changepassword.jsp").forward(request,response);
        } finally { 
            out.close();
        }
	}

}
