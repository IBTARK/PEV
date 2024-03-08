package model.genes;

import java.util.ArrayList;
import java.util.Random;

import model.fenotypes.FenotypeFunction;

/**
 * Class to represent integer genes
 */
public class IntegerGene extends Gene{
	
	public IntegerGene(int geneLength, FenotypeFunction fenotypeFunction) {
		super(geneLength, fenotypeFunction);
	}
	
	/**
	 * Random initialization of the integer gene
	 * 
	 * @return the fenotype of the gene
	 */
	public Double initializeGeneRandom(Random random) {
		for(int i = 0; i < geneLength; i++) {
			alleles.add(random.nextInt());
		}
		
		return fenotype = fenotypeFunction.apply(this);
	}
	
	/**
	 * Given a position returns the allele in that position of the gene
	 * @param pos position of the wanted allele
	 * @return the allele in the position pos of the gene
	 */
	public Integer getAllele(int pos) {
		return (int) alleles.get(pos);
	}
	
	@Override
	protected boolean valid() {
		return true;
	}

	@Override
	public IntegerGene clone() {
		IntegerGene clone = new IntegerGene(Integer.valueOf(geneLength), fenotypeFunction.clone());
		
		ArrayList<Object> cloneAlleles = new ArrayList<Object>();
		
		for(int i = 0; i < geneLength; i++) {
			cloneAlleles.add(Integer.valueOf((int) alleles.get(i)));
		}
		
		clone.setAlleles(cloneAlleles);
		
		return clone;
	}
}
