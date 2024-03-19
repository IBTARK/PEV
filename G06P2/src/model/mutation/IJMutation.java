package model.mutation;

import model.chromosomes.Chromosome;

public class IJMutation implements Mutation{

	public IJMutation() {
	}

	@Override
	public void mutate(Chromosome c) {
		//add 1 to all alleles
		for(int i = 0; i < c.getChromosomeLength(); i++) {
			Object allele = c.getAllele(i);
			Double al = (Double)allele + 1;
			if(al == c.getChromosomeLength()+1) {
				al = 1.0;
			}
			c.setAllele(i, (Object)al);
		}
	}
}
