package model.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.chromosomes.BinaryChromosome;
import model.chromosomes.Chromosome;

/**
 * This class implements the Truncation selection
 */
public class TruncationSelection implements Selection{
	
	private Double trunc;
	
	public TruncationSelection(Double trunc) {
		this.trunc = trunc;
	}

	@Override
	/**
	 * Given a population returns another population that has been selected following the Truncation selection
	 */
	public ArrayList<Chromosome> select(List<Chromosome> population) {
		ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
		
		//Order the population in descending order
		Collections.sort(population, Collections.reverseOrder());
		
		int numCopies = (int) (1 / trunc);
		
		while(newPopulation.size() < population.size()) {
			for(int j = 0; j < population.size() && newPopulation.size() < population.size(); j++) {
				for(int i = 0; i < numCopies && newPopulation.size() < population.size(); i++) {
					//TODO add an if for each class that extends Chromosome
					if(population.get(j).getClass() == BinaryChromosome.class) {
						//A clone of the original chromosome is added to the new population
						newPopulation.add(new BinaryChromosome((BinaryChromosome) population.get(j)));
					}
				}
			}
		}
		
		return newPopulation;
	}

}
