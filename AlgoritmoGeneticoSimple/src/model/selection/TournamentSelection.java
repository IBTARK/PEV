package model.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.chromosomes.BinaryChromosome;
import model.chromosomes.Chromosome;

/**
 * This class implements the Tournament selection
 */
public class TournamentSelection extends Selection{
	
	private Random random;
	private int k; //Number of chromosomes in the tournament
	private Boolean probabilistic; //True if the tournament is probabilistic
	private Double prob; //Necessary for the probabilistic tournament
	
	
	public TournamentSelection(int k) {
		random = new Random();
		this.probabilistic = false;
		this.k = k;
		prob = 0.5; //Default value
	}
	
	public TournamentSelection(Boolean probabilistic, int k) {
		random = new Random();
		this.probabilistic = probabilistic;
		this.k = k;
		prob = 0.5; //Default value
	}
	
	public void setProbabilistic(Boolean probabilistic) {
		this.probabilistic = probabilistic;
	}
	
	/**
	 * Only necessary if the tournament is probabilistic
	 * 
	 * @param prob value between 0 and 1
	 */
	public void setProbability(Double prob) {
		if(prob >= 0 && prob <= 1)
			this.prob = prob;
	}
	
	@Override
	/**
	 * Given a population returns another population that has been selected following the Tournament selection
	 */
	public ArrayList<Chromosome> select(List<Chromosome> population) {
		ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
		ArrayList<Chromosome> tournament = new ArrayList<Chromosome>();
		
		for(int i = 0; i < population.size(); i++) {
			
			//Select randomly k elements from the original population
			for(int j = 0; j < k; j++) {
				tournament.add(population.get(random.nextInt(population.size())));
			}
			
			//Order the chromosomes in ascending order 
			Collections.sort(tournament);
			
			int elem;
			//If the tournament is probabilistic and the probability indicates so the worst chromosome is chosen
			if(probabilistic && random.nextDouble() < prob)
				elem = 0;
			else //If the tournament is not probabilistic or the probability indicates so the best chromosome is chosen
				elem = k - 1;
			
			//A clone of the original chromosome is added to the new population
			newPopulation.add(tournament.get(elem).clone());
			
			tournament.clear();
		}
		
		return newPopulation;
	}

}
