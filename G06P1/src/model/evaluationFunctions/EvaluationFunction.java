package model.evaluationFunctions;

import model.chromosomes.Chromosome;

/**
 * Interface that every fitnessFunction has to implement
 */
public interface EvaluationFunction{
	public Double apply(Chromosome c);
}
