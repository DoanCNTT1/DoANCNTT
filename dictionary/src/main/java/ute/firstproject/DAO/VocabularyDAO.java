package ute.firstproject.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ute.firstproject.model.OneWordModel;

public class VocabularyDAO implements IObjectDAO{

	@Override
	public boolean add(Connection conn, Object obj) {
		OneWordModel word = (OneWordModel)obj;
		String sql= "Insert into vocabulary(word, pronunciation, wordType, mean) values(?,?,?,?)";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, word.getWord());
			pstm.setString(2, word.getPronunciation());
			pstm.setString(3, word.getWordType());
			pstm.setString(4, word.getMean());
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean edit(Connection conn, Object obj, String word) {
		OneWordModel oneWord = new OneWordModel();
		String sql = "Update vocabulary set pronunciation=?, wordType=?, mean=? Where word=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, oneWord.getPronunciation());
			pstm.setString(2, oneWord.getWordType());
			pstm.setString(3, oneWord.getMean());
			pstm.setString(4, oneWord.getWord());
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Connection conn, String word) {
		OneWordModel oneWord = new OneWordModel();
		String sql = "Delete * From vocabulary Where word=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, oneWord.getWord());
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	

}
