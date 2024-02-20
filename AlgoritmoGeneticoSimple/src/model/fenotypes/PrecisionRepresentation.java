package model.fenotypes;

import model.genes.BinaryGene;
import model.genes.Gene;

/**
 * Class to determine the fenotype of a gene using the next function:
 * 	x(v) = xmin + bin2dec(v) * ((xmax - xmin)/ (2^geneLength -1))
 */
public class PrecisionRepresentation extends FenotypeFunction<BinaryGene>{
	private Double xmin;
	private Double xmax;
	private int geneLength;
	
	public PrecisionRepresentation(Double xmin, Double xmax, int geneLength) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.geneLength = geneLength;
	}
	
	public Double apply(BinaryGene g){
		return xmin + bin2dec(g) * ((xmax - xmin)/ (Math.pow(2, geneLength) - 1));
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
			fenotype += (g.getAllele(i) ? Math.pow(2, g.getGeneLength() - i - 1) : 0);
		}
		
		return fenotype;
	}

	@Override
	public PrecisionRepresentation clone() {
		return new PrecisionRepresentation(Double.valueOf(xmin), Double.valueOf(xmax), geneLength);
	}
}
