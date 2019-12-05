package sql;

import beans.OwnedStock;
import beans.User;
import org.apache.commons.dbutils.BeanProcessor;
import util.SQLUtil;

import java.sql.*;
import java.util.List;

public class StockSql {

	public Long create(OwnedStock stock) {
		Long id = -1L;
		Connection conn = SQLUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "insert into stocksOwned ( " +
					"symbol, quantity, userId " +
					") values (?, ?, ?) ";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, stock.getSymbol());
			stmt.setInt(2, stock.getQuantity());
			stmt.setLong(3, stock.getUserId());
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

	public Long updateQuantity(String symbol, Long userId, int quantity) {
		Long id = -1L;
		Connection conn = SQLUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "update stocksOwned set quantity = ? where symbol = ? and userId = ?";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, quantity);
			stmt.setString(2, symbol);
			stmt.setLong(3, userId);
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

	public OwnedStock readStock(String symbol, Long userId) {
		OwnedStock ownedStock = null;
		Connection conn = SQLUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from stocksOwned where symbol = ? and userId = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, symbol);
			stmt.setLong(2, userId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				BeanProcessor bp = new BeanProcessor();
				ownedStock = bp.toBean(rs, OwnedStock.class);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database failed to query.");
		} finally {
			SQLUtil.close(conn, stmt, rs);
		}
		return ownedStock;
	}

	public List<OwnedStock> readOwnedStocks(Long userId) {
		List<OwnedStock> ownedStock = null;
		Connection conn = SQLUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from stocksOwned where userId = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				BeanProcessor bp = new BeanProcessor();
				ownedStock = bp.toBeanList(rs, OwnedStock.class);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database failed to query.");
		} finally {
			SQLUtil.close(conn, stmt, rs);
		}
		return ownedStock;
	}

}
