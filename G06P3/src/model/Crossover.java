package model;

import model.listRep.chromosomes.Chromosome;

public interface Crossover {
	public void cross(Representation c1, Representation c2);
}
