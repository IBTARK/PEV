package model.mutation;

import java.util.Random;

import model.chromosomes.BinaryChromosome;
import model.chromosomes.Chromosome;
import model.chromosomes.RealChromosome;

public class GenericMutation extends Mutation{
	
	Random random;
	
	public GenericMutation(Double probMutate) {
		super(probMutate);
		
		random = new Random();
	}

	@Override
	public void mutate(Chromosome c) {
		
		for(int i = 0; i < c.getChromosomeLength(); i++) {
			if(random.nextDouble() < probMutate) {
				
				//TODO add an if for each class that extends Chromosome
				if(c.getClass() == BinaryChromosome.class) {
					c.setAllele(i, ((boolean) c.getAllele(i)) ? false : true );
				}
				else if(c.getClass() == RealChromosome.class) {
					c.setAllele(i, random.nextDouble(c.getGene(i).getFenotypeFunction().getMinValue(), c.getGene(i).getFenotypeFunction().getMaxValue()));
				}
			}
		}
	}
}
