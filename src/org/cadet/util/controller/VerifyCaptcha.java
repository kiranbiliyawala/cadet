package org.cadet.util.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cadet.util.model.Constants;
import org.cadet.util.model.ErrorLogging;

/**
 * Servlet implementation class VerifyCaptcha
 */
@WebServlet("/VerifyCaptcha")
public class VerifyCaptcha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyCaptcha() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String remoteip = request.getRemoteAddr();
		String challenge = request.getParameter("recaptcha_challenge_field");
		String respons = request.getParameter("recaptcha_response_field");
		if(challenge==null||respons==null||challenge==""||respons==""){
			response.getWriter().println("false");
			return;
		}
		String Postdata;
		try {
			Postdata = "privatekey=" + URLEncoder.encode(Constants.Captcha.privateKey,"UTF-8") + "&remoteip=" + URLEncoder.encode(remoteip,"UTF-8") + "&challenge=" + URLEncoder.encode(challenge,"UTF-8") + "&response=" + URLEncoder.encode(respons,"UTF-8");
			URL verifyurl = new URL(Constants.Captcha.VerifyUrl);
			
			URLConnection urlconnection = verifyurl.openConnection();
			urlconnection.setDoOutput(true);
			urlconnection.setDoInput(true);
			
			
			OutputStream out = urlconnection.getOutputStream();
			out.write(Postdata.getBytes());
			out.flush();
			
			BufferedReader Reader = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
			
			if(Reader.readLine().equals("true")){
				session.setAttribute("Captcha", true);
				response.getWriter().println("true");
			}else{
				session.setAttribute("Captcha", false);
				response.getWriter().println("false");
			}
		} catch (UnsupportedEncodingException e) {
			ErrorLogging.getInstance().log(Level.SEVERE, e);
		} catch (MalformedURLException e) {
			ErrorLogging.getInstance().log(Level.SEVERE, e);
		} catch (IOException e) {
			session.setAttribute("Captcha", false);
			response.getWriter().println("false");
			ErrorLogging.getInstance().log(Level.SEVERE, e);
		}
	}

}
