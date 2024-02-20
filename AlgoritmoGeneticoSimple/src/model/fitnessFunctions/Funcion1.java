package model.fitnessFunctions;

import model.chromosomes.Chromosome;

public class Funcion1 implements FitnessFunction{

	@Override
	public Double apply(Chromosome c) {
		return Math.pow(c.getGeneFenotype(0), 2) + 2 * Math.pow(c.getGeneFenotype(1), 2);
	}

}
