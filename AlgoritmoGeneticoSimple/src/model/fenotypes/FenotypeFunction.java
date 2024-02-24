package model.fenotypes;

import model.genes.Gene;

public abstract class FenotypeFunction <T extends Gene> implements Cloneable{
	
	protected Double minValue; //Maximum value of the fenotype of the gene 
	protected Double maxValue; //Minimum value of the fenotype of the gene 
	protected boolean hasMinValue; //True if the gene has a minValue
	protected boolean hasMaxValue; //True if the gene has a maxValue
	
	public FenotypeFunction() {
		hasMinValue = false;
		hasMaxValue = false;
	}
	
	public FenotypeFunction(Double minValue, Double maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		hasMinValue = true;
		hasMaxValue = true;
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
	
	/**
	 * 
	 * @return true if the fenotype of the gene has a minimum value
	 */
	public boolean hasMinValue() {
		return hasMinValue;
	}
	
	/**
	 * 
	 * @return true if the fenotype of the gene has a maximum value
	 */
	public boolean hasMaxValue() {
		return hasMaxValue;
	}
	
	/**
	 * set the minimum value of the fenotype and set to true the indicator: hasMinValue
	 */
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
		hasMinValue = true;
	}
	
	/**
	 * set the maximum value of the fenotype and set to true the indicator: hasMaxValue
	 */
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
		hasMaxValue = true;
	}
	

	public void hasMinValue(boolean hasMinValue) {
		this.hasMinValue = hasMinValue;
	}
	

	public void hasMaxValue(boolean hasMaxValue) {
		this.hasMaxValue = hasMaxValue;
	}
	
	public abstract Double apply(T g);
	
	public abstract FenotypeFunction <T> clone();
}
