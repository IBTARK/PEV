package model.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.chromosomes.Chromosome;

public interface Selection {
	
	public ArrayList<Chromosome> select(List<Chromosome> population);

}
