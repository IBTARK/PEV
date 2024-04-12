package model.treeRep.symbols;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Symbols of the mower problem
 */
public class MowerSymbols extends Symbols{
	public MowerSymbols() {
		super(new ArrayList<String>(Arrays.asList("IZQUIERDA", "AVANZA")), 
				new ArrayList<String>(Arrays.asList("SUMA, SALTA")), 
				new ArrayList<Integer>(Arrays.asList(2, 1)));
	}

	@Override
	public Symbols clone() {
		return new MowerSymbols();
	}
}
