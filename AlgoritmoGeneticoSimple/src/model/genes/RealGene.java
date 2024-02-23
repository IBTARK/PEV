package model.genes;

import java.util.ArrayList;
import java.util.Random;

import model.fenotypes.FenotypeFunction;

public class RealGene extends Gene{

	public RealGene(int geneLength, FenotypeFunction fenotypeFunction) {
		super(geneLength, fenotypeFunction);
	}
	
	/**
	 * Random initialization of the real gene
	 */
	public void initializeGeneRandom(Random random) {
		for(int i = 0; i < geneLength; i++) {
			alleles.add(random.nextDouble());
		}
	}
	
	/**
	 * Given a position returns the allele in that position of the gene
	 * @param pos position of the wanted allele
	 * @return the allele in the position pos of the gene
	 */
	public Double getAllele(int pos) {
		return (double) alleles.get(pos);
	}
	
	public void setAlleles(ArrayList<Object> alleles) {
		this.alleles = alleles;
	}

	@Override
	protected boolean valid() {
		return true;
	}

	@Override
	public RealGene clone() {
		RealGene clone = new RealGene(Integer.valueOf(geneLength), fenotypeFunction.clone());
		
		ArrayList<Object> cloneAlleles = new ArrayList<Object>();
		
		for(int i = 0; i < geneLength; i++) {
			cloneAlleles.add(Double.valueOf((double) alleles.get(i)));
		}
		
		clone.setAlleles(cloneAlleles);
		
		return clone;
	}

}
