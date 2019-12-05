package sql;

import beans.User;
import org.apache.commons.dbutils.BeanProcessor;
import util.SQLUtil;

import java.sql.*;

public class UserSql {

	public Long create(User user) {
		Long id = -1L;
		Connection conn = SQLUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "insert into users ( " +
					"username, password, name, email " +
					") values (?, ?, ?, ?) ";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());
			stmt.setString(4, user.getEmail());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next())
				id = rs.getLong(1);
		} catch (SQLException e) {
			System.out.println("Database failed to update");
			e.printStackTrace();
		} finally {
			SQLUtil.close(conn, stmt, rs);
		}
		return id;
	}

	public User readByUsername(String username) {
		User user = null;
		Connection conn = SQLUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from users where username = ?  ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			if (rs.next()) {
				BeanProcessor bp = new BeanProcessor();
				user = bp.toBean(rs, User.class);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database failed to query.");
		} finally {
			SQLUtil.close(conn, stmt, rs);
		}
		return user;
	}

	public User readByUsernameAndPassword(String username, String password) {
		User user = null;
		Connection conn = SQLUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from users where username = ? and password = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				BeanProcessor bp = new BeanProcessor();
				user = bp.toBean(rs, User.class);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database failed to query.");
		} finally {
			SQLUtil.close(conn, stmt, rs);
		}
		return user;
	}

	public Long updateMoney(double money) {
		Long id = -1L;
		Connection conn = SQLUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "update users set money = ? ";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setDouble(1, money);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next())
				id = rs.getLong(1);
		} catch (SQLException e) {
			System.out.println("Database failed to update");
			e.printStackTrace();
		} finally {
			SQLUtil.close(conn, stmt, rs);
		}
		return id;
	}
}
