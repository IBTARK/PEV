package model.listRep.mutation;

import model.listRep.chromosomes.Chromosome;

public class IJMutation implements Mutation{

	public IJMutation() {
	}

	@Override
	public void mutate(Chromosome c) {
		
		//invertir la lista
		int start = 0;
		int end = c.getChromosomeLength()-1;
		
		while(start < end) {
			Object allele = c.getAllele(start);
			c.setAllele(start, c.getAllele(end));
			c.setAllele(end, allele);
			start++;
			end--;
		}
	}
}
