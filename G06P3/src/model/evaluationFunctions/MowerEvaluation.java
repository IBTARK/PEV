package model.evaluationFunctions;

import java.util.ArrayList;

import model.representation.Representation;
import model.treeRep.symbols.Symbol;
import model.treeRep.trees.MowerTree;
import model.treeRep.trees.TreeNode;
import resources.Pair;

/**
 * Evaluation function to evaluate only MowerTrees
 */
public class MowerEvaluation implements EvaluationFunction{
	
	private int numCols;
	private int numRows;
	
	private int col; //from 0 to numCols - 1
	private int row; //from 0 to numRows - 2
	
	private int orientation; //4 values (0: north, 1: west, 2: south, 3: east)
	
	private ArrayList<Pair<Integer, Integer>> path; //path followed by the mower
	private ArrayList<Integer> orientationPath; //orientation of the path followed by the mower
	
	private int numLawnCut; //number of cut boxes
	
	private ArrayList<ArrayList<Boolean>> garden; //false: not cut; true: cut
	
	private int numLeftRotations;
	private int numMovements;
	
	public MowerEvaluation(int numCols, int numRows) {
		this.numCols = numCols;
		this.numRows = numRows;
	}
	
	/**
	 * Reset all the attributes. This is done because the same evaluation function is used to evaluate all the trees
	 */
	private void reset() {
		col = 4;
		row = 4;
		orientation = 0;
		numLeftRotations = 0;
		numMovements = 0;
		numLawnCut = 0;
		
		path = new ArrayList<Pair<Integer, Integer>>();
		orientationPath = new ArrayList<Integer>();
		
		garden = new ArrayList<ArrayList<Boolean>>();
		for(int i = 0; i < numCols; i++) {
			ArrayList<Boolean> newCol = new ArrayList<Boolean>();
			for(int j = 0; j < numRows; j++) {
				newCol.add(false);
			}
			garden.add(newCol);
		}
	}

	@Override
	public Double apply(Representation c) {
		int numNoMovement = 0; //Number of times that no movement has been done
		int lastNumLeftRotations = 0, lastNumMovement = 0;
		
		//Reset all the attributes
		reset();
		
		MowerTree t = (MowerTree) c;
		
		//The program is executed repeatedly
		while(numLeftRotations < 100 && numMovements < 100 && numLawnCut < numCols * numRows && numNoMovement < 2) {
			//Code to control those cases in which no movement can be done
			lastNumLeftRotations = numLeftRotations;
			lastNumMovement = numMovements;
			
			visitNode(t.getRoot());
			
			//Code to control those cases in which no movement can be done
			if(lastNumLeftRotations == numLeftRotations && lastNumMovement == numMovements) {
				numNoMovement++;
			}
			else {
				numNoMovement = 0;
			}
		}
		
		//Save the path and the garden
		t.setPath(path);
		t.setOrientationPath(orientationPath);
		t.setGarden(garden);
		
		return Double.valueOf(numLawnCut);
	}
	
	private Pair<Integer, Integer> visitNode(TreeNode<Symbol> n) {
		if(n.getSymbol().getArity() == 0) { //leaf
			if(n.getSymbol().getSymbol() == "IZQUIERDA") return izquierda();
			else if(n.getSymbol().getSymbol() == "AVANZA") return avanza();
			else if(n.getSymbol().getSymbol() == "CONSTALEAT") return constanteAleatoria(n.getSymbol());
		}
		else {
			if(n.getSymbol().getSymbol() == "SUMA") return suma(visitNode(n.getChildren().get(0)), visitNode(n.getChildren().get(1)));
			else if(n.getSymbol().getSymbol() == "SALTA") return salta(visitNode(n.getChildren().get(0)));
			else if(n.getSymbol().getSymbol() == "PROGN") return progn(n);
		}
		
		return null;
	}
	
	private Pair<Integer, Integer> izquierda(){
		orientation = (orientation + 1) % 4; //turns left
		numLeftRotations++;
		return new Pair<Integer, Integer>(0, 0);
	}
	
	private Pair<Integer, Integer> avanza(){
		if(orientation == 0){ //north
			col = Math.floorMod(col + 1, numCols);
			row = Math.floorMod(row + 1, numRows);
		}
		else if(orientation == 1){ //west
			col = Math.floorMod(col - 1, numCols);
			row = Math.floorMod(row + 1, numRows);
		}
		else if(orientation == 2 || orientation == 3) { //south or east
			col = Math.floorMod(col + 1, numCols);
			row = Math.floorMod(row + 1, numRows);
		}
		
		//The lawn is cut
		if(!garden.get(col).get(row)) {
			numLawnCut++;
			garden.get(col).set(row, true);
		}
		
		path.add(new Pair<Integer, Integer>(col, row));
		orientationPath.add(orientation);
		
		numMovements++;
		
		return new Pair<Integer, Integer>(0, 0);
	}
	
	private Pair<Integer, Integer> constanteAleatoria(Symbol s){
		return new Pair<Integer, Integer>(s.getCol(), s.getRow());
	}
	
	private Pair<Integer, Integer> suma(Pair<Integer, Integer> pos1, Pair<Integer, Integer> pos2){
		return new Pair<Integer, Integer>((pos1.getFirst() + pos2.getFirst()) % numCols, (pos2.getSecond() + pos2.getSecond()) % numRows);
	}
	
	private Pair<Integer, Integer> salta(Pair<Integer, Integer> offsets) {
		if(orientation == 0){ //north
			col = Math.floorMod(col + offsets.getFirst(), numCols); 
			row = Math.floorMod(row - offsets.getSecond(), numRows);
		}
		else if(orientation == 1){ //west
			col = Math.floorMod(col - offsets.getFirst(), numCols); 
			row = Math.floorMod(row + offsets.getSecond(), numRows);
		}
		else if(orientation == 2 || orientation == 3) {//south or east
			col = Math.floorMod(col + offsets.getFirst(), numCols); 
			row = Math.floorMod(row + offsets.getSecond(), numRows);
		}
		
		//The lawn is cut
		if(!garden.get(col).get(row)) {
			numLawnCut++;
			garden.get(col).set(row, true);
		}
		
		path.add(new Pair<Integer, Integer>(col, row));
		orientationPath.add(orientation);
		
		numMovements++;
		
		return new Pair<Integer, Integer>(offsets.getFirst(), offsets.getSecond());
	}
	
	private Pair<Integer, Integer> progn(TreeNode<Symbol> n){
		visitNode(n.getChildren().get(0));
		return visitNode(n.getChildren().get(1));
	}
	
	public ArrayList<Pair<Integer, Integer>> getPath(){
		return path;
	}
	
	public ArrayList<Integer> getOrientationPath(){
		return orientationPath;
	}
}
