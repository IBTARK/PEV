package model.listRep.genes;

import java.util.ArrayList;
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
		ArrayList<Object> newAlleles = new ArrayList<Object>();
		for(int i = 0; i < clone.getGeneLength(); i++) {
			newAlleles.add(alleles.get(i));
		}
		
		clone.setAlleles(newAlleles);
		
		clone.setFenotype((int)alleles.get(0)*1.0);
		return clone;
	}

}
