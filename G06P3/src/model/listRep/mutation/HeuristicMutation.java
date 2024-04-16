package model.listRep.mutation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.Mutation;
import model.Representation;
import model.fitnessFunctions.FitnessFunction;
import model.listRep.chromosomes.Chromosome;

public class HeuristicMutation implements Mutation{

	private Random random;
	
	private int numPos; //number of positions
	
	private FitnessFunction fitnessFunction; //fitness function to evaluate the new population
	
	public HeuristicMutation(int numPos, FitnessFunction fitnessFunction) {
		random = new Random();
		this.numPos = numPos;
		this.fitnessFunction = fitnessFunction;
	}
	
	private void checkNumInsValid(Chromosome c) {
		if(numPos > c.getChromosomeLength() || numPos < 0) {
			numPos = random.nextInt(1, c.getChromosomeLength() / 2);
		}
	}

	@Override
	public void mutate(Representation co) {
		Chromosome c = (Chromosome) co;
		
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
		//sort the alleles
		Collections.sort(allelesToInsert); //TODO revisar
		
		//save the alleles to exchange
		ArrayList<Object> allelesInsert = new ArrayList<Object>();
		for(int i = 0; i < numPos; i++) {
			allelesInsert.add(i, c.getAllele(allelesToInsert.get(i)));
		}
		
		//create the permutations
		ArrayList<Chromosome> population = new ArrayList<Chromosome>();
		//calculate permutation
		ArrayList<ArrayList<Object>> permutations = new ArrayList<>();
        generatePermutations(allelesInsert, numPos, permutations);
		
		Chromosome newC = c.clone();
		for(int j = 0; j < permutations.size(); j++) {
			for(int i = 0; i < numPos; i++) {
				newC.setAllele(allelesToInsert.get(i), permutations.get(j).get(i));
			}
			population.add(newC);
		}
		
		//evaluate all individuals and save the best individual
		fitnessFunction.applyEvaluationFunction(population); //TODO revisar
		int bestInd = 0;
		double bestScore = population.get(0).getScore();
		for(int i = 1; i < population.size(); i++) {
			if(population.get(i).getScore() > bestScore) {
				bestScore = population.get(i).getScore();
				bestInd = i;
			}
		}

		c = population.get(bestInd);
	}

    private void generatePermutations(ArrayList<Object> elements, int n, ArrayList<ArrayList<Object>> result) {
        if (n == 1) {
            result.add(new ArrayList<Object>(elements));
        } else {
            for (int i = 0; i < n; i++) {
                //exchange the elements
                Object temp = elements.get(i);
                elements.set(i, elements.get(n-1));
                elements.set(n-1, temp);
                //recursive
                generatePermutations(elements, n - 1, result);
                //exchange the elements
                temp = elements.get(i);
                elements.set(i, elements.get(n-1));
                elements.set(n-1, temp);
            }
        }
    }
	
}
