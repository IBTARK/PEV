package control;

import java.util.ArrayList;
import java.util.List;

import model.GenAlgObserver;
import model.GeneticAlgorithm;
import model.crossover.Crossover;
import model.evaluationFunctions.EvaluationFunction;
import model.fenotypes.FenotypeFunction;
import model.fitnessFunctions.FitnessFunction;
import model.mutation.Mutation;
import model.representation.RepresentationType;
import model.selection.Selection;
import model.treeRep.symbols.Symbols;
import model.treeRep.trees.InitializationType;

public class Controller {
	private GeneticAlgorithm genAlg;
	
	public Controller(GeneticAlgorithm genAlg) {
		this.genAlg = genAlg;
	}
	
	/**
	 * Add an observer
	 * 
	 * @param o observer
	 */
	public void addObserver(GenAlgObserver o) {
		genAlg.addObserver(o);
	}
	
	/**
	 * Remove an observer
	 * 
	 * @param o observer
	 */
	public void removeObserver(GenAlgObserver o) {
		genAlg.removeObserver(o);
	}
	
	/**
	 * Executes the genetic algorithm
	 */
	public void execute() {
		genAlg.execute();
	}
	
//************************************************************************************
//Getters
	/**
	 * @return an array list with the names of the types of representations
	 */
	public ArrayList<String> getRepresentationTypes(){
		return genAlg.getRepresentationTypes();
	}
	
	/**
	 * @return an array list with the names of the types of selection
	 */
	public ArrayList<String> getSelectionTypes(){
		return genAlg.getSelectionTypes();
	}
	
	/**
	 * @return an array list with the names of the types of crossover
	 */
	public ArrayList<String> getCrossoverTypes(){
		return genAlg.getCrossoverTypes();
	}
	
	/**
	 * @return an array list with the names of the types of mutation
	 */
	public ArrayList<String> getMutationTypes(){
		return genAlg.getMutationTypes();
	}
	
	/**
	 * @return an array list with the names of the initialization types of the tree choromosomes
	 */
	public ArrayList<String> getInitializationTypes(){
		return genAlg.getInitializationTypes();
	}
	
	/**
	 * @return an array list with the names of the types of fenotypes
	 */
	public ArrayList<String> getFenotypeTypes(){
		return genAlg.getFenotypeTypes();
	}
	
	/**
	 * @return an array list with the names of the evaluation functions 
	 */
	public ArrayList<String> getEvaluationFunctionTypes(){
		return genAlg.getEvaluationFunctionTypes();
	}
	
	/**
	 * @return minimization
	 */
	public boolean getMinimization() {
		return genAlg.getMinimization();
	}
	
	public FitnessFunction getFitnessFunction(){
		return genAlg.getFitnessFunction();
	}

//************************************************************************************
//Setters	
	/**
	 * Set the representation type
	 * 
	 * @param representationType
	 */
	public void setRepresentationType (RepresentationType representationType) {
		genAlg.setRepresentationType(representationType);
	}
	
	/**
	 * Set the number of genes
	 * 
	 * @param numGenes
	 */
	public void setNumGenes (int numGenes) {
		genAlg.setNumGenes(numGenes);
	}
	
	/**
	 * Set the genes lengths
	 * 
	 * @param genesLengths
	 */
	public void setGenesLengths (ArrayList<Integer> genesLengths) {
		genAlg.setGenesLengths(genesLengths);
	}
	
	/**
	 * Set the population size
	 * 
	 * @param populationSize
	 */
	public void setPopulationSize(int populationSize) {
		genAlg.setPopulationSize(populationSize);
	}
	
	/**
	 * set the number of generations
	 * 
	 * @param generations
	 */
	public void setNumGenerations(int generations) {
		genAlg.setNumGenerations(generations);
	}
	
	/**
	 * Set elitism
	 * 
	 * @param elitism [0.0, 1.0]
	 */
	public void setElitism(double elitism) {
		genAlg.setElitism(elitism);
	}
	
	/**
	 * Set the crossover probability
	 * 
	 * @param crossoverPctg
	 */
	public void setCrossoverPctg(double crossoverPctg) {
		genAlg.setCrossoverPctg(crossoverPctg);
	}
	
	/**
	 * Set the mutation probability
	 * 
	 * @param crossoverPctg
	 */
	public void setMutationProb(double mutationProb) {
		genAlg.setMutationProb(mutationProb);
	}
	
	/**
	 * Set the selection type
	 * 
	 * @param selection
	 */
	public void setSelection(Selection selection) {
		genAlg.setSelection(selection);
	}
	
	/**
	 * Set the crossover
	 * 
	 * @param crossover
	 */
	public void setCrossover(Crossover crossover) {
		genAlg.setCrossover(crossover);
	}
	
	/**
	 * Set the mutation
	 * 
	 * @param mutation
	 */
	public void setMutation(Mutation mutation) {
		genAlg.setMutation(mutation);
	}
	
	/**
	 * Set the initialization type for a tree chromosome
	 * 
	 * @param iniType
	 */
	public void setIniType(InitializationType iniType) {
		genAlg.setIniType(iniType);
	}
	
	/**
	 * Set the maximum height of a tree when its initialized
	 * 
	 * @param maxHeight
	 */
	public void setMaxHeight(int maxHeight) {
		genAlg.setMaxHeight(maxHeight);
	}
	
	/**
	 * Set the minimum height of a tree when its initialized
	 * 
	 * @param minHeight
	 */
	public void setMinHeight(int minHeight) {
		genAlg.setMinHeight(minHeight);
	}
	
	/**
	 * Set the symbols for the tree representation
	 * 
	 * @param symbols
	 */
	public void setSymbols(Symbols symbols) {
		genAlg.setSymbols(symbols);
	}
	
	/**
	 * Set the fenotype function
	 */
	public void setFenotypeFunction(FenotypeFunction fenotypeFunction) {
		genAlg.setFenotypeFunction(fenotypeFunction);
	}
	
	
	/**
	 * Set the evaluation function and update the fitness function
	 * 
	 * @param evaluationFunction
	 */
	public void setEvaluationFunction(EvaluationFunction evaluationFunction) {
		genAlg.setEvaluationFunction(evaluationFunction);
	}
	
	/**
	 * Set the minimization variable and update the fitness function
	 * 
	 * @param minimization
	 */
	public void setMinimization(boolean minimization) {
		genAlg.setMinimization(minimization);
	}
	
	/**
	 * Set the minimization variable and update the fitness function
	 * 
	 * @param minimization
	 */
	public void setBloating(boolean bloating) {
		genAlg.setBloating(bloating);
	}
}
