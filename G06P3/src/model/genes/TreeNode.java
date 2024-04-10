package model.genes;

import java.util.ArrayList;
import java.util.Random;

import model.fenotypes.FenotypeFunction;

/**
 * Representation of a simple node (one element on the node) of a tree.
 *
 */
public class TreeNode extends Gene{
	private ArrayList<TreeNode> children;
	private TreeNode father;

	public TreeNode(FenotypeFunction fenotypeFunction) {
		super(1, fenotypeFunction);
		children = new ArrayList<TreeNode>();
		father = null;
	}
	
	public TreeNode(TreeNode father, FenotypeFunction fenotypeFunction) {
		super(1, fenotypeFunction);
		children = new ArrayList<TreeNode>();
		this.father = father;
	}

	@Override
	/**
	 * Random initialization of a gene. 
	 * The type of the only available allele is double.
	 */
	public Double initializeGeneRandom(Random random) {
		alleles.set(0, random.nextDouble(fenotypeFunction.getMinValue(), fenotypeFunction.getMaxValue()));
		return fenotype = fenotypeFunction.apply(this);
	}
	
	/**
	 * Random initialization of a gene. 
	 * The type of the only available allele is integer.
	 */
	public Double initializeGeneRandomInteger(Random random) {
		alleles.set(0, random.nextInt(fenotypeFunction.getMinValue().intValue(), fenotypeFunction.getMaxValue().intValue()));
		return fenotype = fenotypeFunction.apply(this);
	}

	@Override
	protected boolean valid() {
		return true;
	}

	@Override
	public Gene clone() {
		TreeNode newNode = new TreeNode(getFather(), fenotypeFunction);
		newNode.setAlleles(alleles);
		return newNode;
	}
	
	/**
	 * Add a child to this node
	 * 
	 * @param child node to be added as a child
	 */
	public void addChild(TreeNode child) {
		if(!children.contains(child))
			children.add(child);
	}
	
	/**
	 * Remove a child from this node
	 * 
	 * @param child node to be removed
	 */
	public void removeChild(TreeNode child) {
		if(children.contains(child))
			children.remove(child);
	}
	
//**********************************************************************************
//Getters
	
	/**
	 * 
	 * @return father of the node
	 */
	public TreeNode getFather() {
		return father;
	}
	
	/**
	 * 
	 * @return list of children of this node
	 */
	public ArrayList<TreeNode> getChildren(){
		return children;
	}
	
	public int getNumChildren() {
		return children.size();
	}

//**********************************************************************************
//Setters
	
	public void setFather(TreeNode father) {
		this.father = father;
	}
	

	public void setChildren(ArrayList<TreeNode> children){
		this.children = children;
	}

}
