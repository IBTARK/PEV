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
import model.crossover.CrossoverType;
import model.evaluationFunctions.EvaluationFunction;
import model.evaluationFunctions.EvaluationFunctionType;
import model.fenotypes.FenotypeFunction;
import model.fenotypes.FenotypeType;
import model.fitnessFunctions.FitnessFunction;
import model.mutation.Mutation;
import model.mutation.MutationType;
import model.selection.Selection;
import model.selection.SelectionType;

public class GeneticAlgorithm implements Observable<GenAlgObserver>{
	
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
	private EvaluationFunction evaluationFunction;
	private boolean minimization;
	
	private ArrayList<Chromosome> population;
	
	private List<GenAlgObserver> observerList;
	
	private double absoluteBestFitness; //Is the best fitness of all the generations
	private double absoluteBestEvaluation; //Is the evaluation of the best fitness of all the generations
	
	public GeneticAlgorithm() {
		random = new Random();
		population = new ArrayList<Chromosome>();
		
		//List of observers
		observerList = new ArrayList<GenAlgObserver>();
		
		absoluteBestFitness = Double.MIN_VALUE;
	}
	
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
		
		this.evaluationFunction = evaluationFunction;
		this.minimization = minimization;
		fitnessFunction = new FitnessFunction(minimization, evaluationFunction);
		
		population = new ArrayList<Chromosome>();
		
		//List of observers
		observerList = new ArrayList<GenAlgObserver>();
		
