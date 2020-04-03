package com.it.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class Myfilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding("GBK");
		response.setCharacterEncoding("GBK");
		response.setContentType("text/html");
		// Ö´ÐÐ¹ýÂËÁ´
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String path = req.getRequestURI();

		if ((req.getSession().getAttribute("login_id") != null) || "/Acc/jsp/login.jsp".equalsIgnoreCase(path)
				|| "/Acc/account".equals(path) || "/Acc/css/book.css".equals(path)
				|| "/Acc/jsp/register.jsp".equals(path) || "/Acc/jsp/test.jsp".equals(path)) {

			if ("/jsp/index_us.jsp".equals(req.getSession().getAttribute("jsp"))
					&& (("/Acc/jsp/index_ad.jsp".equals(path))
					||"/Acc/jsp_operat/insert.jsp".equals(path)
					||"/Acc/jsp_operat/update.jsp".equals(path)
					) ){
				resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");

			} else
				chain.doFilter(request, response);
		}

		else {
			resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
