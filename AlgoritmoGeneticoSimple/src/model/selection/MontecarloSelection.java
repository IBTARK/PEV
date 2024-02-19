package model.selection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.chromosomes.Chromosome;

public class MontecarloSelection implements Selection{
	
	private Random random;
	
	public MontecarloSelection() {
		random = new Random();
	}

	@Override
	public List<Chromosome> select(List<Chromosome> population) {
		List<Chromosome> newPopulation = new ArrayList<Chromosome>();
		
		for(int i = 0; i < population.size(); i++) {
			double rnd = random.nextDouble();
			int elem = 0;
			
			while(rnd > population.get(elem).getScoreAccumulated() && elem < population.size()) {
				elem++;
			}
			
			newPopulation.add(population.get(elem));
		}
		
		return null;
	}

}
