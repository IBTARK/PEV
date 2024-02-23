package model.evaluationFunctions;

import model.chromosomes.Chromosome;
import model.genes.Gene;

public class HolderTable implements EvaluationFunction{

	@Override
	public Double apply(Chromosome c) {
		Double f0 = c.getGeneFenotype(0);
		Double f1 = c.getGeneFenotype(1);
		return -Math.abs(Math.sin(f0) * Math.cos(f1) * Math.exp(Math.abs(1 - ((Math.sqrt(Math.pow(f0, 2) + Math.pow(f1, 2)) / Math.PI)))));
	}

}
