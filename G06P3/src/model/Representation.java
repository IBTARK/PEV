package model;

import java.util.Random;

import model.listRep.chromosomes.Chromosome;

public abstract class Representation implements Comparable<Representation>, Cloneable{
	protected Random random;
	
	protected double evaluation; //evaluation of the chromosome
	protected double fitness; //fitness of the chromosome
	protected double score; //relative score (fitness_i/fitness_Total)
	protected double scoreAccumulated; //accumulated relative score
	
	public Representation() {
		random = new Random();
	}

//*********************************************************************************
// Comparable
	
	public int compareTo(Representation c) {
		if(fitness < c.getFitness()) return -1;
		else if(fitness > c.getFitness()) return 1;
		else return 0;
	}

//*********************************************************************************
// Cloneable
	public abstract Representation clone();
	

//*********************************************************************************
// Getters
	
	/**
	 * 
	 * @return value of the evaluation of the representation
	 */
	public Double getEvaluation() {
		return evaluation;
	}
	
	/**
	 * 
	 * @return value of the fitness of the representation
	 */
	public Double getFitness() {
		return fitness;
	}
	
	/**
	 * 
	 * @return value of the score of the representation
	 */
	public Double getScore() {
		return score;
	}
	
	/**
	 * 
	 * @return value of the accumulated score of the representation
	 */
	public Double getScoreAccumulated() {
		return scoreAccumulated;
	}
	
//*********************************************************************************
// Setters
	
	public void setEvaluation(Double evaluation) {
		this.evaluation = evaluation;
	}
	
	public void setFitness(Double fitness) {
		this.fitness = fitness;
	}
	
	public void setScore(Double score) {
		this.score = score;
	}
	
	public void setScoreAccumulated(Double scoreAccumulated) {
		this.scoreAccumulated = scoreAccumulated;
	}
	
}
