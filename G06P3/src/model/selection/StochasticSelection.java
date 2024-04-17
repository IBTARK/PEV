package model.selection;

import java.util.ArrayList;
import java.util.Random;

import model.representation.Representation;

public class StochasticSelection implements Selection{

	private Random random;
	
	public StochasticSelection(){
		random = new Random();		
	}
	
	@Override
	public ArrayList<Representation> select(ArrayList<Representation> population) {
		ArrayList<Representation> newPopulation = new ArrayList<Representation>();
		double a = random.nextDouble();
		int elem = 0;
		
		for(int i = 0; i < population.size(); i++) {
			double score = (a + i - 1) / population.size();
			
			
			while(elem < population.size() && score > population.get(elem).getScoreAccumulated()) {
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
