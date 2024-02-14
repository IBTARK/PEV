package model.genes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.fenotypes.FenotypeFunction;

/**
 * Class to represent a generic Gene
 */
public abstract class Gene {
	protected List<Object> alleles; //List of alleles (components that form the gene)
	protected int geneLength; //Number of alleles in the gene
	
	public Gene(int geneLength) {
		this.geneLength = geneLength;
		alleles = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return the length of the gene
	 */
	public int getGeneLength() {
		return geneLength;
	}
	
	/**
	 * Given a position returns the allele in that position of the gene
	 * @param pos position of the wanted allele
	 * @return the allele in the position pos of the gene
	 */
	public Object getAllele(int pos) {
		return alleles.get(pos);
	}
	
	public abstract void initializeGeneRandom(Random random);
	
	/**
	 * Gets the fenotype of the gene
	 * @param function is a class that implements the interface FenotypeFunction. 
	 * 		  Indicates the way to compute the fenotype.
	 * @return the fenotype as a double.
	 */
	public Double getFenotype(FenotypeFunction<Gene> function) {
		return function.apply(this);
	}
}
