package com.it.dao.manager;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.it.dao.entity.Book;
import com.it.dao.idao.IOAO;
import com.it.tom.TomcatDataSource;
import com.it.tool.Tool;

public class BookManager implements IOAO<Book> {

	private final DataSource ds;
	private final QueryRunner runner;

	public BookManager() {
		ds = TomcatDataSource.getDataSource();
		this.runner = new QueryRunner(ds);
	}

	@Override
	public List<Book> selrctAll() {

		List<Book> list = null;
		try {
			list = runner.query("select * from books order by name", new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public Book getById(Serializable id) {

		Book book = null;
		try {
			book = runner.query("select * from books where id=?", new BeanHandler<Book>(Book.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return book;
	}

	@Override
	public List<Book> nextPage(int currPage, int limit) {
		currPage = (currPage - 1) * limit;
		List<Book> list = null;
		try {
			list = runner.query("select * from books order by name limit ?,? ", new BeanListHandler<Book>(Book.class),
					currPage, limit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public int deleteById(Serializable id) {
		int rows = 0;
		try {
			rows = runner.update("delete from books where id=?", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int insertBook(Book book) {
		double pri = book.getPrice();
		int qty = book.getQty();
		if (book.getPrice() < 0) {
			pri = 1;
		}
		if (book.getQty() < 0) {
			qty = 0;
		}

		String string = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		int update = 0;
		try {
			update = runner.update("insert into books values(?,?,?,?,?)", string, book.getName(), book.getAuthor(), pri,
					qty);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return update;
	}

	@Override
	public int updateBook(Book book) {

		if (book.getPrice() <= 0) {
			return 0;
		}
		if (book.getQty() < 0) {
			return 0;
		}

		if (book.getName().isEmpty() || book.getName() == null) {
			return 0;
		}

		int update = 0;
		try {
			update = runner.update("update books set name=?,author=?,price=?,qty=? where id=?", book.getName(),
					book.getAuthor(), book.getPrice(), book.getQty(), book.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return update;
	}

	@Override
	public int getPage(int pageSize) {
		long totalRecord = 0;
		try {
			totalRecord = runner.query("select count(*) from books order by name", new ScalarHandler<Long>());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Tool.upLimit((totalRecord * 1.0 / pageSize));
	}

//ËÑË÷µÄ·ÖÒ³
	@Override
	public List<Book> nextByNamePage(String keyword, int currPage) {
		currPage = (currPage - 1) * 5;
		List<Book> book = null;
		try {
			book = runner.query("select * from books where name like ? or author like ? order by name limit ?,5",
					new BeanListHandler<Book>(Book.class), "%" + keyword + "%", "%" + keyword + "%", currPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (book.size() == 0) {
			book = null;
		}
		return book;

	}

	// ËÑË÷
	@Override
	public List<Book> getByName(String keyword) {

		List<Book> book = null;
		try {
			book = runner.query("select * from books where name like ? or author like ?",
					new BeanListHandler<Book>(Book.class), "%" + keyword + "%", "%" + keyword + "%");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (book.size() == 0) {
			book = null;
		}
		return book;
	}

}