package model.crossover;

import java.util.Random;

import model.chromosomes.Chromosome;

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
	public void cross(Chromosome c1, Chromosome c2) {
		
		for(int i = 0; i < c1.getChromosomeLength(); i++) {
			if(r.nextDouble() <= prob) {
				Object allele = c1.getAllele(i);
				c1.setAllele(i, c2.getAllele(i));
				c2.setAllele(i, allele);
			}
		}
	}

}
