package model.listRep.crossover;

import java.util.ArrayList;

import model.Crossover;
import model.listRep.chromosomes.Chromosome;
import model.representation.Representation;

/**
 * Class to implement the cycle crossover
 */
public class CycleCrossover implements Crossover{

	public CycleCrossover() {
	}
	
	@Override
	/**
	 * CX crossover (the original chromosomes are modified)
	 */
	public void cross(Representation co1, Representation co2) {
		Chromosome c1 = (Chromosome) co1;
		Chromosome c2 = (Chromosome) co2;
		
		int index = 0;
		//save the alleles positions of the cycle
		Object al2 = c2.getAllele(index);
		ArrayList<Integer> setPositions = new ArrayList<Integer>();
		setPositions.add(index);
		//search the position of al2 on first parent
		index = c1.indexOf(al2)-1;
		while(index != 0) {
			//get the allele on the other parent
			al2 = c2.getAllele(index);
			setPositions.add(index);
			//search the position of al2 on first parent
			index = c1.indexOf(al2)-1;
		}
		//exchange the the remain alleles
		for(int i = 0; i < c1.getChromosomeLength(); i++) {
			if(!setPositions.contains(i)) {
				Object al1 = c1.getAllele(i);
				al2 = c2.getAllele(i);
				c1.setAllele(i, al2);
				c2.setAllele(i, al1);
			}
		}
	}
}
