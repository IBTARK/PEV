package model.listRep.selection;

import java.util.ArrayList;
import java.util.Collections;

import model.listRep.chromosomes.Chromosome;

public class RankingSelection implements Selection{

	private double beta;
	
	public RankingSelection(double beta) {
		if(beta < 1 || beta > 2) {
			this.beta = 2;
		}
		else {
			this.beta = beta;
		}
	}

	@Override
	/**
	 * Given a population returns another population that has been selected following the Montecarlo selection
	 */
	public ArrayList<Chromosome> select(ArrayList<Chromosome> population) {
		//The population is sorted in descending order
		Collections.sort(population, Collections.reverseOrder());
		
		double accScore = 0, prob;
		
		for(int i = 0; i < population.size(); i++) {
			population.get(i).setScoreAccumulated(accScore);
			prob = (double)i/population.size();
			prob *= 2*(beta-1);
			prob = beta - prob;
			prob = (double)prob * ((double)1/population.size());
			population.get(i).setScore(prob);
			accScore += prob;
		}
		
		return new MontecarloSelection().select(population);
	}

}
