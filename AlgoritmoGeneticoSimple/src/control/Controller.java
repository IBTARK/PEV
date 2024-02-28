package control;

import java.util.ArrayList;
import java.util.List;

import model.GenAlgObserver;
import model.GeneticAlgorithm;
import model.chromosomes.ChromosomeType;
import model.crossover.Crossover;
import model.evaluationFunctions.EvaluationFunction;
import model.fenotypes.FenotypeFunction;
import model.fitnessFunctions.FitnessFunction;
import model.mutation.Mutation;
import model.selection.Selection;

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

//************************************************************************************
//Setters
	/**
	 * Set the chromosome type
	 * 
	 * @param chromosomeType
	 */
	public void setChromosomeType (ChromosomeType chromosomeType) {
		genAlg.setChromosomeType(chromosomeType);
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
	 * Set the genes fenotypes functions
	 * 
	 * @param genesFenotypesFunctions
	 */
	public void setGenesFenotypesFunctions (List<FenotypeFunction> genesFenotypesFunctions) {
		genAlg.setGenesFenotypesFunctions(genesFenotypesFunctions);
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
}
