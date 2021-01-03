package ute.firstproject.services;


import java.util.ArrayList;
import java.util.List;
import ute.firstproject.model.NodeModel;


public class SearchOption {
	List<String> names = new ArrayList<String>();
	public List<String> search(NodeModel node, String keyword)
	{
		if (node == null) {
			return null;
		}
		else {
			if (node.getData().getWord().toLowerCase().contains(keyword.toLowerCase())) {
				names.add(node.getData().getWord());
			}
			search(node.getpLeft(),keyword);
			search(node.getpRight(),keyword);
		}
		return names;
	}
}
