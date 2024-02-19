package model.fitnessFunctions;

import java.util.ArrayList;

public class Funcion1 implements FitnessFunction{

	@Override
	public Double apply(ArrayList<Double> genesFenotypes) {
		return Math.pow(genesFenotypes.get(0), 2) + 2 * Math.pow(genesFenotypes.get(1), 2);
	}

}
