package com.icss.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.icss.biz.UserBiz;
import com.icss.dto.LoginUser;
import com.icss.exception.InputNullException;

public class LoginSvl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginSvl() {
		super();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// 读取配置文件中的参数信息，进行对象初始化
		//在servlet的构造函数中config对象为null		
	}

	
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
        request.getRequestDispatcher("/WEB-INF/views/main/login.jsp").forward(request, response);	
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd"); 		
		UserBiz biz = new UserBiz();
		String msg = null;		
		try {			
			LoginUser user = biz.login(uname, pwd);	
			if(user != null){				
				//登陆成功，转到main.jsp,需要把登录者的个人信息传到主页
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("rights", user.getAllRights());
				request.getRequestDispatcher("/WEB-INF/views/main/main.jsp").forward(request, response);
			}else{
				msg = "你的用户名、密码错误，请重新输入";
				//登陆失败，转到login.jsp，继续登陆
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/WEB-INF/views/main/login.jsp").forward(request, response);
			}
		} catch (InputNullException e) {
			msg = e.getMessage();
			//输入信息为空，转到登陆页
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/views/main/login.jsp").forward(request, response);			
		} catch (Exception e) {
			msg = "网络连接异常，请和网络运营商联系";
			//系统异常，转到错误页
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/views/error/error.jsp").forward(request, response);			
		}		
	}


}
