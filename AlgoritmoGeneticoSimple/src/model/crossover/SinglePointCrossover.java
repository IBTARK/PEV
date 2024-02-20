package model.crossover;

import java.util.ArrayList;
import java.util.Random;

import model.chromosomes.Chromosome;

/**
 * Class to implement the single point crossover
 *
 */
public class SinglePointCrossover implements Crossover{
	
	private Random r;
	
	public SinglePointCrossover() {
		r = new Random();
	}

	@Override
	/**
	 * Single point crossover (the original chromosomes are modified)
	 */
	public void cross(Chromosome c1, Chromosome c2) {
		
		int pos = r.nextInt(0, c1.getChromosomeLength());
		
		//Select the alleles that will be replaced
		ArrayList<Object> replacement1 = c1.getAlleles(pos, c1.getChromosomeLength() - 1);
		//Replace the same subsection of the chromosome
		ArrayList<Object> replacement2 = c2.replaceAlleles(replacement1, pos, c1.getChromosomeLength() - 1);
		c1.replaceAlleles(replacement2, pos, c1.getChromosomeLength() - 1);
	}

}
