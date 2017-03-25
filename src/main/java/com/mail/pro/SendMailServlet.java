package com.mail.pro;

import java.io.IOException;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class SendMailServlet extends HttpServlet{
	protected Log logger = LogFactory.getLog(getClass());
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName ="eddy";
		String password  = "";
		String mailTo = request.getParameter("mailTo");
		String mailTopic = request.getParameter("mailTopic");
		String mailContent = request.getParameter("mailContent");
		if(mailTo == null || "".equals(mailTo)){
			return;
		}
		String mailToArray[] = mailTo.split(";");
		try{
			Address addresses[] = new Address[mailToArray.length];
			for(int i =0; i< mailToArray.length; i++){
				Address address = new InternetAddress(mailToArray[i]);
				addresses[i] = address;
			}
		}catch(Exception e){
			logger.debug("A270 ·¢ËÍÓÊ¼þÊ§°Ü");
		}
		
	}

}
