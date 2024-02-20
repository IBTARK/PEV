package model.fitnessFunctions;

import model.chromosomes.Chromosome;

public class MishraBird implements FitnessFunction{

	@Override
	public Double apply(Chromosome c) { 
		return Math.sin(c.getGeneFenotype(1)) * Math.pow(Math.exp(1 - Math.cos(c.getGeneFenotype(0))), 2)
		+ Math.cos(c.getGeneFenotype(0)) * Math.pow(Math.exp(1 - Math.sin(c.getGeneFenotype(1))), 2)
		+ Math.pow(c.getGeneFenotype(0)-c.getGeneFenotype(1), 2);
	}

}
