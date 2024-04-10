package model.genes;

import java.util.ArrayList;
import java.util.Random;

import model.fenotypes.FenotypeFunction;

/**
 * Representation of a simple node (one element on the node) of a tree.
 *
 */
public class TreeNode<T>{
	private ArrayList<TreeNode<T>> children;
	private TreeNode<T> father;
	
	private T elem; //elemento del nodo

	public TreeNode(T elem) {
		this.elem = elem;
		children = new ArrayList<TreeNode<T>>();
		father = null;
	}
	
	public TreeNode(T elem, TreeNode<T> father) {
		this.elem = elem;
		children = new ArrayList<TreeNode<T>>();
		this.father = father;
	}

	/**
	 * Random initialization of a gene. 
	 * The type of the only available allele is double.
	 */
	public Double initializeNodeRandom(Random random) {
		alleles.set(0, random.nextDouble(fenotypeFunction.getMinValue(), fenotypeFunction.getMaxValue()));
		return fenotype = fenotypeFunction.apply(this);
	}

	protected boolean valid() {
		return true;
	}

	@Override
	public TreeNode<T> clone() {
		TreeNode<T> newNode = new TreeNode(getFather().clone());
		ArrayList<TreeNode<T>> childrenCpy = new ArrayList<TreeNode<T>>();
		for (TreeNode<T> child : children){
			childrenCpy.add(child.clone());
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
	
	public T getElem(){
		return elem;
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
	
	public void getElem(T elem){
		this.elem = elem;
	}

}
