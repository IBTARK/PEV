package model.fenotypes;

import model.genes.BinaryGene;
import model.genes.Gene;

/**
 * Class to determine the fenotype of a gene using the next function:
 * 	x(v) = xmin + bin2dec(v) * ((xmax - xmin)/ (2^geneLength -1))
 */
public class PrecisionRepresentation implements FenotypeFunction<BinaryGene>{
	private Double xmax;
	private Double xmin;
	private int geneLength;
	
	
	public Double apply(BinaryGene g){
		return xmin + bin2dec(g) * ((xmax - xmin)/ (2^geneLength -1));
	}
	
	/**
	 * Method to obtain the decimal representation of a binary gene
	 * 
	 * @param g BinaryGene
	 * @return decimal representation of a binary gene
	 */
	private int bin2dec(BinaryGene g) {
		int fenotype = 0;
		for(int i = 0; i < g.getGeneLength(); i++){
			fenotype += 2 ^ (int) g.getAllele(i);
		}
		
		return fenotype;
	}
}
