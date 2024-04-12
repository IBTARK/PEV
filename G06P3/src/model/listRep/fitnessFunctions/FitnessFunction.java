package model.listRep.fitnessFunctions;

import java.util.ArrayList;

import model.listRep.chromosomes.Chromosome;
import model.listRep.evaluationFunctions.EvaluationFunction;

public class FitnessFunction {
	private Boolean minimization;
	private EvaluationFunction evaluationFunction;
	private Chromosome bestOfGen; //Chromosome with best fitness of the generation
	
	public FitnessFunction(Boolean minimization, EvaluationFunction evaluationFunction) {
		this.minimization = minimization;
		this.evaluationFunction = evaluationFunction;
	}

	public void applyEvaluationFunction(ArrayList<Chromosome> population) {
		double minEvaluation = Double.MAX_VALUE, maxEvaluation = Double.MIN_VALUE, evaluation, totalEvaluation = 0;
		double totalFitness;
		double lastScoreAccumulated = 0;
		
		for(int i = 0; i < population.size(); i++) {
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
	private Double displaceToMinimize(ArrayList<Chromosome> population, Double maxEvaluation) {
		Double totalFitness = 0.0, fitness, maxFitness = Double.MIN_VALUE;
		
		for(Chromosome c : population) {
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
	private Double displaceToMaximize(ArrayList<Chromosome> population, Double minEvaluation) {
		Double totalFitness = 0.0, fitness, maxFitness = Double.MIN_VALUE;
		
		for(Chromosome c : population) {
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
	 * 
	 * @return the best chromosome of the population
	 */
	public Chromosome getBestOfGen() {
		return bestOfGen;
	}
}
