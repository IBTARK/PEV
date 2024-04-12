package model.listRep.selection;

import java.util.ArrayList;

import model.listRep.chromosomes.Chromosome;

public interface Selection {

	public ArrayList<Chromosome> select(ArrayList<Chromosome> population);
}
