package model.evaluationFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import model.listRep.chromosomes.MowerChromosome;
import model.representation.Representation;
import resources.Pair;

public class GrammarEvaluation implements EvaluationFunction{

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
	
	private Random r;
	
	public GrammarEvaluation(int numCols, int numRows) {
		this.numCols = numCols;
		this.numRows = numRows;
		r = new Random();
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
		path.add(new Pair<Integer, Integer>(4,4));
		orientationPath = new ArrayList<Integer>();
		orientationPath.add(0);
		
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
		
		MowerChromosome mc = (MowerChromosome) c;
		ArrayList<String> s;
		
		//The program is executed repeatedly
		while(numLeftRotations < 100 && numMovements < 100 && numLawnCut < numCols * numRows && numNoMovement < 2) {
			//Code to control those cases in which no movement can be done
			lastNumLeftRotations = numLeftRotations;
			lastNumMovement = numMovements;
			
			//s = toArray("progn(progn(suma(progn(progn(izquierda(suma(izquierda(salta(progn(avanza(izquierda(salta(progn(suma(progn(izquierda(suma(progn(salta(progn(progn(suma(progn(progn(izquierda(suma(izquierda(salta(progn(avanza(izquierda(salta(progn(suma(progn(izquierda(suma(progn(salta(progn(progn(suma(progn(progn(izquierda(suma(izquierda(salta(progn(avanza(izquierda(salta(progn(suma(progn(izquierda(suma(progn");
			s = toArray(mc.getFenotype());
			//System.out.println(s);
			procesa(s);
			
			//Code to control those cases in which no movement can be done
			if(lastNumLeftRotations == numLeftRotations && lastNumMovement == numMovements) {
				numNoMovement++;
			}
			else {
				numNoMovement = 0;
			}
		}
		
		//Save the path and the garden
		mc.setPath(path);
		mc.setOrientationPath(orientationPath);
		mc.setGarden(garden);
		
		return Double.valueOf(numLawnCut);
	}
	
	private ArrayList<String> toArray(String fenotype){
		String[]s = fenotype.split("\\(");
		for(int i = 0; i < s.length; i++) {
			s[i] = s[i].replaceAll("\\)", "");
		}
		ArrayList<String> a = new ArrayList<String>(Arrays.asList(s));
		for (int i = 0; i < a.size(); i++) {
			if(a.get(i).equals("")) {
				a.remove(i);
			}
		}
		return a;
	}
	
	private Pair<Integer, Integer> procesa(ArrayList<String> s) {
		if(!s.isEmpty()) {
			if(s.get(0).equals("izquierda")) {
				return izquierda();
			}
			else if(s.get(0).equals("avanza")) {
				return avanza();
			}
			else if(s.get(0).equals("suma")) {
				s.remove(0);
				Pair<Integer, Integer> primero = procesa(s);
				if(!s.isEmpty()) {
					s.remove(0);
					Pair<Integer, Integer> segundo = procesa(s);
					
					return suma(primero, segundo);
				}
				else {
					return new Pair<Integer, Integer>(0, 0);
				}
			}
			else if(s.get(0).equals("progn")) {
				s.remove(0);
				Pair<Integer, Integer> primero = procesa(s);
				if(!s.isEmpty()) {
					s.remove(0);
					Pair<Integer, Integer> segundo = procesa(s);
					return progn(primero, segundo);
				}
				else {
					return new Pair<Integer, Integer>(0, 0);
				}				
			}
			else if(s.get(0).equals("salta")) {
				s.remove(0);
				
				return salta(procesa(s));
			}
			else { //cte
				return constanteAleatoria(s.get(0));
			}
		}
		else {
			return new Pair<Integer, Integer>(0, 0);
		}
	}
	
	private Pair<Integer, Integer> izquierda(){
		orientation = (orientation + 1) % 4; //turns left
		numLeftRotations++;
		return new Pair<Integer, Integer>(0, 0);
	}
	
	private Pair<Integer, Integer> constanteAleatoria(String s){
		String[] sep = s.split(",");
		return new Pair<Integer, Integer>(Integer.parseInt(sep[0]), Integer.parseInt(sep[1]));
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
	
	private Pair<Integer, Integer> suma(Pair<Integer, Integer> pos1, Pair<Integer, Integer> pos2){
		return new Pair<Integer, Integer>((pos1.getFirst() + pos2.getFirst()) % numCols, (pos2.getSecond() + pos2.getSecond()) % numRows);
	}
	
	private Pair<Integer, Integer> progn(Pair<Integer, Integer> pos1, Pair<Integer, Integer> pos2){
		return pos2;
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

	public ArrayList<Pair<Integer, Integer>> getPath(){
		return path;
	}
	
	public ArrayList<Integer> getOrientationPath(){
		return orientationPath;
	}
	
}
