package org.cadet.client.controller.adaptive;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.client.bean.Question;
import org.cadet.client.model.adaptive.AdaptiveTest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class FetchNextQuestion
 */
@WebServlet("/Client/Test/Adaptive/FetchNextQuestion")
public class FetchNextQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchNextQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestType=request.getParameter("requestType");
		JSONObject data=null;
		PrintWriter out;
		AdaptiveTest test;
		Question question;
		try{
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out=response.getWriter();
			data=new JSONObject();
		}catch(IOException ioe){
			return;
		}
		
		try{
			HttpSession session = request.getSession(false);

			if (session == null) {
				try {
					data.put("result", "invalidSession");
					out.println(data);
					return;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			}
			test = (AdaptiveTest) session.getAttribute("test");
			
			if(requestType.equals("getFirstQuestion")){
				int categoryId=Integer.parseInt(request.getParameter("categoryId"));
				try {
					question=test.startSection(categoryId);
					JSONArray array=new JSONArray();
					try {
						array.put(0, question.getQuestionId());
						array.put(1, question.getOptionA());
						array.put(2, question.getOptionB());
						array.put(3, question.getOptionB());
						array.put(4, question.getOptionD());
						array.put(5, question.getCorrectAnswer());
						data.put("question", array);
						data.put("result", true);
						out.println(data);
						return;
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
				} catch (SQLException sqle) {
					// TODO Auto-generated catch block
					sqle.printStackTrace();
					try {
						data.put("result", "databaseError");
						out.println(data);
						return;
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
				}catch(InvalidAlgorithmParameterException iape) {
					iape.printStackTrace();
					try {
						data.put("result", "algorithmError");
						out.println(data);
						return;
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
				}catch(Exception ex){
					switch(ex.getMessage()){
					case "This section is already completed!":
						try {
							data.put("result", "sectionAlreadyDoneError");
							out.println(data);
							return;
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return;
						}
					case "No such Test Exists!":
						try {
							data.put("result", "accessDeniedError");
							out.println(data);
							return;
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return;
						}
						default:
							ex.printStackTrace();
							return;
					}
				}
				
			}
			else if(requestType.equals("submitQuestion")){
				String answer=request.getParameter("answer");
				try {
					question=test.submitQuestion(answer);
					JSONArray array=new JSONArray();
					try {
						array.put(0, question.getQuestionId());
						array.put(1, question.getOptionA());
						array.put(2, question.getOptionB());
						array.put(3, question.getOptionB());
						array.put(4, question.getOptionD());
						array.put(5, question.getCorrectAnswer());
						data.put("question", array);
						data.put("result", true);
						out.println(data);
						return;
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
				} catch (SQLException sqle) {
					// TODO Auto-generated catch block
					sqle.printStackTrace();
					try {
						data.put("result", "databaseError");
						out.println(data);
						return;
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
				} catch (Exception ex){
					if(ex.getMessage().equals("Section completed!")){
						try {
							data.put("result", "sectionFinished");
							out.println(data);
							return;
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return;
						}
					}else{
						ex.printStackTrace();
						return;
					}
				}
			}
			else { //skip question
				try {
					question=test.skipQuestion();
					JSONArray array=new JSONArray();
					try {
						array.put(0, question.getQuestionId());
						array.put(1, question.getOptionA());
						array.put(2, question.getOptionB());
						array.put(3, question.getOptionB());
						array.put(4, question.getOptionD());
						array.put(5, question.getCorrectAnswer());
						data.put("question", array);
						data.put("result", true);
						out.println(data);
						return;
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						return;
					}
				} catch (SQLException sqle) {
					// TODO Auto-generated catch block
					sqle.printStackTrace();
					try {
						data.put("result", "databaseError");
						out.println(data);
						return;
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						return;
					}
				} catch (Exception ex){
					if(ex.getMessage().equals("Section completed!")){
						try {
							data.put("result", "sectionFinished");
							out.println(data);
							return;
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							return;
						}
					}else{
						ex.printStackTrace();
						return;
					}
				}
			}
		}catch(Exception e){
			
		}
	}
}
