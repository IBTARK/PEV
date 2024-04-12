package model.listRep.evaluationFunctions;

import model.listRep.chromosomes.Chromosome;

/**
 * Interface that every fitnessFunction has to implement
 */
public interface EvaluationFunction{
	public Double apply(Chromosome c);
}
