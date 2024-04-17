package model.listRep.mutation;

import model.listRep.chromosomes.Chromosome;
import model.mutation.Mutation;
import model.representation.Representation;

public class IJMutation implements Mutation{

	public IJMutation() {
	}

	@Override
	public void mutate(Representation co) {
		Chromosome c = (Chromosome) co;
		
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
