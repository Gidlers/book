package com.it.dao.idao;

import java.io.Serializable;
import java.util.List;

import com.it.dao.entity.Details;

public interface DetailsDao<E> {
	// �ж�
	String judgeDetails(Details det);

	// ����
	String insertdetails(E det);

	// ɾ������
	int deleteDetails(String login_id, int state);

	// ɾ������
	int deleteDetail(Serializable id, String login_id);

	// ��ѯ����
	List<E> selDetails(String login_id, int state);

	// ��ѯ����
	E getDetailsById(String login_id, String book_id);

	Details getDetById(Serializable id);

	// �޸�
	int updatedetails(E det);

	int payDetails(Serializable id);

	// ��ҳ
	List<E> pageDetails(int currPage,String login_id,int state);

	// ��ʾҳ��
	//int getPage(Details det);
}
