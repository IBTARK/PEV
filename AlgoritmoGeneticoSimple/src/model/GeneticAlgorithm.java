package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.chromosomes.BinaryChromosome;
import model.chromosomes.Chromosome;
import model.chromosomes.ChromosomeType;
import model.crossover.Crossover;
import model.crossover.CrossoverType;
import model.crossover.SinglePointCrossover;
import model.fenotypes.FenotypeFunction;
import model.fitnessFunctions.FitnessFunction;
import model.mutation.GenericMutation;
import model.mutation.Mutation;
import model.mutation.MutationType;
import model.selection.MontecarloSelection;
import model.selection.Selection;
import model.selection.SelectionType;

public class GeneticAlgorithm {
	
	private Random random;
	
	private ChromosomeType chromosomeType;
	private int numGenes;
	private List<Integer> genesLengths;
	private List<FenotypeFunction> genesFenotypesFunctions;
	private int populationSize;
	private int generations;
	
	private SelectionType selectionType;
	private Selection selection;
	
	private CrossoverType crossoverType;
	private double crossoverPctg;
	private Crossover crossover;
	
	private MutationType mutationType;
	private double mutationPctg;
	private Mutation mutation;
	
	private FitnessFunction fitnessFunction;
	
	ArrayList<Chromosome> population;
	
	public GeneticAlgorithm(ChromosomeType chromosomeType, int numGenes, List<Integer> genesLengths, List<FenotypeFunction> genesFenotypesFunctions,
							int populationSize, int generations, SelectionType selectionType, CrossoverType crossoverType, double crossoverPctg,
							MutationType mutationType, double mutationPctg, FitnessFunction fitnessFunction) {
		random = new Random();
		
		this.chromosomeType = chromosomeType;
		this.numGenes = numGenes;
		this.genesLengths = genesLengths;
		this.genesFenotypesFunctions = genesFenotypesFunctions;
		this.populationSize = populationSize;
		this.generations = generations;
		this.selectionType = selectionType;
		this.crossoverType = crossoverType;
		this.crossoverPctg = crossoverPctg;
		this.mutationType = mutationType;
		this.mutationPctg = mutationPctg;
		
		this.fitnessFunction = fitnessFunction;
		
		population = new ArrayList<Chromosome>();
		
		initializeSelection();
		initializeCrossover();
		initializeMutation();
	}
	
	private void initializeSelection() {
		//TODO add a line for every type of selection
		switch(selectionType) {
			case MONTECARLO:
				selection = new MontecarloSelection();
				break;
		}
	}
	
	private void initializeCrossover() {
		//TODO add a line for every type of crossover
		switch(crossoverType) {
			case SINGLEPOINT:
				crossover = new SinglePointCrossover();
				break;
		}
	}
	
	private void initializeMutation() {
		//TODO add a line for every type of mutation
		switch(mutationType) {
			case GENERIC:
				mutation = new GenericMutation(mutationPctg);
				break;
		}
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
			}
		}
	}
	
	/**
	 * Compute the fitness, score and accumulatedScore of every chromosome of the population
	 */
	private void evaluate() {
		double totalFitness = 0, lastScoreAccumulated = 0;
		
		for(int i = 0; i < populationSize; i++) {
			//Compute the fitness
			population.get(i).computeFitness(fitnessFunction);
			//Add the fitness of the chromosome to the total fitness
			totalFitness += population.get(i).getFitness();
		}
		
		for(int i = 0; i < populationSize; i++) {
			population.get(i).computeScoreAndAccumulated(totalFitness, lastScoreAccumulated);
			lastScoreAccumulated = population.get(i).getScoreAccumulated();
		}
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
