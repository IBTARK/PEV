package model.mutation;

import java.util.Random;

import model.chromosomes.Chromosome;

public class ExchangeMutation implements Mutation{

	private Random random;
	
	public ExchangeMutation() {
		random = new Random();
	}

	@Override
	public void mutate(Chromosome c) {
		//choose 2 points
		int p1 = random.nextInt(0, c.getChromosomeLength()), p2 = random.nextInt(0, c.getChromosomeLength());
		
		//The points have to be different
		while(p2 == p1) {
			p2 = random.nextInt(0, c.getChromosomeLength());
		}
		
		//TODO delete
		p1 = 2;
		p2 = 6;
		
		//exchange the alleles at those points
		Object allele1 = c.getAllele(p1), allele2 = c.getAllele(p2);
		c.setAllele(p1, allele2);
		c.setAllele(p2, allele1);
	}
}
