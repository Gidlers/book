package com.it.dao.idao;

import java.io.Serializable;
import java.util.List;

import com.it.dao.entity.Book;

public interface IOAO<E> {

	List<E> selrctAll();

//��ѯ����
	E getById(Serializable id);

	// ����
	List<E> getByName(String keyword);

	List<E> nextByNamePage(String keyword, int currPage);

//��ҳ
	List<E> nextPage(int currPage, int limit);

//��ʾҳ��
	int getPage(int pagelist);

	int insertBook(Book book);

	

	int deleteById(Serializable id);

	int updateBook(Book book);

}
