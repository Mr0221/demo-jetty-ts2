package com.icss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.dto.LoginUser;

public class UserFileter implements Filter{

	public void destroy() {
		
	}

	/**
	 * ���˵�¼��LogiSvl�;�̬js/css/jpg�ȣ�����servlet����Ҫ�ж��Ƿ��¼��
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;		 
		HttpServletResponse response = (HttpServletResponse)servletResponse;		
		LoginUser user  = (LoginUser)request.getSession().getAttribute("user");
		if(user != null){
			chain.doFilter(request, response);
		}else{
			request.setAttribute("msg","��������ҳ�棬���ȵ�¼");
			request.getRequestDispatcher("/WEB-INF/views/main/login.jsp").forward(request, response);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
