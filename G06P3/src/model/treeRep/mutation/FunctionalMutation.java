package model.treeRep.mutation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.Mutation;
import model.Representation;
import model.treeRep.symbols.Symbol;
import model.treeRep.trees.TreeChromosome;
import model.treeRep.trees.TreeNode;

/**
 * If possible select a functional node and replace it by another function with the same arity
 */
public class FunctionalMutation implements Mutation{
	private Random random;
	
	public FunctionalMutation() {
		random = new Random();
	}

	@Override
	public void mutate(Representation c) {
		TreeChromosome t = (TreeChromosome) c;
		
		Collections.shuffle(t.getRoot().getDescendants());
		
		//The node to be mutated is selected
		TreeNode<Symbol> sn = null;
		
		for(TreeNode<Symbol> n : t.getRoot().getDescendants()) {
			if(n.getSymbol().getArity() != 0) { //If possible a functional node is selected for the first tree
				sn = n;
				break;
			}
		}
		
		if(sn != null) {
			ArrayList<Symbol> functions = t.getSymbols().getFunctionsAritiesMap().get(sn.getSymbol().getArity());
			Symbol selected = sn.getSymbol();
			//If sn is not the only function with his arity
			if(functions.size() > 1) {
				while(selected.getSymbol() == sn.getSymbol().getSymbol()) {
					selected = functions.get(random.nextInt(functions.size())).clone();
				}
				
				sn.setSymbol(selected);
			}
		}
	}
}
