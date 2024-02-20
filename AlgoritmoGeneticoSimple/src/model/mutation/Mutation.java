package model.mutation;

import model.chromosomes.Chromosome;

public abstract class Mutation {
	
	protected Double probMutate;
	
	public Mutation(Double probMutate) {
		this.probMutate = probMutate;
	}
	
	public abstract void mutate(Chromosome c);
}
