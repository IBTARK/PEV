package model.genes;

import java.util.ArrayList;
import java.util.Random;

import model.fenotypes.FenotypeFunction;

/**
 * Class to represent binary genes
 */
public class BinaryGene extends Gene{

	public BinaryGene(int geneLength, FenotypeFunction fenotypeFunction) {
		super(geneLength, fenotypeFunction);
	}
	
	/**
	 * Random initialization of the binary gene
	 * 
	 * @return the fenotype of the gene
	 */
	public Double initializeGeneRandom(Random random) {
		for(int i = 0; i < geneLength; i++) {
			alleles.add(random.nextBoolean());
		}
		
		return fenotype = fenotypeFunction.apply(this);
	}
	
	/**
	 * Given a position returns the allele in that position of the gene
	 * @param pos position of the wanted allele
	 * @return the allele in the position pos of the gene
	 */
	public Boolean getAllele(int pos) {
		return (boolean) alleles.get(pos);
	}
	
	@Override
	protected boolean valid() {
		return true;
	}

	@Override
	public BinaryGene clone() {
		BinaryGene clone = new BinaryGene(Integer.valueOf(geneLength), fenotypeFunction.clone());
		
		ArrayList<Object> cloneAlleles = new ArrayList<Object>();
		
		for(int i = 0; i < geneLength; i++) {
			cloneAlleles.add(Boolean.valueOf((boolean) alleles.get(i)));
		}
		
		clone.setAlleles(cloneAlleles);
		
		return clone;
	}
}
