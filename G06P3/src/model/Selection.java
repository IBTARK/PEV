package model;

import java.util.ArrayList;

import model.listRep.chromosomes.Chromosome;

public interface Selection {

	public ArrayList<Representation> select(ArrayList<Representation> population);
}
