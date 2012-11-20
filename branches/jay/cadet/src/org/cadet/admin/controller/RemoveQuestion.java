package org.cadet.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cadet.admin.model.QuestionBankManagement;

/**
 * Servlet implementation class RemoveQuestion
 * 
 *@WebServlet("/RemoveQuestion") 
 * 
 */

public class RemoveQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveQuestion() {
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
		QuestionBankManagement objQuestionBankManagement = new QuestionBankManagement();
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String error="";

        try{
            objQuestionBankManagement.removeQuestion(questionId);
            status = true;
        }
        catch(Exception e){
            status = false;
            error=e.getMessage();
        }
        if(status){
        	out.print("Question Removed");
            request.setAttribute("status", "Done");
        }
        else{
        	out.print("Question Not Removed");
        	request.setAttribute("status", "Fail, error:" + error);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
