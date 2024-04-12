package model.listRep.crossover;

import model.listRep.chromosomes.Chromosome;

public interface Crossover {
	public void cross(Chromosome c1, Chromosome c2);
}
