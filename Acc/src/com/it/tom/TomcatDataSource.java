package com.it.tom;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public final class TomcatDataSource {
	private static final ThreadLocal<DataSource> THREAD_LOCAL = new ThreadLocal<>();

	private TomcatDataSource() {
	}

	public static DataSource getDataSource() {

		if (THREAD_LOCAL.get() == null) {
			try {
				Context context = new InitialContext();
				DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/mysql");
				THREAD_LOCAL.set(ds);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return THREAD_LOCAL.get();
	}

}
