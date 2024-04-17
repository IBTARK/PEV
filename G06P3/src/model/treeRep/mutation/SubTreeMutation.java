package model.treeRep.mutation;

import java.util.Collections;
import java.util.Random;

import model.mutation.Mutation;
import model.representation.Representation;
import model.treeRep.symbols.Symbol;
import model.treeRep.trees.TreeChromosome;
import model.treeRep.trees.TreeNode;

/**
 * Randomly select a subtree and replace it by another tree initialized by the grow initialization
 */
public class SubTreeMutation implements Mutation{
	
	private Random random;
	
	public SubTreeMutation() {
		random = new Random();
	}

	@Override
	public void mutate(Representation c) {
		TreeChromosome t = (TreeChromosome) c;
		
		Collections.shuffle(t.getRoot().getDescendants());
		
		//Randomly select a subtree
		TreeNode<Symbol> selected = t.getRoot().getDescendants().get(random.nextInt(t.getRoot().getDescendants().size()));
		
		//Save the father of the subtree
		TreeNode<Symbol> father = selected.getFather();
		
		//Save the position of the selected node
		int pos = father.getChildren().indexOf(selected);
		
		//Create a new tree
		TreeChromosome newTree = t.clone();
		newTree.growInitialization(newTree.getMaxHeight());
		
		//Remove the selected subtree from the original tree
		t.deleteNode(selected);
		
		//Add the new tree to the original tree
		t.addNode(newTree.getRoot(), father, pos);
	}

}