		absoluteBestFitness = Double.MIN_VALUE;
	}
	
	public ArrayList<Chromosome> execute(){
		//Generate an initial population
		generatePopulation();
		//Evaluate the population
		evaluate();
		
		for(int i = 0; i < generations; i++) {
			//Communicate with the observers
			onGenCompleted(i, population);
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
		population = new ArrayList<Chromosome>();
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
	
	//If necessary mutate the chromosome
	private void mutate() {
		for(Chromosome c : population) {
			mutation.mutate(c);
		}
	}

//**************************************************************************************
//Observable interface
	@Override
	/**
	 * Add an observer
	 * 
	 * @param o observer to be added
	 */
	public void addObserver(GenAlgObserver o) {
		if(!observerList.contains(o)) {
			observerList.add(o);
			o.onRegister();
		}
	}

	@Override
	/**
	 * Remove an observer
	 * 
	 * @param o observer to be removed
	 */
	public void removeObserver(GenAlgObserver o) {
		if(observerList.contains(o)) {
			observerList.remove(o);
		}
	}
	
	/**
	 * Actions executed when a generation has been completed
	 * 
	 * @param generation actual generation
	 * @param population population of the generation
	 */
	private void onGenCompleted(int generation, ArrayList<Chromosome> population) {
		
		Collections.sort(population, Collections.reverseOrder());
		
		if(population.get(0).getFitness() > absoluteBestFitness) {
			absoluteBestFitness = population.get(0).getFitness();
			absoluteBestEvaluation = population.get(0).getEvaluation();
		}
		
		double meanGeneration = 0;
		for(Chromosome c : population) {
			meanGeneration += c.getEvaluation();
		}
		meanGeneration /= population.size();
		
		for(GenAlgObserver o : observerList) {
			o.onGenCompleted(generation, absoluteBestEvaluation, population.get(0).getEvaluation(), meanGeneration);
		}
		
		System.out.println("Tamaño: " + population.size());
		System.out.println("Generación: " + generation);
		System.out.println("MejorTotal: " + absoluteBestEvaluation);
		System.out.println("MejorGen: " + population.get(0).getEvaluation());
		System.out.println("MediaGen: " + meanGeneration);
		
		
		Collections.shuffle(population);
	}

//**************************************************************************************
//Getters
	
	/**
	 * @return an array list with the names of the types of selection
	 */
	public ArrayList<String> getSelectionTypes(){
		ArrayList<String> selectionTypes = new ArrayList<String>();
		
		for(SelectionType st : SelectionType.values()) {
			selectionTypes.add(st.toString());
		}
		
		return selectionTypes;
	}
	
	/**
	 * @return an array list with the names of the types of crossover
	 */
	public ArrayList<String> getCrossoverTypes(){
		ArrayList<String> crossoverTypes = new ArrayList<String>();
		
		for(CrossoverType ct : CrossoverType.values()) {
			crossoverTypes.add(ct.toString());
		}
		
		return crossoverTypes;
	}
	
	/**
	 * @return an array list with the names of the types of mutation
	 */
	public ArrayList<String> getMutationTypes(){
		ArrayList<String> mutationTypes = new ArrayList<String>();
		
		for(MutationType mt : MutationType.values()) {
			mutationTypes.add(mt.toString());
		}
		
		return mutationTypes;
	}
	
	/**
	 * @return an array list with the names of the types of representation
	 */
	public ArrayList<String> getFenotypeTypes(){
		ArrayList<String> fenotypeTypes = new ArrayList<String>();
		
		for(FenotypeType ft : FenotypeType.values()) {
			fenotypeTypes.add(ft.toString());
		}
		
		return fenotypeTypes;
	}
	
	/**
	 * @return an array list with the names of the types of evaluation functions
	 */
	public ArrayList<String> getEvaluationFunctionTypes(){
		ArrayList<String> evaluationTypes = new ArrayList<String>();
		
		for(EvaluationFunctionType et : EvaluationFunctionType.values()) {
			evaluationTypes.add(et.toString());
		}
		
		return evaluationTypes;
	}
	
//**************************************************************************************
//Setters
	
	/**
	 * Set the chromosome type
	 * 
	 * @param chromosomeType
	 */
	public void setChromosomeType (ChromosomeType chromosomeType) {
		this.chromosomeType = chromosomeType;
	}
	
	/**
	 * Set the number of genes
	 * 
	 * @param numGenes
	 */
	public void setNumGenes (int numGenes) {
		this.numGenes = numGenes;
	}
	
	/**
	 * Set the genes lengths
	 * 
	 * @param genesLengths
	 */
	public void setGenesLengths (ArrayList<Integer> genesLengths) {
		this.genesLengths = genesLengths;
	}
	
	/**
	 * Set the genes fenotypes functions
	 * 
	 * @param genesFenotypesFunctions
	 */
	public void setGenesFenotypesFunctions (List<FenotypeFunction> genesFenotypesFunctions) {
		this.genesFenotypesFunctions = genesFenotypesFunctions;
	}
	
	/**
	 * Set the population size
	 * 
	 * @param populationSize
	 */
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}
	
	/**
	 * set the number of generations
	 * 
	 * @param generations
	 */
	public void setNumGenerations(int generations) {
		this.generations = generations;
	}
	
	/**
	 * Set the crossover probability
	 * 
	 * @param crossoverPctg
	 */
	public void setCrossoverPctg(double crossoverPctg) {
		this.crossoverPctg = crossoverPctg;
	}
	
	/**
	 * Set the selection type
	 * 
	 * @param selection
	 */
	public void setSelection(Selection selection) {
		this.selection = selection;
	}
	
	/**
	 * Set the crossover
	 * 
	 * @param crossover
	 */
	public void setCrossover(Crossover crossover) {
		this.crossover = crossover;
	}
	
	/**
	 * Set the mutation
	 * 
	 * @param mutation
	 */
	public void setMutation(Mutation mutation) {
		this.mutation = mutation;
	}
	
	/**
	 * Set the evaluation function and update the fitness function
	 * 
	 * @param evaluationFunction
	 */
	public void setEvaluationFunction(EvaluationFunction evaluationFunction) {
		this.evaluationFunction = evaluationFunction;
		fitnessFunction = new FitnessFunction(minimization, evaluationFunction);
	}
	
	/**
	 * Set the minimization variable and update the fitness function
	 * 
	 * @param minimization
	 */
	public void setMinimization(boolean minimization) {
		this.minimization = minimization;
		fitnessFunction = new FitnessFunction(minimization, evaluationFunction);
	}
}
