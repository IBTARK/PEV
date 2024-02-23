package model.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.chromosomes.Chromosome;

public abstract class Selection {
	
	protected Double elitism;
	
	public Selection(Double elitism) {
		if(elitism >= 0 && elitism <= 1)
			this.elitism = elitism;
		else 
			this.elitism = 0.03;
	}
	
	protected abstract ArrayList<Chromosome> selection(List<Chromosome> population);
	
	public ArrayList<Chromosome> select(List<Chromosome> population){
		ArrayList<Chromosome> newPopulation = selection(population);
		
		if(elitism > 0)
			introduceElitism(population, newPopulation);
		
		return newPopulation;
	}
	
	protected void introduceElitism(List<Chromosome> population, List<Chromosome> newPopulation) {
		int elite = (int) (population.size() * elitism);
		
		//Order the original population in descending order
		Collections.sort(population, Collections.reverseOrder());
		//Order the new population in descending order
		Collections.sort(newPopulation, Collections.reverseOrder());
		
		//Replace the worst individuals of the newPopulation for the elite of the original population 
		for(int i = 0; i < elite; i++) {
			newPopulation.remove(newPopulation.size() - 1);
		}
		newPopulation.addAll(population.subList(0, elite - 1));
		
		//The newPopulation is shuffled
		Collections.shuffle(newPopulation);
	}

}
