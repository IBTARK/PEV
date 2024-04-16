package model.evaluationFunctions;

import model.Representation;

/**
 * Interface that every fitnessFunction has to implement
 */
public interface EvaluationFunction{
	public Double apply(Representation c);
}
