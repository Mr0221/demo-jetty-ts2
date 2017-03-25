package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JettyServlet extends HttpServlet {
	/***
	 * erer
	 */
	@Override
	protected void service(final HttpServletRequest req, final HttpServletResponse res)
			throws ServletException, IOException {
		req.setAttribute("user", "ad");
		req.getRequestDispatcher("user.jsp").forward(req, res);
	}
}
