package ute.firstproject.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException
	{
		String hostName="localhost";
		String dbName="firstproject";
		String userName="root";
		String password="giakhang123";
		return getMySQLConnection(hostName, dbName, userName, password);
		
	}
	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String connectionURL= "jdbc:mysql://" + hostName + ":3306/" +dbName;
		
		Connection conn= DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}
	
	
}
