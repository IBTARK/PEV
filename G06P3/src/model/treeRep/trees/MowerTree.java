package model.treeRep.trees;

import java.util.ArrayList;

import model.fenotypes.FenotypeFunction;
import model.treeRep.symbols.Symbols;
import resources.Pair;

public class MowerTree extends TreeChromosome{
	
	private ArrayList<Pair<Integer, Integer>> path; //path followed by the mower
	
	public MowerTree(FenotypeFunction fenotypeFunction, Symbols symbols, int minHeight, int maxHeight) {
		super(fenotypeFunction, symbols, minHeight, maxHeight);
		
		path = new ArrayList<Pair<Integer, Integer>>();
	}

//**********************************************************************************
// Cloneable
	
	@Override
	public MowerTree clone() {
		MowerTree newTree = new MowerTree(fenotypeFunction.clone(), symbols.clone(), maxHeight, minHeight);
		
		//New root
		newTree.setRoot(root.clone());
		
		newTree.setFitness(fitness);
		newTree.setEvaluation(evaluation);
		newTree.setScore(score);
		newTree.setScoreAccumulated(scoreAccumulated);
		
		ArrayList<Pair<Integer, Integer>> newPath = new ArrayList<Pair<Integer, Integer>>();
		for(Pair<Integer, Integer> pos : path) {
			newPath.add(new Pair<Integer, Integer>(pos.getFirst(), pos.getSecond()));
		}
		
		newTree.setPath(newPath);
		
		return newTree;
	}

//**********************************************************************************
//Getters
	
	public ArrayList<Pair<Integer, Integer>> getPath(){
		return path;
	}
	
//**********************************************************************************
//Setters
	
	public void setPath(ArrayList<Pair<Integer, Integer>> path){
		this.path = path;
	}
}
