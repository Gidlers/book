package com.it.dao.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it.dao.entity.Book;
import com.it.dao.entity.Details;
import com.it.dao.idao.DetailsDao;
import com.it.dao.idao.IOAO;
import com.it.dao.manager.BookManager;
import com.it.dao.manager.DetailsManager;
import com.it.tom.TomcatDataSource;
import com.it.tool.Tool;

/**
 * Servlet implementation class JdbcServlet
 */
@WebServlet(value = "/buy_book")
@SuppressWarnings("all")
public class BuyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static IOAO<Book> dao = new BookManager();
	private static DetailsDao<Details> daoDe = new DetailsManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("buy".equals(action)) {
			buy(request, response);
		} else if ("add".equals(action)) {
			add(request, response);
		} else if ("sub".equals(action)) {
			sub(request, response);
		} else if ("clear".equals(action)) {
			clearBook(request, response);
		} else if ("del".equals(action)) {
			delete(request, response);
		} else if ("pay".equals(action)) {
			pay(request, response);
		} else if ("page".equals(action)) {
			page(request, response);
		} else {
			refresh(request, response);
		}

	}

	// 刷新
	private void refresh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login_id = (String) request.getSession().getAttribute("login_id");
		int currPage1 = 1;
		int currPage2 = 1;
		if (request.getSession().getAttribute("currPage1") != null) {

			currPage1 = (int) request.getSession().getAttribute("currPage1");
		} else {
			
			request.getSession().setAttribute("currPage1", 1);
		}
		if (request.getSession().getAttribute("currPage2") != null) {
			currPage2 = (int) request.getSession().getAttribute("currPage2");
		} else {
			request.getSession().setAttribute("currPage2", 1);
		}
		
		List<Details> cart_details = daoDe.pageDetails(currPage1, login_id, 0);
		List<Details> pay_details = daoDe.pageDetails(currPage2, login_id, 1);
		
		
		Map<Details, Book> cart = new TreeMap<>((o1, o2) -> o1.getBook_id().compareTo(o2.getBook_id()));
		Map<Details, Book> pay_cart = new TreeMap<>((o1, o2) -> o1.getBook_id().compareTo(o2.getBook_id()));

		if (cart_details == null || cart_details.isEmpty()) {
			cart = null;
		} else {
			for (Details detail : cart_details) {
				Book book = dao.getById(detail.getBook_id());
				if (book != null) {
					cart.put(detail, book);
				} else {
					daoDe.deleteDetail(detail.getId(), login_id);
				}
			}
		}
		if (pay_details == null) {
			pay_cart = null;
		} else {
			for (Details det : pay_details) {
				Book book = dao.getById(det.getBook_id());
				if (book != null) {
					pay_cart.put(det, book);
				} else {
					daoDe.deleteDetail(det.getId(), login_id);
				}
			}
		}

		if (pay_cart == null || pay_cart.isEmpty()) {

			request.getSession().setAttribute("pay_cart", null);
		} else {
			request.getSession().setAttribute("pay_cart", pay_cart);
		}

		if (cart == null || cart.isEmpty()) {
			request.getSession().setAttribute("cart", null);
		} else {
			double price = price(cart);
			request.getSession().setAttribute("money", price);
			request.getSession().setAttribute("cart", cart);
		}

		response.sendRedirect(request.getContextPath() + "/jsp_operat/details.jsp");

	}

	private void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login_id = (String) request.getSession().getAttribute("login_id");
		int state = 0;
		List<Details> cart_details = new ArrayList<>();
		List<Details> pay_details = new ArrayList<>();

		if (request.getParameter("state") == null) {
			refresh(request, response);
		} else {

			state = Integer.parseInt(request.getParameter("state"));
			String order = request.getParameter("order");
			int currPage1 = (int) request.getSession().getAttribute("currPage1");
			int currPage2 = (int) request.getSession().getAttribute("currPage2");
			if (state == 0) {
				if (daoDe.selDetails(login_id, 0) != null) {
					int totalpage1 = Tool.upLimit(daoDe.selDetails(login_id, 0).size() * 1.0 / 3);

					currPage1 = pages(currPage1, totalpage1, order);
					cart_details = daoDe.pageDetails(currPage1, login_id, 0);
					pay_details = daoDe.pageDetails(currPage2, login_id, 1);
					request.getSession().setAttribute("currPage1", currPage1);
				} else {
					cart_details = null;
				}
			} else {
				if (daoDe.selDetails(login_id, 1) != null) {
					int totalpage2 = Tool.upLimit(daoDe.selDetails(login_id, 1).size() * 1.0 / 3);
					currPage2 = pages(currPage2, totalpage2, order);
					

					cart_details = daoDe.pageDetails(currPage1, login_id, 0);
					pay_details = daoDe.pageDetails(currPage2, login_id, 1);
					request.getSession().setAttribute("currPage2", currPage2);
				} else {
					pay_details = null;
				}
			}

			request.getSession().setAttribute("cart_details", cart_details);
			request.getSession().setAttribute("pay_details", pay_details);
			refresh(request, response);
		}
	}

	private int pages(int currPage, int totalpage, String order) {
		if ("f".equals(order)) {
			currPage = 1;
		} else if ("u".equals(order)) {
			if (currPage > 1) {
				currPage = currPage - 1;
			} else {
				currPage = 1;
			}
		} else if ("n".equals(order)) {
			if (currPage < totalpage) {
				currPage = currPage + 1;
			} else {
				currPage = totalpage;
			}
		} else if ("l".equals(order)) {
			currPage = totalpage;
		}
		return currPage;
	}

	private void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login_id = (String) request.getSession().getAttribute("login_id");
		String id = request.getParameter("id");
		if ("All".equals(id)) {
			List<Details> list = daoDe.selDetails(login_id, 0);
			list.forEach(a -> daoDe.payDetails(a.getId()));
		} else {
			daoDe.payDetails(id);
		}
		refresh(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String login_id = (String) request.getSession().getAttribute("login_id");
		Details det = daoDe.getDetById(id);

		if (det.getState() == 1) {
			daoDe.deleteDetail(id, login_id);
		} else {
			update(det.getBook_id(), det.getValue());
			daoDe.deleteDetail(id, login_id);
		}
		refresh(request, response);
	}

