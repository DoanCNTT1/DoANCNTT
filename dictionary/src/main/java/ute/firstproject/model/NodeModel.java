package ute.firstproject.model;

public class NodeModel {
	private OneWordModel data;
	private NodeModel pLeft;
	private NodeModel pRight;
	private int height;
	
	
	
	public OneWordModel getData() {
		return data;
	}
	public void setData(OneWordModel data) {
		this.data = data;
	}
	public NodeModel getpLeft() {
		return pLeft;
	}
	public void setpLeft(NodeModel pLeft) {
		this.pLeft = pLeft;
	}
	public NodeModel getpRight() {
		return pRight;
	}
	public void setpRight(NodeModel pRight) {
		this.pRight = pRight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public NodeModel()
	{
		
	}
	
	public NodeModel(OneWordModel word)
	{
//		data = word;
//		height = 1;
//		pLeft = null;
//		pRight = null;
		
		setData(word);
		setHeight(1);
		setpLeft(null);
		setpRight(null);
	}
	
	public void setNode(NodeModel node) {
		this.data = node.data;
		this.pLeft=node.pLeft;
		this.pRight=node.pRight;
		this.height=node.height;
	}
}
