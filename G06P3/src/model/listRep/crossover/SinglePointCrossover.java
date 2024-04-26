package model.listRep.crossover;

import java.util.ArrayList;
import java.util.Random;

import model.crossover.Crossover;
import model.listRep.chromosomes.Chromosome;
import model.representation.Representation;

/**
 * Class to implement the single point crossover
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
	public void cross(Representation r1, Representation r2) {
		Chromosome c1 = (Chromosome)r1;
		Chromosome c2 = (Chromosome)r2;
		int pos = r.nextInt(1, c1.getChromosomeLength());
		
		//Select the alleles that will be replaced
		ArrayList<Object> replacement1 = c1.getAlleles(pos, c1.getChromosomeLength() - 1);
		//Replace the same subsection of the chromosome
		ArrayList<Object> replacement2 = c2.replaceAlleles(replacement1, pos, c1.getChromosomeLength() - 1);
		c1.replaceAlleles(replacement2, pos, c1.getChromosomeLength() - 1);
	}

}
