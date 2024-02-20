package model.fenotypes;

import model.genes.Gene;

public abstract class FenotypeFunction <T extends Gene> implements Cloneable{
	public abstract Double apply(T g);
	
	public abstract FenotypeFunction <T> clone();
}
