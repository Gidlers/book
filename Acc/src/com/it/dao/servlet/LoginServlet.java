package com.it.dao.servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.dao.entity.Account;
import com.it.dao.idao.UploadDao;
import com.it.dao.manager.UploadManger;
import com.it.tom.TomcatDataSource;
import com.it.tool.Tool;

/**
 * Servlet implementation class JdbcServlet
 */
@WebServlet(value = "/account")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Account aa = new Account();

	UploadDao<Account> acd = new UploadManger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("login".equals(action)) {
			login(request, response);
		} else if ("register".equals(action)) {
			register(request, response);
		}

	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String tab = request.getParameter("select");

		if (Objects.equals(tab, "tb_admin")) {
			request.getSession().setAttribute("jsp", "/jsp/index_ad.jsp");
		} else if (Objects.equals(tab, "tb_user")) {
			request.getSession().setAttribute("jsp", "/jsp/index_us.jsp");
		}

		if (username == "") {
			request.getSession().setAttribute("vars", "username");
			response.sendRedirect("jsp/login.jsp");
		} else if (password == "") {
			request.getSession().setAttribute("vars", "password");
			response.sendRedirect("jsp/login.jsp");
		} else {
			aa.setUsername(username);
			aa.setPassword(password);
			String login_id = acd.login(aa, tab);

			if (login_id != null) {
				request.getSession().setAttribute("login_id", login_id);
				request.getRequestDispatcher("/book").forward(request, response);
			} else {
				request.getSession().setAttribute("vars", "err");
				response.sendRedirect("jsp/login.jsp");
			}
		}

	}

	protected void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");

		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");

		String userRegex = "[1-9a-zA-Z][0-9a-zA-Z]{4,15}";
		String passRegex = "(?![a-zA-Z]+$)(?![0-9]+$).{6,20}";

		if (!username.matches(userRegex)) {
			response.getWriter()
					.write("<script>alert('账号只能为5-16位英文或者数字  ');" + "location.href='jsp/register.jsp';</script>");
		} else if (!password1.matches(passRegex)) {
			response.getWriter().write("<script language=javascript>alert('密码只能为6-20,必须包含英文和数字   ');"
					+ "location.href='jsp/register.jsp';</script>");
		} else if (!(Objects.equals(password1, password2))) {
			response.getWriter().write("<script language=javascript>alert('两次密码不一样，请重新输入   ');"
					+ "location.href='jsp/register.jsp';</script>");

		} else {
			aa.setUsername(username);
			aa.setPassword(password1);
			boolean bool = acd.register(aa);
			if (bool) {
				response.getWriter().write("<script language=javascript>alert('注册成功 ,即将返回登录页面...');"
						+ "location.href='jsp/login.jsp';</script>");
			} else {
				response.getWriter().write("<script language=javascript>alert('用户名已经存在，请重试!!');"
						+ "location.href='jsp/login.jsp';</script>");

			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		System.out.println("销毁");
		Tool.release(TomcatDataSource.getDataSource());
	}
}
