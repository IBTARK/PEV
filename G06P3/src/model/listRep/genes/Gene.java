package model.listRep.genes;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class to represent a generic Gene
 */
public abstract class Gene implements Cloneable{
	
	protected ArrayList<Object> alleles; //List of alleles (components that form the gene)
	protected int geneLength; //Number of alleles in the gene
	protected Double fenotype;
	
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
	 * @return the fenotype of the gene
	 */
	public Double getFenotype() {
		return fenotype;
	}
	
	/**
	 * set one allele of the gene and compute its fenotype
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
	
	/**
	 * set the alleles of the gene and compute its fenotype
	 * @param alleles
	 */
	public void setAlleles(ArrayList<Object> alleles) {
		this.alleles = alleles;
	}
	
	/**
	 * set the fenotype of the gene
	 * @param fenotype
	 */
	public void setFenotype(Double fenotype) {
		this.fenotype = fenotype;
	}
	
	/**
	 * random initialization of the gene
	 * 
	 * @param random
	 * @return the fenotype of the gene
	 */
	public abstract Double initializeGeneRandom(Random random);
	
	protected abstract boolean valid();
	
	public abstract Gene clone();
}
