package model.selection;

import java.util.List;

import model.chromosomes.Chromosome;

public interface Selection {
	
	public abstract List<Chromosome> select(List<Chromosome> population);

}
