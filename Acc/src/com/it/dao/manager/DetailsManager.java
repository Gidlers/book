package com.it.dao.manager;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.it.dao.entity.Details;
import com.it.dao.idao.DetailsDao;
import com.it.tom.TomcatDataSource;

public class DetailsManager implements DetailsDao<Details> {

	private final DataSource ds;
	private final QueryRunner runner;

	public DetailsManager() {
		ds = TomcatDataSource.getDataSource();
		this.runner = new QueryRunner(ds);
	}

	@Override
	public String insertdetails(Details det) {
		String string = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		try {

			runner.update("insert into details values(?,?,?,?,0)", string, det.getLogin_id(), det.getBook_id(),
					det.getValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return string;

	}

	@Override
	public List<Details> selDetails(String login_id, int state) {

		List<Details> det = null;
		try {
			det = runner.query("select * from details  where login_id=? and state=? order by book_id",
					new BeanListHandler<Details>(Details.class), login_id, state);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return det;
	}

	@Override
	public int updatedetails(Details det) {
		String id = judgeDetails(det);
		int update = 0;

		try {
			update = runner.update("update details set value=? where id=?", det.getValue(), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public int deleteDetails(String login_id, int state) {
		int update = 0;
		try {
			update = runner.update("delete from details where login_id=? and state=0", login_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public int deleteDetail(Serializable id, String login_id) {
		int update = 0;
		try {
			update = runner.update("delete from details where id=? and login_id=?", id, login_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public String judgeDetails(Details det) {
		Details details = getDetailsById(det.getLogin_id(), det.getBook_id());
		if (details == null) {
			return insertdetails(det);
		}
		return details.getId();
	}

	@Override
	public Details getDetailsById(String login_id, String book_id) {
		Details det = null;
		try {
			det = runner.query("select * from details where login_id=? and book_id=? and state=0",
					new BeanHandler<Details>(Details.class), login_id, book_id);
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		return det;
	}

	@Override
	public int payDetails(Serializable id) {
		Details details = getDetById(id);
		int value = details.getValue();

		List<Details> list = selDetails(details.getLogin_id(), 1);
		int update = 0;
		if (list != null) {
			for (Details det : list) {
				if (det.getBook_id().equals(details.getBook_id())) {
					value += det.getValue();
					deleteDetail(det.getId(), det.getLogin_id());
				}
			}
		}
		try {
			update = runner.update("update details set value=?,state=1 where id=?", value, id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return update;
	}

	@Override
	public Details getDetById(Serializable id) {
		Details det = null;
		try {
			det = runner.query("select * from details where id=?", new BeanHandler<Details>(Details.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return det;
	}

	@Override
	public List<Details> pageDetails(int currPage, String login_id, int state) {
		currPage = (currPage - 1) * 3;
		List<Details> list = null;
		try {
			list = runner.query("select * from details where login_id=? and state=? order by id limit ?,3",
					new BeanListHandler<Details>(Details.class), login_id, state, currPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
