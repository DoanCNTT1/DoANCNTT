package ute.firstproject.services;





import java.util.ArrayList;
import java.util.List;

import ute.firstproject.DAO.VocabularyDAO;
import ute.firstproject.model.NodeModel;
import ute.firstproject.model.OneWordModel;
import ute.firstproject.utils.MyUtils;

public class BinaryTree {
	public List<String> find_NLR(NodeModel node,String keyword)
	{
		List<String> list=new ArrayList<String>();
		if (node == null) {
			return list;
		}
		else {
			//System.out.print(node.getData().getWord());
			if(node.getData().getWord().contains(keyword))
			{
				list.add(node.getData().getWord());
			}
			System.out.println(node.getData().getWord());
			find_NLR(node.getpLeft(),keyword);
			find_NLR(node.getpRight(),keyword);
		}
		return null;
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
	public  NodeModel minValueNode(NodeModel node) {
        NodeModel current = node;
        /* loop down to find the leftmost leaf */
        while (current.getpLeft() != null)
            current = current.getpLeft();
        return current;
    }
	public NodeModel deleteNode(NodeModel node, String key)
	{
		if (node == null) {
			return node;
		}
		else {
			if (key.compareTo(node.getData().getWord())<0) {
				node.setpLeft(deleteNode(node.getpLeft(), key));
			}
			else if (key.compareTo(node.getData().getWord())>0) {
				node.setpRight(deleteNode(node.getpRight(), key));
			}
			else {
				if( (node.getpLeft() == null) || (node.getpRight() == null) ) {

	                NodeModel temp;
	                if (node.getpLeft() != null)
	                        temp = node.getpLeft();
	                else
	                    temp = node.getpRight();

	                // No child case
	                if(temp == null) {
	                    temp = node;
	                    node = null;
	                }
	                else // One child case
	                	node = temp; // Copy the contents of the non-empty child

	                temp = null;
	            }
	            else {
	                // node with two children: Get the inorder successor (smallest
	                // in the right subtree)
	                NodeModel temp = minValueNode(node.getpRight());

	                // Copy the inorder successor's data to this node
	                node.setData(temp.getData());

	                // Delete the inorder successor
	                node.setpRight(deleteNode(node.getpRight(), key)) ;
	            }
			}
		}
		if (node == null) {
			return node;
		}
        node.setHeight(1+max(height(node.getpLeft()), height(node.getpRight())));
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalance(node.getpLeft()) >= 0)
            return rightRotate(node);

        // Left Right Case
        if (balance > 1 && getBalance(node.getpLeft()) < 0) {
            node.setpLeft(leftRotate(node.getpLeft()));
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance(node.getpRight()) <= 0)
            return leftRotate(node);

        // Right Left Case
        if (balance < -1 && getBalance(node.getpRight()) > 0) {
            node.setpRight(rightRotate(node.getpRight()));
            return leftRotate(node);
        }
		return node;
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
