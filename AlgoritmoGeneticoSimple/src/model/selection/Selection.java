package model.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.chromosomes.Chromosome;

public abstract class Selection {
	
	public Selection() {
	}
	
	public abstract ArrayList<Chromosome> select(List<Chromosome> population);
}
