package model.treeRep.mutation;

import java.util.Collections;

import model.mutation.Mutation;
import model.representation.Representation;
import model.treeRep.symbols.Symbol;
import model.treeRep.trees.TreeChromosome;
import model.treeRep.trees.TreeNode;

/**
 * If possible select a functional node and reverse the order of the arguments
 */
public class PermutationMutation implements Mutation{

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
			Collections.reverse(sn.getChildren());
		}
	}

}
