package model.treeRep.symbols;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Symbols of the mower problem
 */
public class MowerSymbols extends Symbols{
	private int numCols;
	private int numRows;
	
	public MowerSymbols(int numCols, int numRows) {
		super(new ArrayList<String>(Arrays.asList("IZQUIERDA", "AVANZA", "CONSTALEAT")), 
				new ArrayList<String>(Arrays.asList("SUMA", "SALTA", "PROGN")), 
				new ArrayList<Integer>(Arrays.asList(2, 1, 2)), numCols, numRows);
		
		this.numCols = numCols;
		this.numRows = numRows;
	}

	@Override
	public Symbols clone() {
		return new MowerSymbols(numCols, numRows);
	}
}
