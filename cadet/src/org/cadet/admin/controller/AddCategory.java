package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.admin.bean.Category;
import org.cadet.admin.model.CategoryManagement;

/**
 * @author KIRAN
 *
 * Servlet implementation class AddCategory
 * @WebServlet("/AddCategory")
 */

public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean status=false;
		String error="";
		PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        Category objCategory = new Category();
        CategoryManagement objCategoryManagement = new CategoryManagement();
        try{
        	String categoryName = request.getParameter("categoryName").toString();
            String categoryDescription = request.getParameter("categoryDescription").toString();
            objCategory.setCategoryName(categoryName);
            objCategory.setCategoryDescription(categoryDescription);
            objCategory.setAdminUsername(session.getAttribute("user").toString());

            try{
                objCategoryManagement.addCategory(objCategory);
                status = true;
            }
            catch(Exception e){
            	error+="Databade error: " + e.getMessage();
                status = false;
            }
        }catch(Exception e){
        	error+="Outsider catch block: " + e.getMessage();
        	status=false;
        }
        if(status){
        	out.print("Category Added");
            request.setAttribute("status", "Done");
        }
        else{
        	out.print("Category Not Added" + error);
        	request.setAttribute("status", "Fail");
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
