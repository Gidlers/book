package com.it.dao.manager;

import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.it.dao.entity.Account;
import com.it.dao.idao.UploadDao;
import com.it.tom.TomcatDataSource;
import com.it.tool.Tool;

public class UploadManger implements UploadDao<Account> {
	private final DataSource ds;
	private final QueryRunner runner;

	public UploadManger() {
		ds = TomcatDataSource.getDataSource();
		this.runner = new QueryRunner(ds);
	}

	@Override
	public boolean register(Account account) {
		Objects.requireNonNull(account);
		String string = UUID.randomUUID().toString().toUpperCase();
		int update = 0;
		String password = account.getPassword();
		for (int i = 0; i < 2; i++) {
			password = Tool.getEncoder(password);
		}
		// password = Tool.getMd5(password);
		try {
			update = runner.update("insert into tb_user(id,username,password) values(?,?,?)", string,
					account.getUsername(), password);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return update > 0;
	}

	@Override
	public String login(Account account, String tab) {
		// TODO 自动生成的方法存根
		Objects.requireNonNull(account);
		Account a = null;
		try {
			a = runner.query("select * from " + tab + " where username=? ", new BeanHandler<Account>(Account.class),
					account.getUsername());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		// String md5 = Tool.getMd5(password);
		if (a == null) {
			return null;
		}

		String password = a.getPassword();
		for (int i = 0; i < 2; i++) {
			password = Tool.getDecoder(password);
		}
		if ((account.getPassword()).equals(password)) {
			return a.getId();
		} else {
			return null;
		}
	}

}
