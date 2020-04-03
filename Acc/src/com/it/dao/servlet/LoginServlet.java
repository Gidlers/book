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
					.write("<script>alert('�˺�ֻ��Ϊ5-16λӢ�Ļ�������  ');" + "location.href='jsp/register.jsp';</script>");
		} else if (!password1.matches(passRegex)) {
			response.getWriter().write("<script language=javascript>alert('����ֻ��Ϊ6-20,�������Ӣ�ĺ�����   ');"
					+ "location.href='jsp/register.jsp';</script>");
		} else if (!(Objects.equals(password1, password2))) {
			response.getWriter().write("<script language=javascript>alert('�������벻һ��������������   ');"
					+ "location.href='jsp/register.jsp';</script>");

		} else {
			aa.setUsername(username);
			aa.setPassword(password1);
			boolean bool = acd.register(aa);
			if (bool) {
				response.getWriter().write("<script language=javascript>alert('ע��ɹ� ,�������ص�¼ҳ��...');"
						+ "location.href='jsp/login.jsp';</script>");
			} else {
				response.getWriter().write("<script language=javascript>alert('�û����Ѿ����ڣ�������!!');"
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
		// TODO �Զ����ɵķ������
		System.out.println("����");
		Tool.release(TomcatDataSource.getDataSource());
	}
}