//清空
	private void clearBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login_id = (String) request.getSession().getAttribute("login_id");
		List<Details> list = daoDe.selDetails(login_id, 0);
		for (Details details : list) {
			update(details.getBook_id(), details.getValue());
		}
		daoDe.deleteDetails(login_id, 0);
		refresh(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login_id = (String) request.getSession().getAttribute("login_id");
		String book_id = request.getParameter("id");
		Book book = dao.getById(book_id);
		Details det = daoDe.getDetailsById(login_id, book_id);

		if (det.getValue() >= book.getQty()) {
			det.setValue(book.getQty());
		} else {
			updateDet(det, 1);
		}
		refresh(request, response);
	}

	private void sub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String book_id = request.getParameter("id");
		String login_id = (String) request.getSession().getAttribute("login_id");
		Details det = new Details();
		det = daoDe.getDetailsById(login_id, book_id);

		if (det.getValue() - 1 <= 0) {
			daoDe.deleteDetail(det.getId(), login_id);
			update(book_id, 1);
		} else {
			updateDet(det, -1);
		}

		refresh(request, response);
	}

	protected double price(Map<Details, Book> cart) {
		double money = 0;
		for (Map.Entry<Details, Book> e : cart.entrySet()) {
			Book book1 = e.getValue();
			double price = book1.getPrice();
			double value = e.getKey().getValue();
			money += price * value;
		}

		return money;
	}

	private void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String book_id = request.getParameter("id");
		String us = request.getParameter("us");
		String login_id = (String) request.getSession().getAttribute("login_id");
		Book book = dao.getById(book_id);
		Details det = new Details();

		if (book.getQty() > 0) {
			if (daoDe.getDetailsById(login_id, book_id) == null) {
				det.setBook_id(book_id);
				det.setLogin_id(login_id);
				det.setValue(0);
			} else {
				det = daoDe.getDetailsById(login_id, book_id);
			}

			updateDet(det, 1);
		}

		if ("us".equals(us)) {
			response.sendRedirect(request.getContextPath() + "/book?us=us");
		} else {
			us = null;
			response.sendRedirect(request.getContextPath() + "/book");
		}
	}

	protected void updateDet(Details det, int i) {

		Details det2 = new Details();

		det2.setLogin_id(det.getLogin_id());
		det2.setBook_id(det.getBook_id());
		det2.setValue(det.getValue() + i);

		update(det.getBook_id(), -i);
		daoDe.updatedetails(det2);

	}

	protected void update(String id, int i) {
		Book book = dao.getById(id);

		Book b = new Book();
		b.setName(book.getName());
		b.setAuthor(book.getAuthor());
		b.setPrice(book.getPrice());
		int result = book.getQty() + i;
		if (result < 0) {
			result = 0;
		}
		b.setQty(result);
		b.setId(id);
		dao.updateBook(b);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		Tool.release(TomcatDataSource.getDataSource());
	}
}
