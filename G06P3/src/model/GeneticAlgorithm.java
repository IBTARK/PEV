package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.crossover.Crossover;
import model.crossover.CrossoverType;
import model.evaluationFunctions.EvaluationFunction;
import model.evaluationFunctions.EvaluationFunctionType;
import model.fenotypes.FenotypeFunction;
import model.fenotypes.FenotypeType;
import model.fitnessFunctions.FitnessFunction;
import model.mutation.Mutation;
import model.mutation.MutationType;
import model.representation.Representation;
import model.representation.RepresentationType;
import model.selection.Selection;
import model.selection.SelectionType;
import model.treeRep.symbols.Symbols;
import model.treeRep.trees.InitializationType;
import model.treeRep.trees.MowerTree;

public class GeneticAlgorithm implements Observable<GenAlgObserver>{
	
	private Random random;
	
	private RepresentationType representationType;
	
	//*****************
	//List representation
	private int numGenes;
	private List<Integer> genesLengths;
	private List<FenotypeFunction> genesFenotypesFunctions;
	
	//*****************
	//Tree representation
	private int maxHeight;
	private int minHeight;
	private InitializationType iniType;
	private Symbols symbols;
	
	
	//*************
	private int populationSize;
	private int generations;
	
	private Selection selection;
	
	private double crossoverPctg;
	private Crossover crossover;
	
	private Mutation mutation;
	private double mutationProb;
	
	private FitnessFunction fitnessFunction;
	private EvaluationFunction evaluationFunction;
	private boolean minimization;
	
	private ArrayList<Representation> population;
	
	private double elitism; //[0.0, 1.0]
	
	private List<GenAlgObserver> observerList;
	
	private double absoluteBestFitness; //Is the best fitness of all the generations
	private double absoluteBestEvaluation; //Is the evaluation of the best fitness of all the generations
	
	public GeneticAlgorithm() {
		random = new Random();
		population = new ArrayList<Representation>();
		
		//List of observers
		observerList = new ArrayList<GenAlgObserver>();
		
		absoluteBestFitness = Double.MIN_VALUE;
	}
	
	public GeneticAlgorithm(RepresentationType representationType, int numGenes, List<Integer> genesLengths, List<FenotypeFunction> genesFenotypesFunctions,
							int populationSize, int generations, Selection selection, Crossover crossover, double crossoverPctg,
							Mutation mutation, double mutationPctg, EvaluationFunction evaluationFunction, Boolean minimization) {
		random = new Random();
		
		this.representationType = representationType;
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
		
		population = new ArrayList<Representation>();
		
		//List of observers
		observerList = new ArrayList<GenAlgObserver>();
		
		absoluteBestFitness = Double.MIN_VALUE;
	}
	
	public ArrayList<Representation> execute(){
		ArrayList<Representation> elite = null;
		absoluteBestFitness = Double.MIN_VALUE;
		if(minimization) 
			absoluteBestEvaluation = Double.MAX_VALUE;
		else
			absoluteBestEvaluation = Double.MIN_VALUE;
		
		onFirstGen();
		
		//Generate an initial population
		generatePopulation();
		//Evaluate the population
		evaluate();
		
		for(int i = 0; i < generations; i++) {
			//If necessary save the elite
			if(elitism > 0)
				elite = saveElite(population);
			
			//Selection
			population = selection.select(population);
			//Crossover
			reproduce();
			//Mutation
			mutate();
			
			//If necessary introduce the elite
			if(elitism > 0)
				introduceElite(elite, population);
			
			//Evaluate the population
			evaluate();
			
			//Communicate with the observers
			onGenCompleted(i, population);
		}
		
		//Sort the population in descending order
		Collections.sort(population, Collections.reverseOrder());
		
		onAlgFinished(population.get(0));
		
		return population;
	}
	
	private void generatePopulation() {
		if(representationType == RepresentationType.MOWERTREE) { //Tree representation
			generatePopulationTree();
		}
		else { //List representation
			generatePopulationList();
		}
	}
	
	/**
	 * Generate a population for a representation with base type list
	 * 
	 * @return an initialized population with "populationSize" individuals
	 */
	private void generatePopulationList(){
		
		population = new ArrayList<Representation>();
		for(int i = 0; i < populationSize; i++) {
			//TODO add a line for every type of chromosome
			switch(representationType) {
				
			}
		}
	}
	
