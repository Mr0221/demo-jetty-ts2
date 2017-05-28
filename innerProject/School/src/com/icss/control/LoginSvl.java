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
		// ��ȡ�����ļ��еĲ�����Ϣ�����ж����ʼ��
		//��servlet�Ĺ��캯����config����Ϊnull		
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
				//��½�ɹ���ת��main.jsp,��Ҫ�ѵ�¼�ߵĸ�����Ϣ������ҳ
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("rights", user.getAllRights());
				request.getRequestDispatcher("/WEB-INF/views/main/main.jsp").forward(request, response);
			}else{
				msg = "����û����������������������";
				//��½ʧ�ܣ�ת��login.jsp��������½
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/WEB-INF/views/main/login.jsp").forward(request, response);
			}
		} catch (InputNullException e) {
			msg = e.getMessage();
			//������ϢΪ�գ�ת����½ҳ
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/views/main/login.jsp").forward(request, response);			
		} catch (Exception e) {
			msg = "���������쳣�����������Ӫ����ϵ";
			//ϵͳ�쳣��ת������ҳ
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/views/error/error.jsp").forward(request, response);			
		}		
	}


}
