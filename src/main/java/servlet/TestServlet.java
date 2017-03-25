package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestServlet extends HttpServlet{
	 final static Logger logger = LoggerFactory.getLogger(TestServlet.class);
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.setAttribute("user", "ADD");
		logger.debug("req forword user.jsp");
		logger.info("Entering application.");
        logger.info("Exiting application.");
        logger.debug("this debug log hon.");
        logger.error("this debug log _class.");
		req.getRequestDispatcher("user.jsp").forward(req, res);
	}
	
}
