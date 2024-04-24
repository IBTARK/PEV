package model.listRep.genes;

import java.util.Random;

public class MowerGene extends Gene{

	public MowerGene() {
		super(1);
	}
	
	public Double initializeGeneRandom(Random random) {
		alleles.add(random.nextInt(256));
		return (int) alleles.get(0)*1.0;
	}
	
	public Integer getAllele() {
		return (int) alleles.get(0);
	}
	
	
	public void setAllele(int numFlight){
		alleles.set(0, numFlight);
	}

	@Override
	protected boolean valid() {
		return true;
	}

	@Override
	public MowerGene clone() {
		MowerGene clone = new MowerGene();
		clone.setFenotype(Double.valueOf((double) alleles.get(0)));
		return clone;
	}

}
