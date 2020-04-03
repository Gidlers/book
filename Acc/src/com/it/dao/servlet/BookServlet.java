package com.it.dao.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.dao.entity.Book;
import com.it.dao.idao.IOAO;
import com.it.dao.manager.BookManager;
import com.it.tom.TomcatDataSource;
import com.it.tool.Tool;

/**
 * Servlet implementation class JdbcServlet
 */
@WebServlet(value = "/book")
public class BookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int PAGE_SIZE = 5;
	private static IOAO<Book> dao = new BookManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("redirectUpdate".equals(action)) {
			redirectUpdate(request, response);

		}

		else if ("delete".equals(action)) {
			delete(request, response);
		} else if ("insert".equals(action)) {
			insert(request, response);
		} else if ("update".equals(action)) {
			update(request, response);
		} else if ("search".equals(action)) {
			search(request, response);
		} else if ("logout".equals(action)) {
			logout(request, response);
		} else if ("alterpage".equals(action)) {
			pageRefresh(request, response);
		} else {
			getBooks(request, response);

		}

	}

	// ��ҳ
	private void pageRefresh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int total = (int) request.getSession().getAttribute("totalPage");
		int currPage = (int) request.getSession().getAttribute("currPage");
		String page = request.getParameter("page");

		int totalPage = 0;
		if (total == 0) {
			totalPage = dao.getPage(PAGE_SIZE);
		} else {
			totalPage = total;
		}
		// ָ��
		if (currPage == 0) {
			currPage = 1;
		}

		List<Book> books = null;
		if ("first".equals(page)) {
			currPage = 1;
		} else if ("last".equals(page)) {
			currPage = totalPage;
		} else if ("next".equals(page)) {
			if (currPage < totalPage) {
				currPage = currPage + 1;
			}
		} else if ("up".equals(page)) {
			if (currPage > 1) {
				currPage = currPage - 1;
			}
		}
		// ������ҳ
		if (total == 0) {
			books = page(currPage);
			request.getSession().setAttribute("book", books);
		} else {
			String keyword = (String) request.getSession().getAttribute("keyword");
			books = dao.nextByNamePage(keyword, currPage);

			request.getSession().setAttribute("book", books);
			request.getSession().setAttribute("totalPage", total);
		}

		request.getSession().setAttribute("currPage", currPage);
		address(request, response);
	}

	private List<Book> page(int currPage) {
		return dao.nextPage(currPage, PAGE_SIZE);
	}

	/*
	 * ����
	 */
	protected void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("name");
		request.getSession().setAttribute("currPage", 1);
		request.getSession().setAttribute("totalPage", 0);
		keyword = keyword.trim();

		if (keyword == null || keyword.isEmpty()) {
			request.getSession().setAttribute("keyword", null);
			pageRefresh(request, response);
		} else {
			List<Book> list = dao.getByName(keyword);
			request.getSession().setAttribute("keyword", keyword);
			if (list != null) {
				request.getSession().setAttribute("book", list);
				int totalPage = Tool.upLimit(list.size() * 1.0 / PAGE_SIZE);

				request.getSession().setAttribute("totalPage", totalPage);
				pageRefresh(request, response);
			} else {
				request.getSession().setAttribute("book", null);
				address(request, response);
			}
		}

	}

//��������
	protected void redirectUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Book b = dao.getById(id);
		request.getSession().setAttribute("book", b);
		response.sendRedirect("jsp_operat/update.jsp");

	}

	/*
	 * ��ȡ���º�ı�
	 */
	protected void getBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("totalPage", 0);
		request.getSession().setAttribute("currPage", 1);
		request.getSession().setAttribute("keyword", null);

		List<Book> list = page(1);
		request.getSession().setAttribute("book", list);
		address(request, response);
	}

	protected void address(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String us = request.getParameter("us");

		if ("us".equals(us) || ("jsp".equals(action))) {
			us = null;
			response.sendRedirect(request.getContextPath() + "/jsp/index_us.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + request.getSession().getAttribute("jsp"));
		}
	}

	/*
	 * �޸�
	 */
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String prices = request.getParameter("price");
		String qtys = request.getParameter("qty");

		double price = 0;
		int qty = 0;
		if ((prices != null) && (!prices.isEmpty())) {
			price = Double.parseDouble(prices);
		}
		if ((qtys != null) && (!qtys.isEmpty())) {
			qty = Integer.parseInt(qtys);
		}

		String id = request.getParameter("id");
		Book b = new Book();
		b.setName(name);
		b.setAuthor(author);
		b.setPrice(price);
		b.setQty(qty);
		b.setId(id);
		int i = dao.updateBook(b);
		if (i == 0) {
			response.getWriter().write("<script language=javascript>alert('�鼮�޸�ʧ�ܣ���������д��Ч��Ϣ  ');"
					+ "location.href='jsp_operat/update.jsp';</script>");
		} else {
			getBooks(request, response);
		}
	}

	/*
	 * ���
	 */
	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String prices = request.getParameter("price");
		String qtys = request.getParameter("qty");
		name = name.trim();
		author = author.trim();

		double price = 0;
		int qty = 0;
		if ((prices != null) && (!prices.isEmpty())) {
			price = Double.parseDouble(prices);
		}
		if ((qtys != null) && (!qtys.isEmpty())) {
			qty = Integer.parseInt(qtys);
		}
		Book b = new Book();
		if (name.isEmpty() || name == null) {
			response.getWriter().write("<script language=javascript>alert('��������Ϊ�գ���������д��Ч��Ϣ  ');"
					+ "location.href='jsp_operat/insert.jsp';</script>");
		} else {
			b.setName(name);
			b.setAuthor(author);
			b.setPrice(price);
			b.setQty(qty);

			dao.insertBook(b);
			getBooks(request, response);
		}

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		dao.deleteById(id);
		getBooks(request, response);

	}

	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("jsp/login.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void destroy() {

		Tool.release(TomcatDataSource.getDataSource());
	}
}
