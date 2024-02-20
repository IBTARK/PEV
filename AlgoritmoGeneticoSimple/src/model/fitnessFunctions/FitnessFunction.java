package model.fitnessFunctions;

import model.chromosomes.Chromosome;

/**
 * Interface that every fitnessFunction has to implement
 */
public interface FitnessFunction {
	public Double apply(Chromosome c);
}
