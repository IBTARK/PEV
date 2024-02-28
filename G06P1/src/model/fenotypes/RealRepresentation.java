package model.fenotypes;

import model.genes.Gene;
import model.genes.RealGene;

public class RealRepresentation extends FenotypeFunction<RealGene>{
	
	public RealRepresentation() {
		super();
	}
	
	public RealRepresentation(Double minValue, Double maxValue) {
		super(minValue, maxValue);
	}
	
	@Override
	public Double apply(RealGene g) {
		return g.getAllele(0);
	}

	@Override
	public FenotypeFunction clone() {
		return new RealRepresentation(Double.valueOf(minValue), Double.valueOf(maxValue));
	}
}
