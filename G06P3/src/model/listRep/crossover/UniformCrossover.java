package model.listRep.crossover;

import java.util.Random;

import model.crossover.Crossover;
import model.listRep.chromosomes.Chromosome;
import model.representation.Representation;

/**
 * Class to implement the uniform crossover
 */
public class UniformCrossover implements Crossover{
	
	private Random r;
	private Double prob;
	
	public UniformCrossover(Double prob) {
		this.prob = prob;
		r = new Random();
	}

	@Override
	/**
	 * Uniform crossover (the original chromosomes are modified)
	 */
	public void cross(Representation r1, Representation r2) {
		Chromosome c1 = (Chromosome)r1;
		Chromosome c2 = (Chromosome)r2;
		
		for(int i = 0; i < c1.getChromosomeLength(); i++) {
			if(r.nextDouble() <= prob) {
				Object allele = c1.getAllele(i);
				c1.setAllele(i, c2.getAllele(i));
				c2.setAllele(i, allele);
			}
		}
	}

}
