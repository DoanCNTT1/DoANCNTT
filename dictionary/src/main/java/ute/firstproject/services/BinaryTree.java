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
	
	public void deleteNode(NodeModel node, String key)
	{
		if (node == null) {
			return;
		}
		else {
			if (key.compareTo(node.getData().getWord())<0) {
				deleteNode(node.getpLeft(), key);
			}
			else if (key.compareTo(node.getData().getWord())>0) {
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
	
	
	public NodeModel Search(NodeModel node, String key)
	{
		NodeModel p = node;
		while (p!=null) {
			if (key.compareTo(p.getData().getWord())==0) {
				return p;
			}
			else if (key.compareTo(p.getData().getWord())<1) {
				p=p.getpLeft();
			}
			else {
				p=p.getpRight();
			}
			
		}
		
		return null;
		
	}
	
	// ------------AVL Tree---------------- //
	public int height(NodeModel N)
	{
		if (N == null) {
			return 0;
		}
		
		return N.getHeight();
	}
	
	
	public int max(int a, int b)
	{
		return (a>b) ? a : b;
	}
	
	public NodeModel rightRotate(NodeModel y)
	{
		NodeModel x = y.getpLeft();
		NodeModel temp = x.getpRight();
		
		x.setpRight(y);
		y.setpLeft(temp);
		
		y.setHeight(max(height(y.getpLeft()), height(y.getpRight()))+1);
		x.setHeight(max(height(x.getpLeft()), height(x.getpRight()))+1);
		
		return x;
	}
	
	public NodeModel leftRotate(NodeModel x)
	{
		NodeModel y =x.getpRight();
		NodeModel temp = y.getpLeft();
		
		y.setpLeft(x);
		x.setpRight(temp);
		
		x.setHeight(max(height(x.getpLeft()), height(x.getpRight()))+1);
		y.setHeight(max(height(y.getpLeft()), height(y.getpRight()))+1);
		
		return y;
		
		
	}
	
	public int getBalance(NodeModel N)
	{
		if (N == null) {
			return 0;
		}
		
		return height(N.getpLeft())-height(N.getpRight());
	}
	
	
	public NodeModel insertAVL(NodeModel node, OneWordModel word)
	{
		if (node == null) {
			return (new NodeModel(word));
		}
		
		if (word.getWord().compareTo(node.getData().getWord())<0) {
			node.setpLeft(insertAVL(node.getpLeft(), word));
		}
		else if (word.getWord().compareTo(node.getData().getWord())>0) {
			node.setpRight(insertAVL(node.getpRight(), word));
		}
		else {
			return node;
		}
		
		
		node.setHeight(1+max(height(node.getpLeft()), height(node.getpRight())));
		
		int balance = getBalance(node);
		
		//4 case
		// Left Left 
		if (balance > 1 && word.getWord().compareTo(node.getpLeft().getData().getWord())<0) {
			return rightRotate(node);
		}
		
		// Right Right 
		if (balance <-1 && word.getWord().compareTo(node.getpRight().getData().getWord())>0) {
			return  leftRotate(node);
		}
		
		//Left Right
		if (balance > 1 && word.getWord().compareTo(node.getpLeft().getData().getWord())>0) {
			node.setpLeft(leftRotate(node.getpLeft()));
			return rightRotate(node);
		}
		// Right Left
		if (balance<-1 && word.getWord().compareTo(node.getpRight().getData().getWord())<0) {
			node.setpRight(rightRotate(node.getpRight()));
			return leftRotate(node);
		}
		
		return node;
	}
}
