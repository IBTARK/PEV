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
			Object alelo = c1.getAllele(i);
			Double al = (double)alelo;
			while(replacement2.contains((Object)al)) {
				al = al -1;
				if(al == 0) {
					al = (double)c1.getChromosomeLength();
				}
			}
			replacement2.add((Object)al);
			c1.setAllele(i, (Object)al);
			
			alelo = c2.getAllele(i);
			al = (double)alelo;
			while(replacement1.contains((Object)al)) {
				al = al -1;
				if(al == 0) {
					al = (double)c2.getChromosomeLength();
				}
			}
			replacement1.add((Object)al);
			c2.setAllele(i, (Object)al);
		}
	}
}
