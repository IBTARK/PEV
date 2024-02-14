package model.fitnessFunctions;

import java.util.ArrayList;

/**
 * Interface that every fitnessFunction has to implement
 */
public interface FitnessFunction {
	public Double apply(ArrayList<Double> genesFenotypes);
}
