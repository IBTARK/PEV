package control;

import java.util.ArrayList;

import model.GenAlgObserver;
import model.GeneticAlgorithm;
import model.crossover.Crossover;
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
	 * @return an array list with the names of the problem types 
	 */
	public ArrayList<String> getProblemTypes(){
		return genAlg.getProblemTypes();
	}

//************************************************************************************
//Setters	
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
	 * Set the problem
	 * 
	 * @param mutation
	 */
	public void setProblem(int numFlightts) {
		genAlg.setNumFlights(numFlightts);
	}
}
