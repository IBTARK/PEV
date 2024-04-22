package model.fitnessFunctions;

import java.util.ArrayList;
import java.util.Random;

import model.evaluationFunctions.EvaluationFunction;
import model.representation.Representation;
import model.treeRep.trees.TreeChromosome;

public class FitnessFunction {
	private Random random;
	
	private Boolean minimization;
	private EvaluationFunction evaluationFunction;
	private Representation bestOfGen; //Element with best fitness of the generation
	
	private double avgSize; //Only used when the representation is a tree. Average nodes of the population
	
	public FitnessFunction(Boolean minimization, EvaluationFunction evaluationFunction) {
		random = new Random();
		
		this.minimization = minimization;
		this.evaluationFunction = evaluationFunction;
		
		avgSize = 0;
	}

	public void applyEvaluationFunction(ArrayList<Representation> population) {
		double minEvaluation = Double.MAX_VALUE, maxEvaluation = Double.MIN_VALUE, evaluation, totalEvaluation = 0;
		double totalFitness;
		double lastScoreAccumulated = 0;
		
		for(int i = 0; i < population.size(); i++) {
			//If possible compute the average size of the trees
			if(population.get(0) instanceof TreeChromosome) {
				avgSize += (((TreeChromosome) population.get(0)).getRoot().getDescendants().size() + 1);
			}
			
			//Compute the evaluation
			evaluation = population.get(i).computeEvaluation(evaluationFunction);
			//If necessary update the maximum and minimum evaluation
			if(evaluation > maxEvaluation) {
				maxEvaluation = evaluation;
				bestOfGen = population.get(i);
			}
			if(evaluation < minEvaluation)
				minEvaluation = evaluation;
			//Add the evaluation of the chromosome to the total evaluation
			totalEvaluation += evaluation;
		}
		
		//If possible compute the average size of the trees
		if(population.get(0) instanceof TreeChromosome) {
			avgSize = avgSize / population.size();
		}
		
		totalEvaluation = tarpeian(population, totalEvaluation);
		
		if(minimization)
			totalFitness = displaceToMinimize(population, maxEvaluation);
		else if(minEvaluation < 0)
			totalFitness = displaceToMaximize(population, minEvaluation);
		else
			totalFitness = totalEvaluation;
		
		for(int i = 0; i < population.size(); i++) {
			population.get(i).computeScoreAndAccumulated(totalFitness, lastScoreAccumulated);
			lastScoreAccumulated = population.get(i).getScoreAccumulated();
		}
	}
	
	/**
	 * Displace the fitness to ensure a correct minimization. Ensures that all the fitnesses are positive.
	 * 
	 * @param population already evaluated
	 * @param maxEvaluation maximum evaluation of all the chromosomes
	 * @return the total fitness
	 */
	private Double displaceToMinimize(ArrayList<Representation> population, Double maxEvaluation) {
		Double totalFitness = 0.0, fitness, maxFitness = Double.MIN_VALUE;
		
		for(Representation c : population) {
			fitness = (1.05 * maxEvaluation) - c.getEvaluation();
			//If necessary update the best chromosome
			if(fitness > maxFitness) {
				maxFitness = fitness;
				bestOfGen = c;
			}
			c.setFitness(fitness);
			totalFitness += fitness;
		}
		
		return totalFitness;
	}
	
	/**
	 * Displace the fitness to ensure a correct maximization. Ensures that all the fitnesses are positive.
	 * 
	 * @param population already evaluated
	 * @param minEvaluation minimum evaluation of all the chromosomes
	 * @return the total fitness
	 */
	private Double displaceToMaximize(ArrayList<Representation> population, Double minEvaluation) {
		Double totalFitness = 0.0, fitness, maxFitness = Double.MIN_VALUE;
		
		for(Representation c : population) {
			fitness = (1.05 * Math.abs(minEvaluation)) + c.getEvaluation();
			//If necessary update the best chromosome
			if(fitness > maxFitness) {
				maxFitness = fitness;
				bestOfGen = c;
			}
			c.setFitness(fitness);
			totalFitness += fitness;
		}
		
		return totalFitness;
	}
	
	/**
	 * Tarpeian method to control the bloating.
	 * 
	 * Only used when the representation is in the form of a tree
	 * 
	 * @param population
	 */
	private double tarpeian(ArrayList<Representation> population, double totalEvaluation) {
		double newTotalEvaluation = totalEvaluation, bestEvaluation = Double.MIN_VALUE;
		
		if(population.get(0) instanceof TreeChromosome) {
			for(Representation r : population) {
				TreeChromosome t = (TreeChromosome) r;
				if(t.getRoot().getDescendants().size() + 1 > avgSize && random.nextInt() % 2 == 0) {
					newTotalEvaluation -= t.getEvaluation();
					t.setEvaluation(0.0);
					t.setFitness(0.0);
				}
				if(t.getEvaluation() > bestEvaluation) {
					bestEvaluation = t.getEvaluation();
					bestOfGen = t;
				}
			}
		}
		
		return newTotalEvaluation;
	}
	
	/**
	 * 
	 * @return the best chromosome of the population
	 */
	public Representation getBestOfGen() {
		return bestOfGen;
	}
}
