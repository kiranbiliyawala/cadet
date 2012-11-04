package org.cadet.admin.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.admin.model.UserCategoryModel;
import org.cadet.util.model.DatabaseConnection;

/**
 * Servlet implementation class AddCandidateCategory
 */
@WebServlet("/admin/usercategory/AddCandidateCategory")
public class AddCandidateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCandidateCategory() {
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
		//PrintWriter out = response.getWriter();
		String candidatecategory=request.getParameter("txtcandidatecategory");
        
		DatabaseConnection dbConn = DatabaseConnection.getInstance();
	    Connection dbConnection = dbConn.getDbConnection();
	    
        try {
        	             
             UserCategoryModel.addNewUserCategory(dbConnection, candidatecategory);
             
           
           request.getRequestDispatcher("viewallcategory.jsp").forward(request,response);

        } catch(Exception e) {
	e.printStackTrace();
        }
		
		
		
		
	}

}
