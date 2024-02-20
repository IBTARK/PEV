package model.genes;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class to represent binary genes
 */
public class BinaryGene extends Gene{

	public BinaryGene(int geneLength) {
		super(geneLength);
	}
	
	public BinaryGene(BinaryGene g) {
		super(g.getGeneLength());
		
		alleles = new ArrayList<Object>();
		
		for(int i = 0; i < g.getGeneLength(); i++) {
			alleles.add(Boolean.valueOf((boolean) g.getAllele(i)));
		}
		
	}
	
	/**
	 * Random initialization of the binary gene
	 */
	public void initializeGeneRandom(Random random) {
		for(int i = 0; i < geneLength; i++) {
			alleles.add(random.nextBoolean());
		}
	}
	
	/**
	 * Given a position returns the allele in that position of the gene
	 * @param pos position of the wanted allele
	 * @return the allele in the position pos of the gene
	 */
	public Boolean getAllele(int pos) {
		return (boolean) alleles.get(pos);
	}
	
	public void setAlleles(ArrayList<Object> alleles) {
		this.alleles = alleles;
	}

	@Override
	protected boolean valid() {
		return true;
	}
}
