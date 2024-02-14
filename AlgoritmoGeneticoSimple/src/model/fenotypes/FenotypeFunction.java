package model.fenotypes;

import model.genes.Gene;

public interface FenotypeFunction <T extends Gene> {
	public Double apply(T g);
}
