package model.chromosomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.fenotypes.FenotypeFunction;
import model.genes.Gene;
import model.genes.TreeNode;

public class TreeChromosome implements Comparable<Chromosome>, Cloneable{		
	
	protected Random random;
	
	protected int chromosomeLength;
	
	protected FenotypeFunction genesFenotypesFunctions;
	
	protected double evaluation; //evaluation of the chromosome
	protected double fitness; //fitness of the chromosome
	protected double score; //relative score (fitness_i/fitness_Total)
	protected double scoreAccumulated; //accumulated relative score
	
	private TreeNode root;
	private int maxHeight;
	private int minHeight;

	public TreeChromosome(FenotypeFunction genesFenotypesFunctions, int maxHeight, int minHeight) {
		
		random = new Random();
		
		this.genesFenotypesFunctions = genesFenotypesFunctions;
		root = null;
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
	}

	@Override
	public Chromosome clone() {

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
	
	public int getMinHeight() {
		return minHeight;
	}

//**********************************************************************************
//Setters
	
	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
	
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}
}
