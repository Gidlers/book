package com.it.dao.idao;

import com.it.dao.entity.Account;

public interface UploadDao<E> {

	boolean register(Account account);

	String login(Account account, String tab);

	


}
