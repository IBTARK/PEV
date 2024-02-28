package model.crossover;

import java.util.ArrayList;

import model.chromosomes.Chromosome;

public interface Crossover {
	public void cross(Chromosome c1, Chromosome c2);
}
