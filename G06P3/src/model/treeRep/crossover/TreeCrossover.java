package model.treeRep.crossover;

import java.util.Collections;
import java.util.Random;

import model.Crossover;
import model.representation.Representation;
import model.treeRep.symbols.Symbol;
import model.treeRep.trees.TreeChromosome;
import model.treeRep.trees.TreeNode;

public class TreeCrossover implements Crossover{
	
	private double funProb; //Probability with which a functional node will be selected
	private Random random;
	
	public TreeCrossover(double funProb) {
		this.funProb = funProb;
		random = new Random();
	}

	@Override
	public void cross(Representation c1, Representation c2) {
		TreeChromosome t1 = (TreeChromosome) c1;
		TreeChromosome t2 = (TreeChromosome) c2;
		
		//Roots of the subtrees to be interchanged
		TreeNode<Symbol> n1 = null, n2 = null;
		
		//The descendant list is shuffled
		Collections.shuffle(t1.getRoot().getDescendants());
		Collections.shuffle(t2.getRoot().getDescendants());
		
		double prob = random.nextDouble();
			
		
		for(TreeNode<Symbol> n : t1.getRoot().getDescendants()) {
			if(prob < funProb && n.getSymbol().getArity() != 0) { //If possible a functional node is selected for the first tree
				n1 = n;
				break;
			}
			else if(prob >= funProb && n.getSymbol().getArity() == 0) { //If possible a terminal node is selected for the first tree
				n1 = n;
				break;
			}
		}
		
		if(n1 == null)
			n1 = t1.getRoot().getDescendants().get(0); 
		
		
		for(TreeNode<Symbol> n : t2.getRoot().getDescendants()) {
			if(prob < funProb && n.getSymbol().getArity() != 0) { //If possible a functional node is selected for the second tree
				n2 = n;
				break;
			}
			else if(prob >= funProb && n.getSymbol().getArity() == 0) { //If possible a terminal node is selected for the second tree
				n2 = n;
				break;
			}
		}
		
		if(n2 == null)
			n2 = t2.getRoot().getDescendants().get(0);
		
		int pos1 = n1.getFather().getChildren().indexOf(n1), pos2 = n2.getFather().getChildren().indexOf(n2);
		
		//The fathers of the nodes are saved
		TreeNode<Symbol> father1 = n1.getFather(), father2 = n2.getFather();
		
		//The descendants of the nodes to be interchanged are removed from their parents
		t1.deleteNode(n1);
		t2.deleteNode(n2);
		
		
		//The subtrees are interchanged
		t1.addNode(n2, father1, pos1);
		t2.addNode(n1, father2, pos2);
	}
	
	public void setFunProb(double funProb) {
		this.funProb = funProb;
	}

}
