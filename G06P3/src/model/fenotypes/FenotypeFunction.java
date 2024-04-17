package model.fenotypes;

import model.representation.Representation;

/**
 * Fenotype of a representation (TreeChromosome, chromosome...)
 * @param <T>
 */
public abstract class FenotypeFunction <T extends Representation> implements Cloneable{
	
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
	
	public abstract void apply(T c);
	
	public abstract FenotypeFunction <T> clone();
}
