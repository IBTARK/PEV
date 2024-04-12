package model.treeRep.trees;

import java.util.ArrayList;

import model.Representation;
import model.listRep.fenotypes.FenotypeFunction;
import model.treeRep.symbols.Symbol;
import model.treeRep.symbols.Symbols;

public class TreeChromosome extends Representation{		
	
	protected TreeNode<Symbol> root;
	protected Symbols symbols;
	
	protected int maxHeight;
	protected int minHeight;
	protected int numNodes;
	protected int height;
	
	protected FenotypeFunction nodesFenotypesFunctions;
	

	public TreeChromosome(FenotypeFunction nodesFenotypesFunctions, int maxHeight, int minHeight, Symbols symbols) {
		
		this.nodesFenotypesFunctions = nodesFenotypesFunctions;
		root = new TreeNode<Symbol>();
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
		this.symbols = symbols;
		
		numNodes = 0;
		height = 0;
	}

//**********************************************************************************
//Trees initializations
	
	public void initialize(InitializationType type) {
		root.setChildren(new ArrayList<TreeNode<Symbol>>());
		root.setFather(null);
		
		if(type == InitializationType.FULL) {
			fullInitialization(root, 0);
		}
		else {
			growInitialization(root, 0);
		}
	}
	
	
	/**
	 * Full initialization of the tree
	 * 
	 * @param node root of the tree
	 * @param height initial height (0)
	 */
	private void fullInitialization(TreeNode<Symbol> node, int height) {
		if(height < maxHeight) {
			Symbol function = symbols.getFunctions().get(random.nextInt(0, symbols.getFunctions().size()));
			node.setSymbol(function);
			
			numNodes++;
			
			for(int i = 0; i < function.getArity(); i++) {
				TreeNode<Symbol> child = new TreeNode<Symbol>(node);
				node.addChild(child);
				
				fullInitialization(child, height + 1);
			}
		}
		else {
			node.setSymbol(symbols.getTerminals().get(random.nextInt(0, symbols.getTerminals().size())));
			numNodes++;
			
			this.height = maxHeight;
		}
	}
	
	/**
	 * Grow initialization of the tree
	 * 
	 * @param node root of the tree
	 * @param height initial height (0)
	 */
	private void growInitialization(TreeNode<Symbol> node, int height) {
		if(height < maxHeight) {
			Symbol functionOrTerminal = symbols.getBoth().get(random.nextInt(0, symbols.getBoth().size()));
			node.setSymbol(functionOrTerminal);
			
			numNodes++;
			
			for(int i = 0; i < functionOrTerminal.getArity(); i++) {
				TreeNode<Symbol> child = new TreeNode<Symbol>(node);
				node.addChild(child);
				
				growInitialization(child, height + 1);
			}
		}
		else {
			node.setSymbol(symbols.getTerminals().get(random.nextInt(0, symbols.getTerminals().size())));
			numNodes++;
			
			if(height > this.height)
				this.height = height;
		}
	}
	

//**********************************************************************************
// Cloneable
	
	@Override
	public TreeChromosome clone() {
		TreeChromosome newTree = new TreeChromosome(nodesFenotypesFunctions.clone(), maxHeight, minHeight, symbols.clone());
		
		//New root
		newTree.setRoot(root.clone());
		
		newTree.setFitness(fitness);
		newTree.setEvaluation(evaluation);
		newTree.setScore(score);
		newTree.setScoreAccumulated(scoreAccumulated);
		
		newTree.setNumNodes(numNodes);
		newTree.setHeight(height);
		
		return newTree;
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
	
	public int getNumNodes() {
		return numNodes;
	}
	
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @return symbols
	 */
	public Symbols getSymbols() {
		return symbols;
	}

//**********************************************************************************
//Setters
	
	public void setRoot(TreeNode<Symbol> root) {
		this.root = root;
	}
	
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
	
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}
	
	public void setSymbols(Symbols symbols) {
		this.symbols = symbols;
	}
	
	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
	}
	
	public void setHeight(int height) {
		this.height =  height;
	}
}
