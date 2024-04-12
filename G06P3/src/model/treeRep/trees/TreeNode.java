package model.treeRep.trees;

import java.util.ArrayList;

import model.treeRep.symbols.Symbol;

/**
 * Representation of a simple node (one element on the node) of a tree.
 *
 */
public class TreeNode<T extends Symbol>{
	private ArrayList<TreeNode<T>> children;
	private TreeNode<T> father;
	
	private T symbol; //elemento del nodo

	public TreeNode() {
		this.symbol = null;
		children = new ArrayList<TreeNode<T>>();
		this.father = null;
	}
	
	public TreeNode(TreeNode<T> father) {
		this.symbol = null;
		children = new ArrayList<TreeNode<T>>();
		this.father = father;
	}
	
	public TreeNode(T symbol) {
		this.symbol = symbol;
		children = new ArrayList<TreeNode<T>>();
		father = null;
	}
	
	public TreeNode(T symbol, TreeNode<T> father) {
		this.symbol = symbol;
		children = new ArrayList<TreeNode<T>>();
		this.father = father;
	}

	protected boolean valid() {
		return true;
	}

	@Override
	public TreeNode<T> clone() {
		TreeNode<T> newNode = new TreeNode(symbol.clone());
		
		ArrayList<TreeNode<T>> childrenCpy = new ArrayList<TreeNode<T>>();
		TreeNode<T> newChild;
		for (TreeNode<T> child : children){
			newChild = child.clone();
			child.setFather(newNode);
			childrenCpy.add(newChild);
		}
		newNode.setChildren(childrenCpy);

		return newNode;
	}
	
	/**
	 * Add a child to this node
	 * 
	 * @param child node to be added as a child
	 */
	public void addChild(TreeNode<T> child) {
		if(!children.contains(child))
			children.add(child);
	}
	
	/**
	 * Remove a child from this node
	 * 
	 * @param child node to be removed
	 */
	public void removeChild(TreeNode<T> child) {
		if(children.contains(child))
			children.remove(child);
	}
	
//**********************************************************************************
//Getters
	
	/**
	 * 
	 * @return father of the node
	 */
	public TreeNode<T> getFather() {
		return father;
	}
	
	/**
	 * 
	 * @return list of children of this node
	 */
	public ArrayList<TreeNode<T>> getChildren(){
		return children;
	}
	
	public T getSymbol(){
		return symbol;
	}
	
	public int getNumChildren() {
		return children.size();
	}

//**********************************************************************************
//Setters
	
	public void setFather(TreeNode<T> father) {
		this.father = father;
	}
	

	public void setChildren(ArrayList<TreeNode<T>> children){
		this.children = children;
	}
	
	public void setSymbol(T symbol){
		this.symbol = symbol;
	}

}
