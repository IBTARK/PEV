package model.fenotypes;

import model.genes.FlightGene;
import model.genes.RealGene;

public class AirportRepresentation extends FenotypeFunction<FlightGene>{

	public AirportRepresentation() {
		super();
	}
	
	public AirportRepresentation(Double minValue, Double maxValue) {
		super(minValue, maxValue);
	}
	
	@Override
	public Double apply(FlightGene g) {		
		return g.getAllele().doubleValue(); //TODO
	}

	@Override
	public FenotypeFunction clone() {
		return new AirportRepresentation(Double.valueOf(minValue), Double.valueOf(maxValue));
	}
}
