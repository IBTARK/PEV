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
	 * Sets the fitness of the function
	 * 
	 * @param fitnessFunction fitness function used 
	 * @return value of the fitness of the chromosome
	 */
	public Double getFitness(FitnessFunction fitnessFunction) {
		fitness = fitnessFunction.apply(getGenesFenotypes());
		return fitness;
	}
}
