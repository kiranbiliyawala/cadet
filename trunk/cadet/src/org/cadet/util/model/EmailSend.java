package org.cadet.util.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSend {

 
    public static void main(String[] args) throws Exception{
	EmailSend.SendMail("jobanputrajayn@gmail.com");
    }
 
    public static Boolean SendMail(String username) throws MessagingException {
    	
    	Pattern p = Pattern.compile(Constants.email.EmailRegex);
    	if(!p.matcher(username).matches()){
    		return false;
    	}
    	
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", Constants.email.SMTP_HOST_NAME);
        props.put("mail.smtp.port", Constants.email.SMTP_PORT);
        props.put("mail.smtp.auth", Constants.email.SMTP_AUTH);
        props.put("mail.smtp.ssl.enable", Constants.email.SMTP_SSL);
 
        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getDefaultInstance(props, auth);
        // uncomment for debugging infos to stdout
        //mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();
 
        MimeMessage message = new MimeMessage(mailSession);
 
		message.setContent(Constants.email.EmailMessage+"user="+username+"&key="+UserControl.getHashEmail(username),"text/html");
		
        message.setFrom(new InternetAddress(Constants.email.EmailUsername));
        message.setSubject(Constants.email.EmailSubject);
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(username));
 
        transport.connect();
        transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
        transport.close();
        return true;
    }
    
    
public static Boolean SendForgotMail(String username) throws MessagingException {
    	
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date dt = new Date();
	
		String datakey = UserControl.getHashEmail(username+dateFormat.format(dt));
	
    	Pattern p = Pattern.compile(Constants.email.EmailRegex);
    	if(!p.matcher(username).matches()){
    		return false;
    	}
    	
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", Constants.email.SMTP_HOST_NAME);
        props.put("mail.smtp.port", Constants.email.SMTP_PORT);
        props.put("mail.smtp.auth", Constants.email.SMTP_AUTH);
        props.put("mail.smtp.ssl.enable", Constants.email.SMTP_SSL);
 
        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getDefaultInstance(props, auth);
        // uncomment for debugging infos to stdout
        //mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();
 
        MimeMessage message = new MimeMessage(mailSession);
 
		message.setContent(Constants.email.ForgotEmailMessage+"user="+username+"&key="+datakey,"text/html");
		
        message.setFrom(new InternetAddress(Constants.email.EmailUsername));
        message.setSubject(Constants.email.EmailSubject);
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(username));
 
        transport.connect();
        transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
        transport.close();
        return true;
    }
 
    private static class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(Constants.email.EmailUsername, Constants.email.EmailPassword);
        }
    }
}
