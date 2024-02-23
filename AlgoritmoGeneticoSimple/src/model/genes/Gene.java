package model.genes;

import java.util.ArrayList;
import java.util.Random;

import model.fenotypes.FenotypeFunction;

/**
 * Class to represent a generic Gene
 */
public abstract class Gene implements Cloneable{
	
	protected ArrayList<Object> alleles; //List of alleles (components that form the gene)
	protected int geneLength; //Number of alleles in the gene
	protected FenotypeFunction fenotypeFunction; //Function to compute the fenotype of the gene
	
	public Gene(int geneLength, FenotypeFunction fenotypeFunction) {
		this.geneLength = geneLength;
		alleles = new ArrayList<>();
		this.fenotypeFunction = fenotypeFunction;
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
	 * @return the fenotype function
	 */
	public FenotypeFunction getFenotypeFunction(){
		return fenotypeFunction;
	}
	
	/**
	 * Computes the fenotype of the gene
	 * 
	 * @return the fenotype as a double.
	 */
	public Double computeFenotype() {
		return fenotypeFunction.apply(this);
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
	
	public abstract Gene clone();
}
