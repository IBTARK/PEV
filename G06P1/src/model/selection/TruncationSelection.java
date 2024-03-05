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
		
		int numCopies = (int) (1 / trunc), i = 0, j = 0;
		
		while(i < population.size() && newPopulation.size() < population.size()) {
			while(j < numCopies && newPopulation.size() < population.size()) {
				//A clone of the original chromosome is added to the new population
				newPopulation.add(population.get(i).clone());
				
				j++;
			}
			j = 0;
			i++;
		}
		
		System.out.println(newPopulation.size());
		
		return newPopulation;
	}

}