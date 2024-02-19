package model.chromosomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.fenotypes.FenotypeFunction;
import model.fitnessFunctions.FitnessFunction;
import model.genes.Gene;

/**
 * Class to represent a generic chromosome
 */
public abstract class Chromosome {
	protected List<Gene> genes; //List of genes that form the chormosome
	protected List<FenotypeFunction> genesFenotypesFunctions; //List of functions to compute the fenotypes of each gene of the chromosome
	
	protected List<Double> fenotype; //List of doubles that represent the fenotype of the chromosome (list of the fenotypes of the genes that form the chromosome)
	private Random random;
	
	protected int chromoseLength; //number of genes
	
	protected double fitness; //fitness of the chromosome
	protected double score; //relative score (fitness_i/fitness_Total)
	protected double scoreAccumulated; //accumulated relative score
	
	public Chromosome(int chromosomeLenght, List<FenotypeFunction> genesFenotypesFunctions) {
		this.chromoseLength = chromosomeLenght;
		this.genesFenotypesFunctions = genesFenotypesFunctions;
		random = new Random();
	}
	
	public Chromosome(Chromosome c) {
		genes = new ArrayList<Gene>();
		
		for(int i = 0; i < c.getChromosomeLength(); i++) {
			genes.add(new Gene(c.getGene(i)));
		}
	}
	
	/**
	 * Method that initializes each gene of the chormosome randomly
	 */
	public void initializeChromosomeRandom() {
		for(int i = 0; i < chromoseLength; i++) {
			genes.get(i).initializeGeneRandom(random);
		}
	}
	
	/**
	 * 
	 * @return list with the fenotypes of each gene of the chromosome
	 */
	private ArrayList<Double> getGenesFenotypes(){
		ArrayList<Double> genesFenotypes = new ArrayList<>();
		
		for(int i = 0; i < chromoseLength; i++) {
			genesFenotypes.add(genes.get(i).getFenotype(genesFenotypesFunctions.get(i)));
		}
		
		return genesFenotypes;
	}
	
	/**
	 * Compute the fitness of the chormosome
	 * 
	 * @param fitnessFunction function to compute the fitness of the chormosome
	 */
	public void computeFitness(FitnessFunction fitnessFunction) {
		fitness = fitnessFunction.apply(getGenesFenotypes());
	}
	
	/**
	 * Compute the score and the accumulated score
	 * 
	 * @param totalFitness total fitness of the population
	 * @param previousAccumulated accumulated score of the previous choromosome of the population
	 */
	public void computeScoreAndAccumulated(Double totalFitness, Double previousAccumulated) {
		score = fitness / totalFitness;
		scoreAccumulated = previousAccumulated + score; 
	}
	
	
	/**
	 * returns the fitness of the chromosome
	 * 
	 * @return value of the fitness of the chromosome
	 */
	public Double getFitness() {
		return fitness;
	}
	
	/**
	 * returns the score of the chromosome
	 * 
	 * @return value of the score of the chromosome
	 */
	public Double getScore() {
		return score;
	}
	
	/**
	 * returns the accumulated score of the chromosome
	 * 
	 * @return value of the accumulated score of the chromosome
	 */
	public Double getScoreAccumulated() {
		return scoreAccumulated;
	}
	
	/**
	* return the genes list of the chromosome
	*
	* @return value of the genes length
	*/
	public List<Gene> getGenes(){
		return genes;
	}
	
	/**
	* return the gen in a determined position
	*
	* @return value of the requested gen
	*/
	public Gene getGene(int index) {
		return genes.get(index);
	}
	
	/**
	* return the lenght of the chromosome
	*
	* @return value of the chromosome length
	*/
	public int getChromosomeLength(){
		return chromoseLength;
	}
	
}
