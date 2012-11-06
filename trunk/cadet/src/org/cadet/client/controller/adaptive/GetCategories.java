package org.cadet.client.controller.adaptive;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.bean.CategoryAdaptiveTest;
import org.cadet.client.model.adaptive.AdaptiveTest;

/**
 * Servlet implementation class GetCategories
 */
@WebServlet("/GetCategories")
public class GetCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCategories() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			HttpSession session=request.getSession(false);
			if(session==null)
				response.sendRedirect("");//send to dashboard
			AdaptiveTest test=(AdaptiveTest)session.getAttribute("test");
			try{
				Collection<CategoryAdaptiveTest> categories= test.getUnattemptedCategories().values();
				request.setAttribute("categories", categories);
				request.getRequestDispatcher("/client/Test/Adaptive/ShowCategories.jsp").forward(request, response);
			}catch(Exception ex){
				if(ex.getMessage().equals("Test Finished!")){
					//redirect to finished test page.
				}
			}
		}catch(Exception e){
			response.sendError(403);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendError(403);
	}

}
