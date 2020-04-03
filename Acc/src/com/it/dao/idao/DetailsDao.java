package com.it.dao.idao;

import java.io.Serializable;
import java.util.List;

import com.it.dao.entity.Details;

public interface DetailsDao<E> {
	// 判断
	String judgeDetails(Details det);

	// 插入
	String insertdetails(E det);

	// 删除所有
	int deleteDetails(String login_id, int state);

	// 删除单行
	int deleteDetail(Serializable id, String login_id);

	// 查询所有
	List<E> selDetails(String login_id, int state);

	// 查询单行
	E getDetailsById(String login_id, String book_id);

	Details getDetById(Serializable id);

	// 修改
	int updatedetails(E det);

	int payDetails(Serializable id);

	// 分页
	List<E> pageDetails(int currPage,String login_id,int state);

	// 显示页数
	//int getPage(Details det);
}
