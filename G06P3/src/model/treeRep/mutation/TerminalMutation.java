package model.treeRep.mutation;

import java.util.Collections;
import java.util.Random;

import model.Mutation;
import model.representation.Representation;
import model.treeRep.symbols.Symbol;
import model.treeRep.trees.TreeChromosome;
import model.treeRep.trees.TreeNode;

/**
 * Randomly select a terminal and replace it by a different one
 */
public class TerminalMutation implements Mutation{
	
	private Random random;
	
	public TerminalMutation() {
		random = new Random();
	}

	@Override
	public void mutate(Representation c) {
		TreeChromosome t = (TreeChromosome) c;
		
		Collections.shuffle(t.getRoot().getDescendants());
		
		//The node to be mutated is selected
		TreeNode<Symbol> sn = null;
		
		for(TreeNode<Symbol> n : t.getRoot().getDescendants()) {
			if(n.getSymbol().getArity() == 0) { //If possible a terminal node is selected for the first tree
				sn = n;
				break;
			}
		}
		
		if(sn != null) {
			Symbol s = (t.getSymbols().getTerminals().get(random.nextInt(0, t.getSymbols().getTerminals().size()))).clone();
			while(s.getSymbol() == sn.getSymbol().getSymbol()) {
				s = (t.getSymbols().getTerminals().get(random.nextInt(0, t.getSymbols().getTerminals().size()))).clone();
			}
			s.repos();
			sn.setSymbol(s);
		}
	}

}
