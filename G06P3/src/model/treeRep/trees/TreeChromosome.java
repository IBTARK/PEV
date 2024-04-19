package model.treeRep.trees;

import java.util.ArrayList;

import model.evaluationFunctions.EvaluationFunction;
import model.fenotypes.FenotypeFunction;
import model.representation.Representation;
import model.treeRep.symbols.Symbol;
import model.treeRep.symbols.Symbols;

public abstract class TreeChromosome extends Representation{		
	
	protected TreeNode<Symbol> root;
		
	protected int maxHeight;
	protected int minHeight;
	
	protected Symbols symbols;
	
	protected String fenotype; //Is calculated in the evaluation function
	
	protected FenotypeFunction fenotypeFunction;

	public TreeChromosome(FenotypeFunction fenotypeFunction, Symbols symbols, int minHeight, int maxHeight) {
		root = new TreeNode<Symbol>();
		
		this.fenotypeFunction = fenotypeFunction;
		
		this.symbols = symbols;
		
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
	}
	
	/**
	 * Delete a node from the tree
	 * 
	 * @param node
	 */
	public void deleteNode(TreeNode<Symbol> node) {
		if(root.getDescendants().contains(node)) {
			//The descendants of the nodes to be interchanged are removed from their parents
			TreeNode<Symbol> act = node.getFather();
			
			//Remove the node from the list of children
			act.getChildren().remove(node);
			
			//Remove the descendants of the node from the rest of the tree
			while(act != null) {
				act.removeDescendants(node.getDescendants());
				act.removeDescendant(node);
				act = act.getFather();
			}
		}
	}
	
	/**
	 * Add a node to the tree
	 * 
	 * @param node
	 * @param father
	 */
	public void addNode(TreeNode<Symbol> node, TreeNode<Symbol> father, int pos) {
		if(!root.getDescendants().contains(node)) {
			node.setFather(father);
			
			//Add the node to the list of children
			TreeNode<Symbol> act = father;
			act.addChild(node, pos);
			
			//Add the descendants of the node to the rest of the tree
			while(act != null) {
				act.addDescendants(node.getDescendants());
				act.addDescendant(node);
				act = act.getFather();
			}
		}
	}
	
	/**
	 * 
	 * @return number of nodes in the tree
	 */
	public int getSize() {
		return root.getDescendants().size() + 1;
	}
	
	/**
	 * Compute the evaluation function of the tree chormosome (the fitness is also set to the same value as the evaluation)
	 * 
	 * @param evaluationFunction function to compute the evaluation of the chormosome
	 */
	public Double computeEvaluation(EvaluationFunction evaluationFunction) {
		evaluation = evaluationFunction.apply(this);
		fitness = Double.valueOf(evaluation);
		
		return evaluation;
	}

//**********************************************************************************
//Trees initializations
	
	public void fullInitialization(int maxHeight) {
		root = new TreeNode<Symbol>();
		fullInitializationAux(root, maxHeight, 0);
	}
	
	/**
	 * Full initialization of the tree
	 * 
	 * @param node root of the tree
	 * @param height initial height (0)
	 */
	private void fullInitializationAux(TreeNode<Symbol> node, int maxHeight, int height) {
		if(height < maxHeight) {
			Symbol function = (symbols.getFunctions().get(random.nextInt(0, symbols.getFunctions().size()))).clone();
			node.setSymbol(function);
			
			ArrayList<TreeNode<Symbol>> descendants = new ArrayList<TreeNode<Symbol>>();
			
			for(int i = 0; i < function.getArity(); i++) {
				TreeNode<Symbol> child = new TreeNode<Symbol>(node);
				node.addChild(child);
				child.setFather(node);
				
				fullInitializationAux(child, maxHeight, height + 1);
				
				descendants.add(child);
				descendants.addAll(child.getDescendants());
			}
			
			node.setDescendants(descendants);
		}
		else {
			Symbol s = (symbols.getTerminals().get(random.nextInt(0, symbols.getTerminals().size()))).clone();
			s.repos();
			node.setSymbol(s);
		}
	}
	
	public void growInitialization(int maxHeight) {
		root = new TreeNode<Symbol>();
		growInitializationAux(root, maxHeight, 0);
	}
	
	/**
	 * Grow initialization of the tree
	 * 
	 * @param node root of the tree
	 * @param height initial height (0)
	 */
	private void growInitializationAux(TreeNode<Symbol> node, int maxHeight, int height) {
		if(height < maxHeight) {
			Symbol functionOrTerminal = (symbols.getBoth().get(random.nextInt(0, symbols.getBoth().size()))).clone();
			if(height == 0) 
				functionOrTerminal = (symbols.getFunctions().get(random.nextInt(0, symbols.getFunctions().size()))).clone();
			
			functionOrTerminal.repos();
			node.setSymbol(functionOrTerminal);
			
			ArrayList<TreeNode<Symbol>> descendants = new ArrayList<TreeNode<Symbol>>();
			
			for(int i = 0; i < functionOrTerminal.getArity(); i++) {
				TreeNode<Symbol> child = new TreeNode<Symbol>(node);
				node.addChild(child);
				child.setFather(node);
				
				growInitializationAux(child, maxHeight, height + 1);
				
				descendants.add(child);
				descendants.addAll(child.getDescendants());
			}
			
			node.setDescendants(descendants);
		}
		else {
			Symbol s = (symbols.getTerminals().get(random.nextInt(0, symbols.getTerminals().size()))).clone();
			s.repos();
			node.setSymbol(s);
		}
	}
	

//**********************************************************************************
// Cloneable
	
	@Override
	public abstract TreeChromosome clone();
	
	
//**********************************************************************************
//Getters
	
	public TreeNode<Symbol> getRoot() {
		return root;
	}
	
	public int getMaxHeight() {
		return maxHeight;
	}
	
	public int getMinHeight() {
		return minHeight;
	}
	
	public Symbols getSymbols() {
		return symbols;
	}
	
	public FenotypeFunction getFenotypeFunction() {
		return fenotypeFunction;
	}
	
	public String getFenotype() {
		return fenotype;
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
		this.symbols =  symbols;
	}
	
	public void setFenotypeFunction(FenotypeFunction fenotypeFunction) {
		this.fenotypeFunction = fenotypeFunction;
	}
	
	public void setFenotype(String fenotype) {
		this.fenotype = fenotype;
	}
}
