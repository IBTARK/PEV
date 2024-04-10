package model.chromosomes;

import java.util.List;

import model.fenotypes.FenotypeFunction;
import model.genes.TreeNode;

public class TreeChromosome implements Comparable<Chromosome>, Cloneable{{
	
	private TreeNode root;
	private int maxHeight;

	public TreeChromosome(int numGenes, int genesLengths, List<FenotypeFunction> genesFenotypesFunctions, int maxHeight) {
		super(numGenes, genesLengths, genesFenotypesFunctions);
		root = null;
		this.maxHeight = maxHeight;
	}

	@Override
	public Chromosome clone() {
		// TODO Auto-generated method stub
		return null;
	}

	
//**********************************************************************************
//Getters
	
	public TreeNode getRoot() {
		return root;
	}
	
	public int getMaxHeight() {
		return maxHeight;
	}

//**********************************************************************************
//Setters
	
	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
}
