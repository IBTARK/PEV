package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.chromosomes.BinaryChromosome;
import model.chromosomes.Chromosome;
import model.chromosomes.ChromosomeType;
import model.chromosomes.RealChromosome;
import model.crossover.Crossover;
import model.evaluationFunctions.EvaluationFunction;
import model.fenotypes.FenotypeFunction;
import model.fitnessFunctions.FitnessFunction;
import model.mutation.Mutation;
import model.selection.Selection;

public class GeneticAlgorithm {
	
	private Random random;
	
	private ChromosomeType chromosomeType;
	private int numGenes;
	private List<Integer> genesLengths;
	private List<FenotypeFunction> genesFenotypesFunctions;
	
	private int populationSize;
	private int generations;
	
	private Selection selection;
	
	private double crossoverPctg;
	private Crossover crossover;
	
	private Mutation mutation;
	
	private FitnessFunction fitnessFunction;
	
	ArrayList<Chromosome> population;
	
	public GeneticAlgorithm(ChromosomeType chromosomeType, int numGenes, List<Integer> genesLengths, List<FenotypeFunction> genesFenotypesFunctions,
							int populationSize, int generations, Selection selection, Crossover crossover, double crossoverPctg,
							Mutation mutation, EvaluationFunction evaluationFunction, Boolean minimization) {
		random = new Random();
		
		this.chromosomeType = chromosomeType;
		this.numGenes = numGenes;
		this.genesLengths = genesLengths;
		this.genesFenotypesFunctions = genesFenotypesFunctions;
		
		this.populationSize = populationSize;
		this.generations = generations;
		
		this.selection = selection;
		
		this.crossover = crossover;
		this.crossoverPctg = crossoverPctg;
		
		this.mutation = mutation;
		
		fitnessFunction = new FitnessFunction(minimization, evaluationFunction);
		
		population = new ArrayList<Chromosome>();
	}
	
	public ArrayList<Chromosome> execute(){
		//Generate an initial population
		generatePopulation();
		//Evaluate the population
		evaluate();
		
		for(int i = 0; i < generations; i++) {
			//Selection
			population = selection.select(population);
			//Crossover
			reproduce();
			//Mutation
			mutate();
			//Evaluate the population
			evaluate();
		}
		
		//Sort the population in descending order
		Collections.sort(population, Collections.reverseOrder());
		
		return population;
	}
	
	/**
	 * 
	 * @return an initialized population with "populationSize" individuals
	 */
	private void generatePopulation(){
		for(int i = 0; i < populationSize; i++) {
			//TODO add a line for every type of chromosome
			switch(chromosomeType) {
				case BINARYCHROMOSOME:
				{
					//Generate a binary chromosome
					BinaryChromosome bc = new BinaryChromosome(numGenes, genesLengths, genesFenotypesFunctions);
					//Initialize the binary chromosome 
					bc.initializeChromosomeRandom();
					//Add the chromosome to the population
					population.add(bc);
					
					break;
				}
				case REALCHROMOSOME:
				{
					//Generate a binary chromosome
					RealChromosome rc = new RealChromosome(numGenes, genesLengths, genesFenotypesFunctions);
					//Initialize the binary chromosome 
					rc.initializeChromosomeRandom();
					//Add the chromosome to the population
					population.add(rc);
					
					break;
				}
			}
		}
	}
	
	/**
	 * Compute the fitness, score and accumulatedScore of every chromosome of the population
	 */
	private void evaluate() {
		fitnessFunction.applyEvaluationFunction(population);
	}
	
	/**
	 * Some chromosomes of the ones selected are crossed (depend on the crossoverPctg)
	 */
	private void reproduce() {
		ArrayList<Chromosome> selected = new ArrayList<Chromosome>();
		
		for(Chromosome c : population) {
			if(random.nextDouble() <= crossoverPctg) {
				selected.add(c);
			}
		}
		
		for(int i = 1; i < selected.size(); i = i + 2) {
			crossover.cross(selected.get(i - 1), selected.get(i));
		}
	}
	
	//TODO
	//If necessary mutate the chromosome
	private void mutate() {
		for(Chromosome c : population) {
			mutation.mutate(c);
		}
	}
}
