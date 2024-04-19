package model.treeRep.trees;

import java.util.ArrayList;

import model.treeRep.symbols.Symbol;

/**
 * Representation of a simple node (one element on the node) of a tree.
 *
 */
public class TreeNode<T extends Symbol>{
	private ArrayList<TreeNode<T>> children; //Direct descendants
	private ArrayList<TreeNode<T>> descendants; //All the descendants
	private TreeNode<T> father;
	
	private T symbol; //elemento del nodo

	public TreeNode() {
		this.symbol = null;
		children = new ArrayList<TreeNode<T>>();
		descendants = new ArrayList<TreeNode<T>>();
		this.father = null;
	}
	
	public TreeNode(TreeNode<T> father) {
		this.symbol = null;
		children = new ArrayList<TreeNode<T>>();
		descendants = new ArrayList<TreeNode<T>>();
		this.father = father;
	}
	
	public TreeNode(T symbol) {
		this.symbol = symbol;
		children = new ArrayList<TreeNode<T>>();
		descendants = new ArrayList<TreeNode<T>>();
		father = null;
	}
	
	public TreeNode(T symbol, TreeNode<T> father) {
		this.symbol = symbol;
		children = new ArrayList<TreeNode<T>>();
		descendants = new ArrayList<TreeNode<T>>();
		this.father = father;
	}

	protected boolean valid() {
		return true;
	}
	
	@Override
	public TreeNode<T> clone() {
		TreeNode<T> newNode = new TreeNode(symbol.clone());
		
		ArrayList<TreeNode<T>> childrenCpy = new ArrayList<TreeNode<T>>();
		ArrayList<TreeNode<T>> descendantsCpy = new ArrayList<TreeNode<T>>();
		TreeNode<T> newChild;
		for (TreeNode<T> child : children){
			newChild = child.clone();

			//The descendants are added
			descendantsCpy.add(newChild);
			childrenCpy.add(newChild);
			descendantsCpy.addAll(newChild.getDescendants());
			
			//The father of the child is set
			newChild.setFather(newNode);
		}
		
		newNode.setChildren(childrenCpy);
		newNode.setDescendants(descendantsCpy);


		return newNode;
	}
	
	/**
	 * Add children to this node
	 * 
	 * @param list of children to be added
	 */
	public void addChildren(ArrayList<TreeNode<T>> children) {
		this.children.addAll(children);
	}
	
	/**
	 * Add a child to this node in an specific position
	 * 
	 * @param child node to be added as a child
	 */
	public void addChild(TreeNode<T> child, int pos) {
		if(!children.contains(child))
			children.add(pos, child);
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
	 * Add a list of descendants to the list of descendants
	 */
	public void addDescendants(ArrayList<TreeNode<T>> descendants) {
		this.descendants.addAll(descendants);
	}
	
	/**
	 * Add a descendant to the list of descendants
	 */
	public void addDescendant(TreeNode<T> descendant) {
		this.descendants.add(descendant);
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
	
	/**
	 * Remove a list of descendants from the list of descendants and if possible from the list of children
	 */
	public void removeDescendants(ArrayList<TreeNode<T>> descendants) {
		this.descendants.removeAll(descendants);
		this.children.removeAll(descendants);
	}
	
	/**
	 * Remove a descendant from the list of descendants and if possible from the list of children
	 */
	public void removeDescendant(TreeNode<T> descendant) {
		this.descendants.remove(descendant);
		this.children.remove(descendant);
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
	
	public ArrayList<TreeNode<T>> getDescendants() {
		return descendants;
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
	
	public void setDescendants(ArrayList<TreeNode<T>> descendants) {
		this.descendants = descendants;
	}

}
