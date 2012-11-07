package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.admin.bean.Category;
import org.cadet.admin.bean.QuestionBank;
import org.cadet.admin.model.CategoryManagement;
import org.cadet.admin.model.QuestionBankManagement;

/**
 * Servlet implementation class EditQuestions
 * 
 * @WebServlet("/EditQuestions")
 * 
 */

public class EditQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditQuestions() {
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
        
		//Get Question to edit
        QuestionBank question = new QuestionBank();
        QuestionBankManagement objQuestionBankManagement = new QuestionBankManagement();
        
        //Get category list for dropdown
        ArrayList<Category> categories = null;
		CategoryManagement objCategoryManagement = new CategoryManagement();
		
		out.println("<html>");
		out.println("<body>");
        try{
			HttpSession session = request.getSession();
			session.setAttribute("user", "admin");
			session.setAttribute("AdminAuth", true);
			session.setAttribute("ClientAuth", false);
			session.setAttribute("homeurl","admin/dashboard");        

			int questionId = Integer.parseInt(request.getParameter("hdnQuestionId"));
			
			//get question detail through Question ID
        	question = objQuestionBankManagement.viewQuestionByQuestionId(questionId);
        	out.println("<br/>question found: " + question);

        	//get category list through model
        	categories = objCategoryManagement.viewCategory();
        	out.println("<br/>category found: " + categories);
        	
        	out.println("<br/>data found");
        	//if succeeded in both functions 
		    status = true;
        }catch(Exception e){
        	status=false;
        }
        out.println("<h3>Question: " + request.getParameter("hdnQuestionId") + " </h3>");
        out.println("<h3>Status: " + status + "</h3>");
        out.println("<h3>QuestionDetail: " + question.getQuestion() + "</h3>");
        out.println("<h3>Categories: " + categories + "</h3>");
        if(status){
        	request.setAttribute("questionId", request.getParameter("hdnQuestionId"));
			request.setAttribute("status", "success");
        	request.setAttribute("questionDetail", question);
			request.setAttribute("categories", categories);
        }
        else{
        	request.setAttribute("status", "fail");
        }
        out.println("</body>");
        out.println("</html>");
		RequestDispatcher rd = request.getRequestDispatcher("/admin/questionbank/EditQuestion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
