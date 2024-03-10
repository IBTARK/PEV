package model.mutation;

import java.util.ArrayList;
import java.util.Random;

import model.chromosomes.Chromosome;

public class HeuristicMutation implements Mutation{

	private Random random;
	
	private int numPos; //number of positions
	
	public HeuristicMutation(int numPos) {
		random = new Random();
		this.numPos = numPos;
	}
	
	private void checkNumInsValid(Chromosome c) {
		if(numPos > c.getChromosomeLength() || numPos < 0) {
			numPos = random.nextInt(1, c.getChromosomeLength() / 2);
		}
	}

	@Override
	public void mutate(Chromosome c) {
		checkNumInsValid(c);
			
		ArrayList<Integer> allelesToInsert = new ArrayList<Integer>();
		int pos;
		
		//Select randomly the index of the alleles to change, ensuring that there are no repeated alleles
		for(int i = 0; i < numPos; i++) {
			pos = random.nextInt(c.getChromosomeLength());
			while(allelesToInsert.contains(pos)) {
				pos = random.nextInt(c.getChromosomeLength());
			}
			allelesToInsert.add(pos);
		}
		//TODO sort the alleles
		allelesToInsert.sort(null);
		
		//create the permutations
		ArrayList<Integer> permutation = new ArrayList<Integer>();
		ArrayList<Chromosome> individuals = new ArrayList<Chromosome>();
		//TODO here, calculate permutation
		Chromosome newC = c.clone();
		for(int i = 0; i < c.getChromosomeLength(); i++) {
			newC.setAllele(i, permutation.get(i));
		}
		individuals.add(newC);
		
		//evaluate all individuals and save the best individual
		int bestInd = 0;
		double bestScore = individuals.get(0).getScore(); //TODO the score might not be the correct one. Calculate the score again
		for(int i = 1; i < individuals.size(); i++) {
			if(individuals.get(i).getScore() > bestScore) {
				bestScore = individuals.get(i).getScore();
				bestInd = i;
			}
		}

		c = individuals.get(bestInd);
	}
	
}
