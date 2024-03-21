package model.genes;

import java.util.ArrayList;
import java.util.Random;

import model.airport.FlightType;
import model.fenotypes.FenotypeFunction;

public class FlightGene extends Gene{
	
	private Double TLA;
	
	public FlightGene(int numFlight, FenotypeFunction fenotypeFunction) {
		super(1, fenotypeFunction);
		alleles.add(numFlight);
	}
	
	/**
	 * Returns the allele of the gene
	 */
	public Integer getAllele() {
		return (int) alleles.get(0);
	}
	
	
	public void setAllele(int numFlight){
		alleles.set(0, numFlight);
	}
	
	public double getTLA() {
		return TLA;
	}
	
	public void setTLA(double TLA) {
		this.TLA = TLA;
	}

	@Override
	public Double initializeGeneRandom(Random random) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean valid() {
		return true;
	}

	@Override
	public Gene clone() {
		
		FlightGene clone = new FlightGene(Integer.valueOf((int) alleles.get(0)), fenotypeFunction.clone());
		
		return clone;
	}
	
}
