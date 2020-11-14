package ute.firstproject.DAO;

import java.sql.Connection;

public interface IObjectDAO {
	public boolean add(Connection conn, Object obj);
	public boolean edit(Connection conn, Object obj, String word);
	public boolean delete(Connection conn, String word);
}
