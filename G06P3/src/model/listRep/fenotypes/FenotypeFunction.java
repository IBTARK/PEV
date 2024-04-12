package model.listRep.fenotypes;

import model.listRep.genes.Gene;

public abstract class FenotypeFunction <T extends Gene> implements Cloneable{
	
	protected Double minValue; //Maximum value of the fenotype of the gene 
	protected Double maxValue; //Minimum value of the fenotype of the gene 
	
	public FenotypeFunction() {
		minValue = Double.MIN_VALUE;
		maxValue = Double.MAX_VALUE;
	}
	
	public FenotypeFunction(Double minValue, Double maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	/**
	 * 
	 * @return the minimum value of the fenotype of the gene 
	 */
	public Double getMinValue() {
		return minValue;
	}
	
	/**
	 * 
	 * @return the maximum value of the fenotype of the gene
	 */
	public Double getMaxValue() {
		return maxValue;
	}
	
	public abstract Double apply(T g);
	
	public abstract FenotypeFunction <T> clone();
}
