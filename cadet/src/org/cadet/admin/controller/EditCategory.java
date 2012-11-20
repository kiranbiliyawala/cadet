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
 * Servlet implementation class EditCategory
 *
 *@WebServlet("/EditCategory")
 *
 */

public class EditCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean status=false;
		PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        Category objCategory = new Category();
        CategoryManagement objCategoryManagement = new CategoryManagement();
        String categoryId = request.getParameter("categoryId").toString();
        String categoryName = request.getParameter("categoryName").toString();
        String categoryDescription = request.getParameter("categoryDescription").toString();
        objCategory.setCategoryId(categoryId);
        objCategory.setCategoryName(categoryName);
        objCategory.setCategoryDescription(categoryDescription);
        objCategory.setAdminUsername((String)session.getAttribute("user"));
        
        System.out.println(objCategory.getAdminUsername());
        
       try{
            objCategoryManagement.editCategory(objCategory);
            status = true;
        }
        catch(Exception e){
            status = false;
        }
        if(status){
        	out.print("Category Edited");
            request.setAttribute("status", "Done");
        }
        else{
        	out.print("Category Not Edited");
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
