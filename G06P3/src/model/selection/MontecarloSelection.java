package model.selection;

import java.util.ArrayList;
import java.util.Random;

import model.representation.Representation;

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
	public ArrayList<Representation> select(ArrayList<Representation> population) {
		ArrayList<Representation> newPopulation = new ArrayList<Representation>();
		
		for(int i = 0; i < population.size(); i++) {
			double rnd = random.nextDouble();
			int elem = 0;
			
			while(elem < population.size() && rnd > population.get(elem).getScoreAccumulated()) {
				elem++;
			}
			
			if(elem == population.size())
				elem --;
			//A clone of the original chromosome is added to the new population
			newPopulation.add(population.get(elem).clone());
		}
		
		return newPopulation;
	}

}
