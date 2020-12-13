package ute.firstproject.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import ute.firstproject.model.OneWordModel;

public class Search {
	public static List<String> search(String keyword)
	{
		List<String> names = new ArrayList<String>();
		
		for (OneWordModel oneWord : allWords) {
			if (oneWord.getWord().toLowerCase().contains(keyword.toLowerCase())) {
				names.add(oneWord.getWord());
			}
		}
		return names;
	}
}
