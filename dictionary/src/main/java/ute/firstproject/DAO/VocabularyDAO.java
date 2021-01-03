package ute.firstproject.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ute.firstproject.model.OneWordModel;

public class VocabularyDAO implements IObjectDAO{
	public static List<OneWordModel> allWordsForViet_Anh(Connection conn)
	{
		String sql = "Select * from vocabulary";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			List<OneWordModel> allWords = new ArrayList<OneWordModel>();
			while (rs.next()) {
				OneWordModel newWord = new OneWordModel();
				newWord.setMean(rs.getString(2));
				newWord.setPronunciation(rs.getString(3));
				newWord.setWordType(rs.getString(4));
				newWord.setWord(rs.getString(5));
				allWords.add(newWord);
			}
			return allWords;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	
	public static List<OneWordModel> allWords(Connection conn)
	{
		String sql = "Select * from vocabulary";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			List<OneWordModel> allWords = new ArrayList<OneWordModel>();
			while (rs.next()) {
				OneWordModel newWord = new OneWordModel();
				newWord.setWord(rs.getString(2));
				newWord.setPronunciation(rs.getString(3));
				newWord.setWordType(rs.getString(4));
				newWord.setMean(rs.getString(5));
				allWords.add(newWord);
			}
			return allWords;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}
	
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
		OneWordModel oneWord = (OneWordModel)obj;
		String sql = "Update vocabulary set pronunciation=?, wordType=?, mean=? Where word=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, oneWord.getPronunciation());
			pstm.setString(2, oneWord.getWordType());
			pstm.setString(3, oneWord.getMean());
			pstm.setString(4, word);
			System.out.println(pstm);
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
		String sql = "Delete From vocabulary Where word=?";
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, word);
			System.out.println(pstm);
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	

}
