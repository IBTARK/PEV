package model.genes;

import java.util.ArrayList;
import java.util.Random;

import model.fenotypes.FenotypeFunction;

/**
 * Class to represent a generic Gene
 */
public abstract class Gene{
	
	protected ArrayList<Object> alleles; //List of alleles (components that form the gene)
	protected int geneLength; //Number of alleles in the gene
	
	public Gene(int geneLength) {
		this.geneLength = geneLength;
		alleles = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return the number of alleles of the gene
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
	
	/**
	 * @return a list with all the alleles of the gene
	 */
	public ArrayList<Object> getAlleles() {
		return alleles;
	}
	
	/**
	 * Computes the fenotype of the gene
	 * @param function is a class that implements the interface FenotypeFunction. 
	 * 		  Indicates the way to compute the fenotype.
	 * @return the fenotype as a double.
	 */
	public Double computeFenotype(FenotypeFunction<Gene> function) {
		return function.apply(this);
	}
	
	/**
	 * 
	 * @param index
	 * @param allele
	 * @return
	 */
	public Object setAllele(int index, Object allele) {
		Object old = alleles.get(index);
		
		alleles.set(index, allele);
		
		//If the change is not valid is reverted
		if(!valid()) alleles.set(index, old);
		
		return old;
	}
	
	public abstract void initializeGeneRandom(Random random);
	
	protected abstract boolean valid();
}
