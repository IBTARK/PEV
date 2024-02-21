package model.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.chromosomes.BinaryChromosome;
import model.chromosomes.Chromosome;

/**
 * This class implements the Montecarlo selection
 */
public class MontecarloSelection implements Selection{
	
	private Random random;
	
	public MontecarloSelection() {
		random = new Random();
	}

	@Override
	/**
	 * Given a population returns another population that has been selected following the Montecarlo selection
	 */
	public ArrayList<Chromosome> select(List<Chromosome> population) {
		ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
		
		for(int i = 0; i < population.size(); i++) {
			double rnd = random.nextDouble();
			int elem = 0;
			
			while(rnd > population.get(elem).getScoreAccumulated() && elem < population.size()) {
				elem++;
			}
			
			//A clone of the original chromosome is added to the new population
			newPopulation.add(population.get(elem).clone());
		}
		
		return newPopulation;
	}

}
