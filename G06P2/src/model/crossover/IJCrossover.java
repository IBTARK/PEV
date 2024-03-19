package model.crossover;

import java.util.ArrayList;
import java.util.Random;

import model.chromosomes.Chromosome;

/**
 * Class to implement the IJ crossover.
 * Exchanges alleles given a position as single point crossover.
 * Then, on the other part, if an allele is already used, it is replaced by the allele - 1 until it is not used. 
 */
public class IJCrossover implements Crossover{

	private Random random;
	
	public IJCrossover() {
		random = new Random();
	}
	
	@Override
	/**
	 * CX crossover (the original chromosomes are modified)
	 */
	public void cross(Chromosome c1, Chromosome c2) {
		int pos = random.nextInt(1, c1.getChromosomeLength());
		//TODO eliminar
		pos = 3;
		
		//Select the alleles that will be replaced
		ArrayList<Object> replacement1 = c1.getAlleles(0, pos);
		//Replace the same subsection of the chromosome
		ArrayList<Object> replacement2 = c2.replaceAlleles(replacement1, 0, pos);
		c1.replaceAlleles(replacement2, 0, pos);
		
		for(int i = pos+1; i < c1.getChromosomeLength(); i++) {
			if(replacement2.contains(c1.getAllele(i))) {
				for(int j = 1; j < c1.getChromosomeLength()+1; j++) {
					if(!replacement2.contains((Object)j)) {
						replacement2.add((Object)j);
						c1.setAllele(i, (Object)j);
						break;
					}
				}	
			}
			
			if(replacement1.contains(c2.getAllele(i))) {
				for(int j = 1; j < c2.getChromosomeLength()+1; j++) {
					if(!replacement1.contains((Object)j)) {
						replacement1.add((Object)j);
						c2.setAllele(i, (Object)j);
						break;
					}
				}	
			}
		}
	}
}
