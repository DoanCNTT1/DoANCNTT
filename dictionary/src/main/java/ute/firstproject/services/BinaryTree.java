package ute.firstproject.services;


import ute.firstproject.model.NodeModel;
import ute.firstproject.model.OneWordModel;

public class BinaryTree {
	private BinaryTree root;

	public BinaryTree getRoot() {
		return root;
	}

	public void setRoot(BinaryTree root) {
		this.root = root;
	}
	// Khởi tạo cây rỗng
	public BinaryTree()
	{
		root = null;
	}
	
	public int insertNode(NodeModel T, OneWordModel word)
	{
		if (T != null) {
			if (T.getData().getWord().compareTo(word.getWord())<0) {
				insertNode(T.getpLeft(), word);
			}
			else if (T.getData().getWord().compareTo(word.getWord())==0) {
				return 0;
			}
			else {
				insertNode(T.getpRight(), word);
			}
		}
		
		
		NodeModel temp = new NodeModel(word);
		T.setNode(temp);
		return 1;
		
	}
	
	public void alternativeSearch(NodeModel X, NodeModel Y)
	{
		if (Y.getpLeft()!=null) {
			alternativeSearch(X, Y.getpLeft());
		}
		else {
			//X.setData(Y.getData());
			X.setNode(Y);
			Y.setNode(Y.getpRight());
		}
	}
	
	public void deleteNode(NodeModel node, OneWordModel key)
	{
		if (node == null) {
			return;
		}
		else {
			if (key.getWord().compareTo(node.getData().getWord())<0) {
				deleteNode(node.getpLeft(), key);
			}
			else if (key.getWord().compareTo(node.getData().getWord())>0) {
				deleteNode(node.getpRight(), key);
			}
			else {
				NodeModel X = node;
				if (node.getpLeft()==null) {
					node=node.getpRight();
				}
				else if (node.getpLeft() == null) {
					node=node.getpLeft();
				}
				else {
					alternativeSearch(X, node.getpRight());
				}
				X=null;
			}
		}	
	}
	
	
	
	
}
