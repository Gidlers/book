package com.it.dao.idao;

import java.io.Serializable;
import java.util.List;

import com.it.dao.entity.Book;

public interface IOAO<E> {

	List<E> selrctAll();

//查询单个
	E getById(Serializable id);

	// 搜索
	List<E> getByName(String keyword);

	List<E> nextByNamePage(String keyword, int currPage);

//分页
	List<E> nextPage(int currPage, int limit);

//显示页数
	int getPage(int pagelist);

	int insertBook(Book book);

	

	int deleteById(Serializable id);

	int updateBook(Book book);

}
