package model.listRep.mutation;

import java.util.ArrayList;
import java.util.Random;

import model.Mutation;
import model.listRep.chromosomes.Chromosome;
import model.representation.Representation;

public class InsertionMutation implements Mutation{

	private Random random;
	
	private int numIns; //number of insertions
	
	public InsertionMutation(int numIns) {
		random = new Random();
		this.numIns = numIns;
	}
	
	private void checkNumInsValid(Chromosome c) {
		if(numIns > c.getChromosomeLength() || numIns < 0) {
			numIns = random.nextInt(1, c.getChromosomeLength() / 2);
		}
	}

	@Override
	public void mutate(Representation co) {
		Chromosome c = (Chromosome) co;
		
		checkNumInsValid(c);
			
		ArrayList<Integer> allelesToInsert = new ArrayList<Integer>();
		int pos;
		
		//Select randomly the index of the alleles to insert, ensuring that there are no repeated alleles
		for(int i = 0; i < numIns; i++) {
			pos = random.nextInt(c.getChromosomeLength());
			while(allelesToInsert.contains(pos)) {
				pos = random.nextInt(c.getChromosomeLength());
			}
			allelesToInsert.add(pos);
		}
		//TODO sort the alleles
		allelesToInsert.sort(null);
		
		//TODO delete
		//allelesToInsert.set(0, 3);
		//allelesToInsert.set(1, 7);
		
		ArrayList<Integer> insertionPositions = new ArrayList<Integer>();
		
		//Select randomly the positions ensuring that there are no repeated positions
		pos = random.nextInt(c.getChromosomeLength());
		while(allelesToInsert.get(0) - pos < 0) {
			pos = random.nextInt(c.getChromosomeLength());
		}
		for(int i = 0; i < numIns; i++) {
			insertionPositions.add(allelesToInsert.get(i) - pos);
		}
		
		//TODO delete
		//insertionPositions.set(0, 1);
		//insertionPositions.set(1, 5);
		
		for (int j = 0; j < numIns; j++) {
			//get the allele in position
			Object lastAllele = c.getAllele(allelesToInsert.get(j));
			Object allele = null;
			
			//move alleles
			for (int i = insertionPositions.get(j); i <= allelesToInsert.get(j); i++) {
				//get the value of the allele in insert position
				allele = c.getAllele(i);
				//insert the new allele
				c.setAllele(i, lastAllele);
				lastAllele = allele;
			}
		}
	}
	
}
