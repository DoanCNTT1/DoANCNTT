package ute.firstproject.conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		return MySQLConnUtils.getMySQLConnection();
	}
	
	public static void closeQuietly(Connection conn)
	{
		try {
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void rollbackQuielty(Connection conn)
	{
		try {
			conn.rollback();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void excuteSql(String sql) throws ClassNotFoundException, SQLException
	{
		Connection conn = getConnection();
		PreparedStatement pstm = (PreparedStatement)conn.createStatement();
		pstm.executeUpdate(sql);
	}
	
	public ResultSet selectData(String sql) throws Exception, SQLException {
		Connection conn = getConnection();
		PreparedStatement pstm = (PreparedStatement) conn.createStatement();
		ResultSet rs = pstm.executeQuery(sql);
		return rs;
	}
	
}