	/**
	 * Generate a population for a representation with base type tree
	 * 
	 * @return an initialized population with "populationSize" individuals
	 */
	private void generatePopulationTree(){
		
		population = new ArrayList<Representation>();
		
		if(iniType == InitializationType.RAMPEDANDHALF) {
			for(int i = 2; i <= maxHeight; i++) {
				for(int j = 0; j < (populationSize / (maxHeight - 1)) / 2; j++) {
					//Generate a tree
					MowerTree tc = new MowerTree(genesFenotypesFunctions.get(0), symbols, minHeight, maxHeight);
					tc.fullInitialization(i);
					
					//Add the tree to the population
					population.add(tc);
				}
				
				for(int j = 0; j < (populationSize / (maxHeight - 1)) / 2; j++) {
					//Generate a tree
					MowerTree tc = new MowerTree(genesFenotypesFunctions.get(0), symbols, minHeight, maxHeight);
					tc.growInitialization(i);
					
					//Add the tree to the population
					population.add(tc);
				}
			}
			
			for(int i = population.size(); i < populationSize; i++) {
				//Generate a tree
				MowerTree tc = new MowerTree(genesFenotypesFunctions.get(0), symbols, minHeight, maxHeight);
				tc.growInitialization(maxHeight);
				
				//Add the tree to the population
				population.add(tc);
			}
		}
		else {
			for(int i = 0; i < populationSize; i++) {
				//TODO add a line for every type of initialization
				switch(iniType) {
					case FULL:
					{
						//Generate a tree
						MowerTree tc = new MowerTree(genesFenotypesFunctions.get(0), symbols, minHeight, maxHeight);
						tc.fullInitialization(maxHeight);
						
						//Add the tree to the population
						population.add(tc);
						
						break;
					}
					case GROW:
					{
						//Generate a tree
						MowerTree tc = new MowerTree(genesFenotypesFunctions.get(0), symbols, minHeight, maxHeight);
						tc.growInitialization(maxHeight);
						
						//Add the tree to the population
						population.add(tc);
						
						break;
					}
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
		ArrayList<Representation> selected = new ArrayList<Representation>();
		
		for(Representation c : population) {
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
		for(Representation c : population) {
			if(random.nextDouble() < mutationProb) {
				mutation.mutate(c);
			}
		}
	}
	
	//Save the elite
	private ArrayList<Representation> saveElite(ArrayList<Representation> population){
		int sizeElite = (int) (population.size() * elitism);
		ArrayList<Representation> elite = new ArrayList<Representation>();
		ArrayList<Representation> populationCpy = new ArrayList<Representation>();
		
		//Copy of the population
		for(int i = 0; i < population.size(); i++) {
			populationCpy.add(population.get(i));
		}
		
		//Order the original population in descending order
		Collections.sort(populationCpy, Collections.reverseOrder());
		
		for(int i = 0; i < sizeElite; i++) {
			elite.add(populationCpy.get(i).clone());
		}
		
		return elite;
	}
	
	//Introduce elitism if necessary
	private void introduceElite(List<Representation> elite, List<Representation> newPopulation) {
		//Order the new population in descending order
		Collections.sort(newPopulation, Collections.reverseOrder());
		
		//Replace the worst individuals of the newPopulation for the elite of the original population 
		for(int i = 0; i < elite.size(); i++) {
			newPopulation.remove(newPopulation.size() - 1);
		}
		
		for(Representation c : elite) {
			newPopulation.add(c);
		}
		
		//The newPopulation is shuffled
		Collections.shuffle(newPopulation);
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
	
	private void onFirstGen() {
		for(GenAlgObserver o : observerList) {
			o.onFirstGen();
		}
	}
	
	/**
	 * Actions executed when a generation has been completed
	 * 
	 * @param generation actual generation
	 * @param population population of the generation
	 */
	private void onGenCompleted(int generation, ArrayList<Representation> population) {
		
		if(minimization) {
			if(fitnessFunction.getBestOfGen().getEvaluation() < absoluteBestEvaluation) {
				absoluteBestFitness = fitnessFunction.getBestOfGen().getFitness();
				absoluteBestEvaluation = fitnessFunction.getBestOfGen().getEvaluation();
			}
		}
		else {
			if(fitnessFunction.getBestOfGen().getFitness() > absoluteBestFitness) {
				absoluteBestFitness = fitnessFunction.getBestOfGen().getFitness();
				absoluteBestEvaluation = fitnessFunction.getBestOfGen().getEvaluation();
			}
		}
		
		
		
		double meanGeneration = 0;
		for(Representation c : population) {
			meanGeneration += c.getEvaluation();
		}
		meanGeneration /= population.size();
		
		for(GenAlgObserver o : observerList) {
			o.onGenCompleted(generation, absoluteBestEvaluation, fitnessFunction.getBestOfGen().getEvaluation(), meanGeneration);
		}
	}
	
	private void onAlgFinished(Representation c) {
		for(GenAlgObserver o : observerList) {
			o.onAlgFinished(c);
		}
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
	
	/**
	 * 
	 * @return an array list with the names of the types of the representations
	 */
	public ArrayList<String> getRepresentationTypes(){
		ArrayList<String> representationTypes = new ArrayList<String>();
		
		for(RepresentationType rt : RepresentationType.values()) {
			representationTypes.add(rt.toString());
		}
		
		return representationTypes;
	}
	
	/**
	 * @return minimization
	 */
	public boolean getMinimization() {
		return minimization;
	}

	/**
	 * @return fitness function
	 */
	public FitnessFunction getFitnessFunction () {
		return fitnessFunction;
	}
	
	public Symbols getSymbols() {
		return symbols;
	}
	
//**************************************************************************************
//Setters
	
	/**
	 * Set the representation type
	 * 
	 * @param representationType
	 */
	public void setRepresentationType (RepresentationType representationType) {
		this.representationType = representationType;
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
	 * Set the maximum height of the tree representation
	 * 
	 * @param maxHeight
	 */
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
	
	/**
	 * Set the minimum height of the tree representation
	 * 
	 * @param minHeight
	 */
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}
	
	/**
	 * Set the initilization type of the tree representation
	 * 
	 * @param iniType
	 */
	public void setIniType(InitializationType iniType) {
		this.iniType = iniType;
	}
	
	/**
	 * Set the symbols for the tree representation
	 * 
	 * @param symbols
	 */
	public void setSymbols(Symbols symbols) {
		this.symbols = symbols;
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
	 * Set the mutation probability
	 * 
	 * @param mutationProb
	 */
	public void setMutationProb(double mutationProb) {
		this.mutationProb = mutationProb;
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
	
	/**
	 * Set the elitism
	 * 
	 * @param elitism
	 */
	public void setElitism(double elitism) {
		this.elitism = elitism;
	}
}
