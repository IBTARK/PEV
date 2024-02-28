package model.evaluationFunctions;

import model.chromosomes.Chromosome;

public class Michalewicz implements EvaluationFunction{
	
	private int dim;
	private int m;
	
	public Michalewicz(int dim) {
		this.dim = dim;
		m = 10;
	}

	@Override
	public Double apply(Chromosome c) {
		Double val = 0.0;
		
		for(int i = 0; i < dim; i++) {
			val -= Math.sin(c.getGeneFenotype(i)) * Math.pow(Math.sin(((i+1) * Math.pow(c.getGeneFenotype(i), 2))/Math.PI), 2 * m);
		}
		return val;
	}

}
