package model.selection;

import java.util.ArrayList;
import java.util.List;

import model.chromosomes.Chromosome;

/**
 * This class implements the Montecarlo selection
 */
public class RemainSelection extends Selection{
	
	private int k;
	
	public RemainSelection(Double elitism, int k) {
		super(elitism);
		this.k = k;
	}

	@Override
	/**
	 * Given a population returns another population that has been selected following the Remains selection
	 */
	protected ArrayList<Chromosome> selection(List<Chromosome> population) {
		
		ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
		
		//Adds p_i * k clones of one chromosome to the newPopulation
		for(int i = 0; i < population.size(); i++) {
			for(int j = 0; j < (int) (population.get(i).getScore() * k); j++) {
				newPopulation.add(population.get(i).clone());
			}
		}
		
		//Add the remaining chromosomes to the newPopulation using Montecarlo selection
		if(newPopulation.size() < population.size()) {
			ArrayList<Chromosome> newPopulation2 = new ArrayList<Chromosome>();
			
			newPopulation2 = (new MontecarloSelection(elitism)).select(population);
			
			newPopulation.addAll(newPopulation2.subList(0, population.size() - newPopulation.size()));
		}
		
		return newPopulation;
	}

}
